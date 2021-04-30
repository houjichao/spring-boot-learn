package com.hjc.learn.design.mode.visitor.dispatch;

/**
 * @author houjichao
 */
public abstract class West {

    /**
     * goWest1
     * @param east
     */
    public abstract void goWest1(SubEast1 east);

    /**
     * goWest2
     * @param east
     */
    public abstract void goWest2(SubEast2 east);
}