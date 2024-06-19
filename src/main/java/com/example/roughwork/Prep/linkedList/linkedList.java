package com.example.roughwork.Prep.linkedList;

import java.util.LinkedList;

public class linkedList {
//    Node head; //you can create a global variable head so that you don't loose the head of a linkedList
    static class Node{
     int data;
     Node next;
     Node(int d){
         data=d;
     }

     public static Node reverseLinkedList(Node head){
        Node prev=null;
        Node current=head;
         // null-> 1 -> 2-> 3->4->5
//          prev  curr next
        while(current!=null){
            Node next=current.next;
            current.next=prev;//if current is 1 then you set current.next to null making it the last value
            prev=current;//now previous value will point to current
            current=next;//so you can goto next value
        }
        return prev;// at the end prev will have the head
     }

    public static Node reverseLinkedListRecursively(Node head,Node prev){
        if(head==null)
            return prev;
        Node next=head.next;
        head.next=prev;
        return reverseLinkedListRecursively(next,head);//current value is next and prev value is current value
    }
//--------------------------------------------------------------
    public static Node zipperList(Node head1,Node head2){
         Node tail=head1;
         Node current1=head1.next;
         Node current2=head2;
         int count=0;

         while(current1!=null && current2!=null){
             if(count%2==0){
                 tail.next=current2;
                 current2=current2.next;
             }else{
                 tail.next=current1;
                 current1=current1.next;
             }
             tail=tail.next;
             count++;
         }
         if(current1!=null)
             tail.next=current1;
         if(current2!=null)
            tail.next=current2;

         return head1;
    }
    public static Node zipperListRecursive(Node head1,Node head2){
         if(head1==null && head2==null)
             return null;
         if(head1==null)
             return head2;
         if(head2==null)
            return head1;

         Node next1=head1.next;
         Node next2=head2.next;

         head1.next=head2;
         head2.next=zipperListRecursive(next1,next2);// to avoid counter like the one used in iterative
         return head1;
    }
    public static void printer(Node head){
        while(head!=null){
            System.out.print(head.data+" ");
            head=head.next;
        }
        System.out.println();
    }
    public static boolean isPalindrome(Node head) {
        Node ptr1=head;
        Node ptr2=head;
        while(ptr2.next!=null && ptr2.next.next!=null){
            ptr1=ptr1.next;
            ptr2=ptr2.next.next;
        }
        //ptr1 is the middle element
        Node prev=null;
        Node current=ptr1.next;
        while(current!=null){
            Node next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        //prev became the first element of the second half of main linkedlist after reversing

        Node first=head;
        Node second=prev;
        printer(first);
        printer(second);
        while(first!=null && second!=null){
            if(first.data!=second.data)
                return false;
            first=first.next;
            second=second.next;
        }
        return true;
    }

//    -----Another palindrome solution---------
    Node curr;
    public boolean isPalindrome2(Node head) {
        curr = head;
        return solve(head);
    }

    public boolean solve(Node head) {
        if(head == null) return true;
        boolean ans = solve(head.next) && head.data == curr.data;
        curr = curr.next;
        return ans;
    }

    //In the below it detects where the cycle starts - Method is Floyd's Cycle-Finding algorithm
    public Node detectCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
    //In the below it detects if a cycle is present
    public boolean hasCycle(Node head) {
        Node slow_pointer = head, fast_pointer = head;
        while (fast_pointer != null && fast_pointer.next != null) {
            slow_pointer = slow_pointer.next;
            fast_pointer = fast_pointer.next.next;
            if (slow_pointer == fast_pointer) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node head=new Node(1);
        Node a=new Node(2);
        Node b=new Node(2);
        Node c=new Node(1);
//        Node d=new Node(1);
        //1->2->3->4->5
        head.next=a;
        a.next=b;
        b.next=c;
//        c.next=d;
        System.out.println(isPalindrome(head));
    }
 }
}
