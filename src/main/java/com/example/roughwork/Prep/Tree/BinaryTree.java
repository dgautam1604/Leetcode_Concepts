package com.example.roughwork.Prep.Tree;

import java.util.*;

public class BinaryTree {

    public static class node<T> {
        node left;
        node right;
        T val;
        node(T v){
            this.val=v;
            this.left=null;
            this.right=null;
        }
    }

    public static void dfsIterative(node root){
        if(root==null)
            return;
        Stack<node> s=new Stack<>();
        s.push(root);
        while (!s.isEmpty()){
            node current=s.pop();
            System.out.print(current.val+" ");
            if(current.right!=null)
                s.push(current.right);
            if(current.left!=null)
                s.push(current.left);
        }
    }
    public static void bfsIterative(node root){
        if(root==null)
            return;
        Queue<node> s=new LinkedList<>();
        s.add(root);
        while (!s.isEmpty()){
            node current=s.remove();
            System.out.print(current.val+" ");
            if(current.left!=null)
                s.add(current.left);
            if(current.right!=null)
                s.add(current.right);

        }
    }


    public static boolean bfsIterativeFinder(node root, String target){
        if(root==null)
            return false;
        Queue<node> s=new LinkedList<>();
        s.add(root);
        while (!s.isEmpty()){
            node current=s.remove();
            if(current.equals(target))
                return true;
            System.out.print(current.val+" ");
            if(current.left!=null)
                s.add(current.left);
            if(current.right!=null)
                s.add(current.right);

        }
        return false;
    }

    public static boolean dfsFinder(node root, String target){
        if(root==null)
            return false;
        if(root.val==target)
            return true;
        return (dfsFinder(root.left,target) || dfsFinder(root.right,target));

    }

    public static void dfsRecursive(node root){
        if(root==null)
            return;
        System.out.print(root.val+" ");
        if(root.left!=null)
            dfsRecursive(root.left);
        if(root.right!=null)
            dfsRecursive(root.right);

    }

    public static int dfsSumRecursive(node<Integer> root){
        if(root.left==null && root.right==null)
            return root.val;
        int left=0,right=0;
        if(root.left!=null)
            left=dfsSumRecursive(root.left);
        if(root.right!=null)
            right=dfsSumRecursive(root.right);
        return root.val+left+right;
    }
    public static int dfsmaxPathSumRecursive(node<Integer> root){
        if(root.left==null && root.right==null)
            return root.val;

        int left=0,right=0;
        if(root.left!=null)
            left=dfsSumRecursive(root.left);
        if(root.right!=null)
            right=dfsSumRecursive(root.right);
        if(left>right) {
            return root.val + left;
        }
        else {
            return root.val + right;
        }
    }
    public int maxDepthFinder(node<Integer> root) {
        if(root==null)
            return 0;
        int left = 0,right=0;
        if(root.left!=null)
            left=maxDepthFinder(root.left);
        if(root.right!=null)
            right=maxDepthFinder(root.right);
        return 1+Math.max(left,right);
    }



    public static void main(String[] args) {
        node a=new node("a");
        node b=new node("b");
        node c=new node("c");
        node d=new node("d");
        node e=new node("e");
        node f=new node("f");
        a.left=b;
        a.right=c;
        b.left=d;
        b.right=e;
        c.right=f;
        dfsIterative(a);
        System.out.println();
        dfsRecursive(a);
        System.out.println();
        bfsIterative(a);
        System.out.println();
        System.out.println(dfsFinder(a,"f"));
        // a
//        / \
//        b  c
//        /\  \
//        d e  f

        node p=new node(5);
        node q=new node(1);
        node r=new node(4);
        node s=new node(3);
        node t=new node(6);
        p.left=q;
        p.right=r;
        q.left=s;
        q.right=t;
        // 5
//        / \
//        1  4
//          /\
//          3 6
        node x=new node(2);
        node y=new node(1);
        node z=new node(3);
        x.left=y;
        x.right=z;
        System.out.println(dfsSumRecursive(p));
        System.out.println(dfsmaxPathSumRecursive(p));




    }
}
