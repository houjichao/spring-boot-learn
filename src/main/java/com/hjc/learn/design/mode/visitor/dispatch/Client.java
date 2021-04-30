package com.hjc.learn.design.mode.visitor.dispatch;

/**
 * 双重分派
 *
 * 系统运行时，会首先创建SubWest1和SubEast1对象，然后客户端调用SubEast1的goEast()方法，
 * 并将SubWest1对象传入。由于SubEast1对象重写了其超类East的goEast()方法，
 * 因此，这个时候就发生了一次动态的单分派。当SubEast1对象接到调用时，会从参数中得到SubWest1对象，
 * 所以它就立即调用这个对象的goWest1()方法，并将自己传入。由于SubEast1对象有权选择调用哪一个对象，
 * \因此，在此时又进行一次动态的方法分派。
 *
 * @author houjichao
 */
public class Client {

    public static void main(String[] args) {
        //组合1
        East east = new SubEast1();
        West west = new SubWest1();
        east.goEast(west);
        //组合2
        east = new SubEast1();
        west = new SubWest2();
        east.goEast(west);
    }

}