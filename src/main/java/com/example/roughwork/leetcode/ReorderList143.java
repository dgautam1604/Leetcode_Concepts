package com.example.roughwork.leetcode;

public class ReorderList143 {
      public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public void reorderList1(ListNode head) {
        ListNode first=head;
        ListNode reverse=new ListNode();
        while(first.next!=null){
            ListNode currentElement=first;
            ListNode lastElement=first;
            first=first.next;
        }
        ListNode last=first;

    }
    public static void reorderList(ListNode head) {
        // if head will be null or head.next will be null simply return ;
        if(head==null || head.next==null)return ;

        //finding middle element
        ListNode slow = head;
        ListNode fast= head;
        while(fast!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newNode = reverseList(slow.next);
      }

    // method to reverse the linkedList
    public static ListNode reverseList(ListNode node){
        ListNode prev = null;
        ListNode curr = node;
        ListNode next = null;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }

        return prev;
    }
    public static void main(String[] args) {
        ListNode l=new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6))))));
        reorderList(l);
    }
}
