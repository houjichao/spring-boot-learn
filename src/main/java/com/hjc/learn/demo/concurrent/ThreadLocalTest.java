package com.hjc.learn.demo.concurrent;


import java.util.stream.IntStream;

/**
 * @author houjichao
 */
public class ThreadLocalTest {

    /**
     * 由于ThreadLocal里设置的值，只有当前线程自己看得见，
     * 这意味着你不可能通过其他线程为它初始化值。
     * 为了弥补这一点，ThreadLocal提供了一个withInitial()方法统一初始化所有线程的ThreadLocal的值
     */
    private ThreadLocal<Integer> localInt = ThreadLocal.withInitial(() -> 6);


    public static void main(String[] args) {


        /**
         * ThreadLocal threadLocal = new ThreadLocal();
         * 直接使用 结果是null
         * 因为在子线程中，是没有threadLocal的。
         * 如果我们希望子线可以看到父线程的ThreadLocal，那么就可以使用InheritableThreadLocal。
         * 顾名思义，这就是一个支持线程间父子继承的ThreadLocal，将上述代码中的threadLocal使用InheritableThreadLocal：
         *
         *
         * 可以看到，每个线程都可以访问到从父进程传递过来的一个数据。
         * 虽然InheritableThreadLocal看起来挺方便的，但是依然要注意以下几点：
         *
         * 变量的传递是发生在线程创建的时候，如果不是新建线程，而是用了线程池里的线程，就不灵了
         * 变量的赋值就是从主线程的map复制到子线程，它们的value是同一个对象，如果这个对象本身不是线程安全的，那么就会有线程安全问题
         *
         */
        //ThreadLocal threadLocal = new ThreadLocal();
        InheritableThreadLocal threadLocal = new InheritableThreadLocal();

        IntStream.range(0,10).forEach(i -> {
            //每个线程的序列号，希望在子线程中能够拿到
            threadLocal.set(i);
            //这里来了一个子线程，我们希望可以访问上面的threadLocal
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}
