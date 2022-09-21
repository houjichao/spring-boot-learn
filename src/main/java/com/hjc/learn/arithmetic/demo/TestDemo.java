package com.hjc.learn.arithmetic.demo;

import com.hjc.learn.arithmetic.model.ListNode;
import com.hjc.learn.arithmetic.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TestDemo {

    /**
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 举例 给定的有序数组： [-10, -3, 0, 5,
     * 9], 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树： 0 / \ -3   9 /   / -10  5
     */


    public static TreeNode sortedList(ListNode head) {

        Queue<Integer> queue = new LinkedList<>();
        while (head != null) {
            queue.add(head.val);
            head = head.next;
        }
        int[] nums = new int[queue.size()];
        int index = 0;
        while (!queue.isEmpty()) {
            nums[index++] = queue.poll();
        }

        return sortedList(nums, 0, nums.length - 1);

    }

    public static TreeNode sortedList(int[] nums, int left, int right) {
        //当起点位置大于终点位置时，说明节点已经遍历完了，直接返回空树
        if (left > right) {
            return null;
        }
        //获取中间位置的值作为根节点
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedList(nums, left, mid - 1);
        root.right = sortedList(nums, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        System.out.println(sortedList(head).toString());
    }

}
