package com.example.roughwork.Prep.Backtrack;

import com.example.roughwork.Prep.Tree.BST;

import java.util.*;

public class leetcode {
//Problem: The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
//For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
    //My solution
    public static int subsetXORSum(int[] nums) {
        List<List<Integer>> l=new ArrayList<>();
        Arrays.sort(nums);
        backtrack(l,new ArrayList<>(),nums,0);
        List<Integer> res=new ArrayList<>();
        for(List<Integer> a:l){
            int xorOutput=0;
            for(int i=0;i< a.size();i++){
                xorOutput=xorOutput^a.get(i);
            }
            res.add(xorOutput);
        }
        int sum=0;
        for(int i:res)
            sum=sum+i;
        return sum;
    }

    public static void backtrack(List<List<Integer>> l, List<Integer> temp,int[] nums,int start) {
        l.add(new ArrayList<>(temp));
        for(int i=start;i< nums.length;i++){
            temp.add(nums[i]);
            backtrack(l,temp,nums,i+1);
            temp.remove(temp.size()-1);
        }
    }
//---------------------------------------------------------------------------------------------------------------
 //Best solution
    public int subsetXORSum2(int[] nums) {
        return find(nums, 0, 0);
    }
    public static int find(int[] nums, int level, int currentXor){
        if(nums.length==level) return currentXor;
        int inc=find(nums, level+1, currentXor^nums[level]);
        int exe=find(nums, level+1, currentXor);

        return inc+exe;

    }
//---------------------------------------------------------------------------------------------------------------

//    257. Given the root of a binary tree, return all root-to-leaf paths in any order.
//A leaf is a node with no children. Input: root = [1,2,3,null,5]
//Output: ["1->2->5","1->3"]
    public List<String> binaryTreePaths(BST.TreeNode root) {
        List<String> l=new ArrayList<>();
        if(root!=null)
            helper(l, new StringBuilder(""),root);
        return l;
    }

    public void helper(List<String> l,StringBuilder s,BST.TreeNode root){
        if(root.left==null && root.right==null)
            l.add(String.valueOf(s.append(root.val)));
        if(root.left!=null)
            helper(l,s.append(root.val+"->"),root.left);
        if(root.right!=null)
            helper(l,s.append(root.val+"->"),root.right);
    }
    //---------------------------------------------------------------------------------------------------------------

    //Letter Combinations of a Phone Number
//Generate Parentheses
//Combination Sum
//Permutations
//N-Queens
//Subsets
//Word Search
//Palindrome Partitioning
//Lowest Common Ancestor of a Binary Tree
    //-------------------there solution--------------------------------------------------------------------------------------------

    private void binaryTreeHelper(List<String> l, BST.TreeNode root, StringBuilder s){
        int len = s.length();
        s.append(root.val);
        if(root.left==null && root.right==null)
            l.add(String.valueOf(s));
        else{
            s.append("->");
            if(root.left!=null)
                helper(l,s,root.left);
            if(root.right!=null)
                helper(l,s,root.right);}
        s.setLength(len);
    }
//---------------------------------------------------------------------------------------------------------------

////17. Letter Combinations of a Phone Number
//    Input: digits = "23"
//    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

    public static List<String> letterCombinations(String digits) {
        HashMap<Character, List<Character>> keypad = new HashMap<>();
//        keypad.put('1', Arrays.asList());
        keypad.put('2', Arrays.asList('a', 'b', 'c'));
        keypad.put('3', Arrays.asList('d', 'e', 'f'));
        keypad.put('4', Arrays.asList('g', 'h', 'i'));
        keypad.put('5', Arrays.asList('j', 'k', 'l'));
        keypad.put('6', Arrays.asList('m', 'n', 'o'));
        keypad.put('7', Arrays.asList('p', 'q', 'r', 's'));
        keypad.put('8', Arrays.asList('t', 'u', 'v'));
        keypad.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        List<String> l=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        if(digits=="")
            return new ArrayList<>();
        letterCombinationsHelper(digits,l,sb,keypad);
        return l;
    }
    public static void letterCombinationsHelper(String digits, List<String> l,StringBuilder sb,HashMap<Character, List<Character>> keypad){
        if(digits.isEmpty()) {
            l.add(sb.toString());
        }

        else {
            char s = digits.charAt(0);
            List<Character> val = keypad.get(s);
            for (Character a : val) {
                letterCombinationsHelper(digits.substring(1), l, sb.append(a), keypad);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
///there solution
    public static List<String> letterCombinations2(String digits) {
        if (digits.isEmpty()) return Collections.emptyList();

        String[] phone_map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> output = new ArrayList<>();
        backtrack("", digits, phone_map, output);
        return output;
    }

    private static void backtrack(String str, String digits, String[] phone_map, List<String> l) {
        if (digits.isEmpty()) {
            l.add(str);
        } else {
            String letters = phone_map[digits.charAt(0) - '2'];
            for (char letter : letters.toCharArray()) {
                backtrack(str + letter, digits.substring(1), phone_map, l);
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------
//    236. Lowest Common Ancestor of a Binary Tree
//    The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has
//    both p and q as descendants (where we allow a node to be a descendant of itself).”

    //Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    //Output: 5
    //Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
    public BST.TreeNode lowestCommonAncestor(BST.TreeNode root, BST.TreeNode p, BST.TreeNode q) {
        if(root == null || root == p || root == q)
            return root;

        BST.TreeNode  left = lowestCommonAncestor(root.left, p, q);
        BST.TreeNode  right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {return right;}
        if (right == null) {return left;}
        else { return root;}
    }


    public static void main(String[] args) {
        int[] nums={3,4,5,6,7,8};
//        System.out.println(subsetXORSum(nums));
        // Populate the HashMap with keypad mappings

        String digits="23";
        System.out.println(letterCombinations2(digits));
        System.out.println(letterCombinations(digits));


    }
}
