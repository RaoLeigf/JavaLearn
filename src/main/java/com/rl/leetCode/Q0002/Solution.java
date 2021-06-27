package com.rl.leetCode.Q0002;

/*
    1. 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
    并且每个节点只能存储一位数字。
    2. 请你将两个数相加，并以相同形式返回一个表示和的链表。
    3. 你可以假设除了数字 0 之外，这两个数都不会以 0开头。

    提示：
    每个链表中的节点数在范围 [1, 100] 内
    0 <= Node.val <= 9
    题目数据保证列表表示的数字不含前导零
 */


import com.alibaba.fastjson.JSON;

class Solution {
    public static void main(String[] args){
        System.out.println("1313");
        ListNode l1  = new ListNode(2);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(7);

        ListNode l2  = new ListNode(5);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(8);
        l2.next.next.next = new ListNode(9);


        ListNode listNode = ListNode.addTwoNumbers(l1, l2);

        System.out.println(JSON.toJSONString(listNode));

    }
}