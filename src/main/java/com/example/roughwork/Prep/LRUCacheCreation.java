package com.example.roughwork.Prep;


class LRUCacheCreation {
    Node head = new Node();
    Node tail = head;
    Node[] nodes = new Node[10001];
    int n = 0;

    public LRUCacheCreation(int capacity) {
        n = capacity;
    }

    public int get(int key) {
        if (nodes[key] != null) {
            if (nodes[key] != tail) {
                nodes[key].prv.next = nodes[key].next;
                nodes[key].next.prv = nodes[key].prv;
                tail.next = nodes[key];
                nodes[key].prv = tail;
                tail = tail.next;
            }
            return nodes[key].val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (nodes[key] != null) {
            nodes[key].val = value;
            if (nodes[key] != tail) {
                nodes[key].prv.next = nodes[key].next;
                nodes[key].next.prv = nodes[key].prv;
                tail.next = nodes[key];
                nodes[key].prv = tail;
                tail = tail.next;
            }
        } else if (n > 0) {
            tail.next = new Node(value, key, tail, null);
            nodes[key] = tail.next;
            tail = tail.next;
            n--;
        } else {
            Node tmp = head.next;
            nodes[tmp.key] = null;
            if (tmp != tail) {
                head.next = tmp.next;
                tmp.next.prv = head;
            } else {
                tail = head;
            }
            tail.next = new Node(value, key, tail, null);
            nodes[key] = tail.next;
            tail = tail.next;
        }
    }
}

class Node {
    int val;
    int key;
    Node prv;
    Node next;

    public Node() {

    }

    public Node(int val, int key, Node prv, Node next) {
        this.val = val;
        this.key = key;
        this.next = next;
        this.prv = prv;
    }
}
