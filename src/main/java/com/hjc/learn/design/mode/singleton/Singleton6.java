package com.hjc.learn.design.mode.singleton;

/**
 * 6、双重校验锁（线程安全，效率高）
 * 此种实现中不用每次需要获得锁，减少了获取锁和等待的事件。
 * 注意volatile关键字的使用，保证了各线程对singleton静态实例域修改的可见性。
 *
 * 虽然上面5这种写法是可以正确运行的，但是其效率低下，还是无法实际应用。
 * 因为每次调用getSingleton()方法，都必须在synchronized这里进行排队，
 * 而真正遇到需要new的情况是非常少的。所以，就诞生了此种写法。
 *
 * 那么，这种写法是不是绝对安全呢？前面说了，从语义角度来看，并没有什么问题。
 * 注意，前面反复提到“从语义上讲是没有问题的”，但是很不幸，禁止指令重排优化这条语义直到jdk1.5以后才能正确工作。
 * 此前的JDK中即使将变量声明为volatile也无法完全避免重排序所导致的问题。
 * 所以，在jdk1.5版本前，双重检查锁形式的单例模式是无法保证线程安全的。
 *
 * @author houjichao
 */
public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }

}
