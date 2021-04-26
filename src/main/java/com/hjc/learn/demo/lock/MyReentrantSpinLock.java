package com.hjc.learn.demo.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述：实现一个可重入的自旋锁
 *
 * @author houjichao
 */

public class MyReentrantSpinLock {

    /**
     * AtomicReference类提供了一个可以原子读写的对象引用变量。
     * 原子意味着尝试更改相同AtomicReference的多个线程（例如，使用比较和交换操作）
     * 不会使AtomicReference最终达到不一致的状态。 AtomicReference甚至有一个先进的compareAndSet（）方法，
     * 它可以将引用与预期值（引用）进行比较，如果它们相等，则在AtomicReference对象内设置一个新的引用。
     */
    private AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 重入次数
     */
    private int count = 0;

    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }

        //自旋获取锁
        while (!owner.compareAndSet(null, t)) {
            System.out.println(t.getName() + "自旋了");
        }
    }

    public void unlock() {
        Thread t = Thread.currentThread();

        //只有持有锁的线程才能解锁
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            } else {
                //此处无需CAS操作，因为没有竞争，因为只有线程持有者才能解锁
                owner.set(null);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyReentrantSpinLock spinLock = new MyReentrantSpinLock();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了了自旋锁");
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

