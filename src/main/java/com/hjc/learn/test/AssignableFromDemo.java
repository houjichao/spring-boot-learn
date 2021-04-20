package com.hjc.learn.test;

/**
 * 1. isAssignableFrom()是干什么用的？
 * 首先我们必须知道的是，java里面一切皆对象，类本身也是会当成对象来处理，主要体现在类的.class文件，其实加载到java虚拟机之后，也是一个对象，它就是Class对象,全限定类名:java.lang.Class。
 * <p>
 * 那这个isAssignableFrom()其实就是Class对象的一个方法：
 * <p>
 * 用native关键字描述，说明是一个底层方法，实际上是使用c/c++实现的，java里面没有实现，那么这个方法是干什么的呢？我们从上面的注释可以解读：
 * <p>
 * 如果是A.isAssignableFrom(B)
 * 确定一个类(B)是不是继承来自于另一个父类(A)，一个接口(A)是不是实现了另外一个接口(B)，或者两个类相同。主要，这里比较的维度不是实例对象，而是类本身，因为这个方法本身就是Class类的方法，判断的肯定是和类信息相关的。
 * <p>
 * 也就是判断当前的Class对象所表示的类，是不是参数中传递的Class对象所表示的类的父类，超接口，或者是相同的类型。是则返回true，否则返回false。
 *
 * @author houjichao
 */
public class AssignableFromDemo {

    interface InterfaceA {
    }

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }

    static class ClassB implements InterfaceA{

    }
    static class ClassC implements InterfaceA{

    }
    static class ClassD extends ClassB{

    }

    public static void main(String[] args) {

        A a = new A();
        B b = new B();
        B b1 = new B();
        C c = new C();
        System.out.println(a.getClass().isAssignableFrom(a.getClass()));
        System.out.println(a.getClass().isAssignableFrom(b.getClass()));
        System.out.println(a.getClass().isAssignableFrom(c.getClass()));
        System.out.println(b1.getClass().isAssignableFrom(b.getClass()));

        System.out.println(b.getClass().isAssignableFrom(c.getClass()));
        System.out.println(c.getClass().isAssignableFrom(b.getClass()));

        System.out.println("=====================================");
        System.out.println(A.class.isAssignableFrom(a.getClass()));
        System.out.println(A.class.isAssignableFrom(b.getClass()));
        System.out.println(A.class.isAssignableFrom(c.getClass()));

        System.out.println("=====================================");
        System.out.println(Object.class.isAssignableFrom(a.getClass()));
        System.out.println(Object.class.isAssignableFrom(String.class));
        System.out.println(String.class.isAssignableFrom(Object.class));

        /**
         * 从运行结果来看，C继承于B，B继承于A,那么任何一个类都可以isAssignableFrom其本身，这个从中文意思来理解就是可以从哪一个转换而来，
         * 自身转换而来肯定是没有问题的，父类可以由子类转换而来也是没有问题的，所以A可以由B转换而来，同时也可以由子类的子类转换而来。
         *
         * 上面的代码也说明一点，所有的类，其最顶级的父类也是Object，也就是所有的类型都可以转换成为Object。
         */

        System.out.println("=====================================");
        System.out.println(InterfaceA.class.isAssignableFrom(InterfaceA.class));
        System.out.println(InterfaceA.class.isAssignableFrom(ClassB.class));
        System.out.println(InterfaceA.class.isAssignableFrom(ClassC.class));
        System.out.println(ClassB.class.isAssignableFrom(ClassC.class));
        System.out.println("============================================");

        System.out.println(ClassB.class.isAssignableFrom(ClassD.class));
        System.out.println(InterfaceA.class.isAssignableFrom(ClassD.class));

        /**
         * 从上面的结果看，其实接口的实现关系和类的实现关系是一样的，没有什么区别，但是如果B和C都实现了同一个接口，他们之间其实是不能互转的。
         *
         * 如果B实现了接口A，D继承了B，实际上D是可以上转为A接口的，相当于D间接实现了A，这里也说明了一点，其实继承关系和接口实现关系，在isAssignableFrom()的时候是一样的，一视同仁的。
         *
         * 总结：
         * isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。
         * 如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
         *
         * 这个方法在我们平时使用的不多，但是很多源码里面判断两个类之间的关系的时候，（注意：是两个类的关系，不是两个类的实例对象的关系！！！），
         * 会使用到这个方法来判断，大概因为框架代码或者底层代码都是经过多层抽象，做到容易拓展和解耦合，只能如此。
         */

    }
}
