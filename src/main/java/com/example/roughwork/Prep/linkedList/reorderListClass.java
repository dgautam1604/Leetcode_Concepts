package com.example.roughwork.Prep.linkedList;

import java.util.List;

public class reorderListClass {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    //---------------------------------------------------------------------------------------------------------------
//
//Reorder with first half as it is and second half reversed and they are connected with 1 element from first half
//    to first element of reversed second half and so on
    //1-2-3-4-5-6
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next != null && fast.next.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //you will find the middle element
        // Step 2: Reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow.next; //this is the next to middle element
        slow.next=null;
        while (curr!=null){
            ListNode nextNode=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nextNode;
        }
        //Now the linked list is reverse
        ListNode first = head;
        ListNode second = prev;
        System.out.println("first");
        printList(first);
        System.out.println("prev");
        printList(prev);
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
        printList(head);
    }
    //---------------------------------------------------------------------------------------------------------------
//2. Add Two Numbers Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        ListNode a = new ListNode(2);
        a.next = new ListNode(4);
        a.next.next = new ListNode(3);

        ListNode b = new ListNode(5);
        b.next = new ListNode(6);
        b.next.next = new ListNode(4);
        printList(addTwoNumbers(a,b));

//        System.out.println("Original linked list:");
//        printList(head);
//        reorderList(head);

    }
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
