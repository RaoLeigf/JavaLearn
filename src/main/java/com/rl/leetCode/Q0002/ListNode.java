package com.rl.leetCode.Q0002;


import org.w3c.dom.NodeList;

public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) {
         this.val = val;
     }
     ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = null,tail = null;
        int gtNum = 0;
        while(null != l1 || null != l2){
            int l1Num = 0;
            int l2Num = 0;
            if(null != l1){
                l1Num = l1.val;
                l1 = l1.next;
            }

            if(null != l2){
                l2Num = l2.val;
                l2 = l2.next;
            }

            //第一次赋值
            if(null == res){
                res = new ListNode((l1Num + l2Num + gtNum)%10);
                tail = res;
            }
            else{
                tail.next = new ListNode((l1Num + l2Num + gtNum)%10);
                tail = tail.next;

            }
            gtNum = (l1Num + l2Num + gtNum)/10;

        }

        if(gtNum > 0){
            tail.next = new ListNode(gtNum);
        }

        return res;
    }


}
