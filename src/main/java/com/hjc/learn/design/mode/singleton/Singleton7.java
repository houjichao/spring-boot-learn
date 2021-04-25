package com.hjc.learn.design.mode.singleton;

/**
 * 静态内部类法
 *
 * 有没有一种延时加载，并且能保证线程安全的简单写法呢？
 * 我们可以把Singleton实例放到一个静态内部类中，这样就避免了静态实例在Singleton类加载的时候就创建对象，
 * 并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的
 *
 * 但是，上面提到的所有实现方式都有两个共同的缺点：
 *
 * 都需要额外的工作(Serializable、transient、readResolve())来实现序列化，
 * 否则每次反序列化一个序列化的对象实例时都会创建一个新的实例。
 *
 * 可能会有人使用反射强行调用我们的私有构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）。
 *
 * @author houjichao
 */
public class Singleton7 {

    private static class Holder {
        private static Singleton7 instance = new Singleton7();
    }

    private Singleton7() {

    }

    public static Singleton7 getInstance() {
        return Holder.instance;
    }

}
