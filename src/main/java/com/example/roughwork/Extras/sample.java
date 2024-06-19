package com.example.roughwork.Extras;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Qualifier("ss")
public  class sample<K,V> {
    public void trip(K d){

    }
    public void trip2(){

    }

//    public static void main(String[] args){
//        int[] a={1,2,3,4,9,8,7,6};
//
//        Integer[] valueToInteger=Arrays.stream(a).boxed().toArray(Integer[]::new);
//        Integer[] valueToIntegerUsingIntStream= IntStream.of(a).boxed().toArray(Integer[]::new);
//        Arrays.sort(a);
//
//        int[] arr = { 13, 7, 6, 45, 21, 9, 101, 102 };
//        Integer[] arrToInteger= Arrays.stream(arr).boxed().toArray(Integer[]::new);
//        // Applying sort() method over to above array
//        // by passing the array as an argument
//        Arrays.sort(arr);
//
//        List<Integer> toSortList = Arrays.stream(a).boxed().collect(Collectors.toList());
//        Collections.sort(toSortList);
//
//        HashMap<Integer, String> map = new HashMap<>();
//        map.put(55, "John");
//        map.put(22, "Apple");
//        map.put(66, "Earl");
//        Collections.sort(toSortList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
//
//        ExecutorService e= Executors.newFixedThreadPool(5);
//        e.execute(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<5;i++){
//                    System.out.println(i);
//                }
//            }
//        });
//        e.shutdown();
//    }
    public int search2(int[] nums, int target) {
        int l=0;
        int r=nums.length;
        while(l<r) {
            int m = (l + r - 1) / 2;
            if(nums[m]==target)
                return m;
            if(target<nums[m])
                r=m-1;
            else if(nums[m]<target)
                l= m+1;
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while(l<r) {
            int m = l + (r - l) / 2;
            if(nums[m]==target)
                return m;
            if(target<nums[m])
                r=m-1;
            else if(nums[m]<target)
                l= m+1;
        }
        return -1;
    }
    public int searcher(int[] nums, int target, int l, int r){
        int m = l + (r - l) / 2;
        if (r >= l) {
            if (nums[m] == target)
                return m;
            if (target < nums[m])
                return searcher(nums, target, l, m - 1);
            else if (nums[m] < target)
                return searcher(nums, target, l + 1, m);
        }
        return -1;
    }
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Invalid input: root is null");
        }

        int leftmostValue = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // The first element encountered in each level is the leftmost value
                if (i == 0) {
                    leftmostValue = current.val;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }


            }
        }

        return leftmostValue;
    }
    public int findHeight(TreeNode root) {
        if(root==null)
            return 0;
        int lDepth=findHeight(root.left);
        int rDepth=findHeight(root.right);
        if (lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }

//    public int diameterOfBinaryTree(TreeNode root) {
//
//        // Create an array to hold the diameter of the tree
//        int diameter[] = new int[1];
//
//        // Recursively calculate the height of the tree and update the diameter array
//        height(root,diameter);
//
//        // Return the diameter of the tree
//        return diameter[0];
//    }


    public void moveZeroes(int[] nums) {
        int n= nums.length-1;
        int l=0;
        for(int i=0;i<n;i++){
            if(nums[i]!=0){
                nums[l]=nums[i];
                l++;
            }
        }
        while(l< nums.length){
            nums[l]=0;
            l++;
        }
    }
    public static String longestCommonPrefix(String[] strs) {
        int shortestArrayIndex=Integer.MAX_VALUE;
        for(int i=0;i<strs.length;i++){
                if(strs[i].length()<shortestArrayIndex)
                    shortestArrayIndex=strs[i].length();
        }
//        System.out.println("shortestArrayIndex: "+shortestArrayIndex);
        String res="";
        for(int i=0;i<shortestArrayIndex;i++){
            String a=strs[0].substring(i,i+1);
//            System.out.println("checking a "+a);
            for(int j=1;j<strs.length;j++){
                if(!a.equals(strs[j].substring(i,i+1))){
                    return res;
                }
            }
            res=res+a;
        }
        return res;
    }
    public String longestCommonPrefixBetter(String[] v) {
        StringBuilder ans = new StringBuilder();
        Arrays.sort(v);
        String first = v[0];
        String last = v[v.length-1];
        for (int i=0; i<Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> l=new ArrayList<>();

        if(numRows>=1) {
            List<Integer> p=new ArrayList<>();
            p.add(1);
            l.add(p);
        }
        if(numRows>=2) {
            List<Integer> p=new ArrayList<>();
            p.add(1);
            p.add(1);
            l.add(p);
        }
        for(int i=3;i<=numRows;i++){
            List<Integer> p=new ArrayList<>();
            p.add(1);
            List<Integer> q=l.get(i-2);
            for(int j=1;j<q.size();j++){
                int sum=q.get(j)+q.get(j-1);
                p.add(sum);
            }
            p.add(1);
            l.add(p);
        }
        return l;
    }
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> h=new HashMap<>();
        for(Integer n:nums){
            if(h.containsKey(n))
                h.put(n,h.get(n)+1);
            else
                h.put(n,1);
        }
        for (Map.Entry<Integer, Integer> set : h.entrySet()) {

            if(set.getValue()==1){
                int a= set.getKey();
                return a;
            }
        }

        return 0;
    }
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }


    public class Node {
        int val;
        Node left;
        Node right;
        public Node(int item) {
            val = item;
            left = right = null;
        }
    }
    class BinarySearchTree {
        Node root;

        BinarySearchTree() {
            root = null;
        }
        void insert(int key) {
            root = insertRec(root, key);
        }

        private Node insertRec(Node root, int key) {
            if(root==null){
                root=new Node(key);
                return root;
            }
            if(key<root.val)
                insertRec(root.left, key);
            else if(key>root.val)
                insertRec(root.right, key);
            return root;
        }

    }

}

