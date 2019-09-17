package qliphoth.LinkPractice;

public class LinkList {

   public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }

    /**
     * leetcode.24 两两交换链表中的节点 递归交换
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
       if(head==null||head.next==null) {
           return head;
       }
       ListNode nex = head.next.next;
       head.next.next = head;
       head = head.next;
       head.next.next = swapPairs(nex);
       return head;
    }

    /**
     * leetcode.61 旋转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null||k<=0) {
            return head;
        }
        int len = 0;
        ListNode p = head;
        while (p!=null) {
            p = p.next;
            len++;
        }
        int moveNum = k%len;
        if(moveNum<=0) {
            return head;
        }
        p = head;
        for(int i=0;i<(len-moveNum-1);i++) {
            p = p.next;
        }
        ListNode newHead = p.next;
        p.next = null;
        p = newHead;
        while(p.next!=null) {
            p = p.next;
        }
        p.next = head;
        return newHead;
    }
}
