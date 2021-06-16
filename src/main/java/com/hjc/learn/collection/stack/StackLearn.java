package com.hjc.learn.collection.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Stack只有入栈和出栈的操作：
 *
 * 把元素压栈：push(E)；
 * 把栈顶的元素“弹出”：pop()；
 * 取栈顶元素但不弹出：peek()。
 * 在Java中，我们用Deque可以实现Stack的功能：
 *
 * 把元素压栈：push(E)/addFirst(E)；
 * 把栈顶的元素“弹出”：pop()/removeFirst()；
 * 取栈顶元素但不弹出：peek()/peekFirst()。
 * 为什么Java的集合类没有单独的Stack接口呢？因为有个遗留类名字就叫Stack，出于兼容性考虑，所以没办法创建Stack接口，只能用Deque接口来“模拟”一个Stack了。
 *
 * 当我们把Deque作为Stack使用时，注意只调用push()/pop()/peek()方法，不要调用addFirst()/removeFirst()/peekFirst()方法，这样代码更加清晰。
 *
 * 最后，不要使用遗留类Stack。
 * 当初 JDK1.0 在开发时，可能为了快速的推出一些基本的数据结构操作，所以推出了一些比较粗糙的类。
 * 比如，Vector、Stack、Hashtable等。这些类中的一些方法加上了 synchronized 关键字，
 * 容易给一些初级程序员在使用上造成一些误解！而且在之前的几个版本中，性能还不怎么好。
 * 基于 Vector 实现的栈 Stack。底层实际上还是数组，所以还是存在需要扩容。Vector 是由数组实现的集合类，它包含了大量集合处理的方法。
 * 而 Stack 之所以继承 Vector，是为了复用 Vector 中的方法，来实现进栈(push)、出栈(pop)等操作。这里就是 Stack 设计不好的地方，
 * 既然只是为了实现栈，不用链表来单独实现，而是为了复用简单的方法而迫使它继承 Vector，Stack 和 Vector 本来是毫无关系的。
 * 这使得 Stack 在基于数组实现上效率受影响，另外因为继承 Vector 类，Stack 可以复用 Vector 大量方法，这使得 Stack 在设计上不严谨。

 *
 * @author houjichao
 */
public class StackLearn {
    public static void main(String[] args) {
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }
    }

    static String toHex(int n) {
        //Stack<String> stack = new Stack<>();
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sHex = new StringBuilder();
        while (n != 0) {
            int r = n % 16;
            String s = Integer.toHexString(r);
            stack.push(s);
            n /= 16;
        }
        System.out.println("SIZE:" + stack.size());
        while (!stack.isEmpty()) {
            sHex.append(stack.pop());
        }
        return sHex.toString();
    }
}
