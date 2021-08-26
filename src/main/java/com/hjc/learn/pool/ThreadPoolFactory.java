package com.hjc.learn.pool;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 *
 * @author houjichao
 */
@Slf4j
public final class ThreadPoolFactory {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAXIMUM_POOL_SIZE = 40;
    private static final long KEEP_ALIVE_TIME = 60L;
    private static final long AWAIT_TIME_TO_TERMINATE = 10;
    private static final AtomicInteger ATOMIC = new AtomicInteger(0);
    private static final String THREAD_NAME = "THREAD_HJC";
    private static final BlockingQueue<Runnable> DEFAULT_BLOCKING_QUEUE = new LinkedBlockingQueue<>(1000);

    private Set<ExecutorService> EXECUTOR_CONTAINERS = new CopyOnWriteArraySet<>();

    /**
     * 线程池工厂
     */
    private static final ThreadFactory threadFactory = ThreadPoolFactory::newThread;

    /**
     * 线程池拒绝策略
     */
    private static final RejectedExecutionHandler rejectedExecutionHandler = (Runnable r, ThreadPoolExecutor executor) -> {
        log.error("Thread pool is full and discard the task...");
        return;
    };


    private ThreadPoolFactory() {
    }

    private static Thread newThread(Runnable r) {
        return new Thread(r) {{
            setUncaughtExceptionHandler((t, e) -> log.error("Thread [{}] had exception [{}]", t.getName(), e));
            setDaemon(Boolean.FALSE);
            setName(THREAD_NAME + "_" + ATOMIC.getAndIncrement());
        }};
    }

    private static class ThreadPoolFactoryHolder {
        private static ThreadPoolFactory INSTANCE = new ThreadPoolFactory();
    }

    /**
     * 对外提供的获取改线程池管理类的方法
     *
     * @return
     */
    public static ThreadPoolFactory INSTANCE() {
        return ThreadPoolFactoryHolder.INSTANCE;
    }


    /**
     * 创建一个默认的线程池
     */
    public ExecutorService getDefaultThreadPool() {
        return getCustomThreadPool(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, DEFAULT_BLOCKING_QUEUE);
    }

    /**
     * 创建一个定制化的线程池
     */
    private ExecutorService getCustomThreadPool(int corePoolSize,
                                                int maximumPoolSize,
                                                Long keepAliveTime,
                                                BlockingQueue<Runnable> blockingQueue) {
        log.info("Init thread pool [corePoolSize={},maximumPoolSize={},keepAliveTime={}s]...",
                corePoolSize, maximumPoolSize, keepAliveTime);
        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                blockingQueue,
                threadFactory,
                rejectedExecutionHandler);
        EXECUTOR_CONTAINERS.add(executorService);
        log.info("Init thread pool competed,and pool containers size [{}].", EXECUTOR_CONTAINERS.size());
        return executorService;
    }

    /**
     * 关闭线程池
     */
    public void shutdown(ExecutorService executorService) {
        try {
            if (executorService != null) {
                log.info("shut down threadpool...{}", executorService);
                executorService.shutdown();
                executorService.awaitTermination(AWAIT_TIME_TO_TERMINATE, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            if (!executorService.isTerminated()) {
                executorService.shutdownNow();
            }
        } finally {
            try {
                if (executorService != null && !executorService.isShutdown()) {
                    executorService.shutdown();
                }
            } catch (Exception e) {
                log.error("shut down threadpool error...：{}", e);
            }
        }
    }

    /**
     * 优雅关闭线程池...
     */
    public void shutdown() {
        Flux.fromIterable(EXECUTOR_CONTAINERS)
                .doOnNext(executor -> {
                    ExecutorService executorService = executor;
                    try {
                        if (executorService != null) {
                            log.info("start to shut down threadpool...{}", executorService);
                            executorService.shutdown();
                            executorService.awaitTermination(AWAIT_TIME_TO_TERMINATE, TimeUnit.MINUTES);
                            log.info("completed to shut down threadpool...{}", executorService);
                        }
                    } catch (Exception e) {
                        if (!executorService.isTerminated()) {
                            executorService.shutdownNow();
                        }
                    } finally {
                        try {
                            if (executorService != null && !executorService.isShutdown()) {
                                log.info("start to shut down threadpool...{}", executorService);
                                executorService.shutdown();
                                log.info("completed to shut down threadpool...{}", executorService);
                            }
                        } catch (Exception e) {
                            log.error("shut down thread pool error...：{}", e.getMessage());
                        }
                    }
                }).subscribe();

    }


}