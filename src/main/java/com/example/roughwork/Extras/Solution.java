package com.example.roughwork.Extras;

import java.util.*;

public class Solution {
    static List<String> l = new ArrayList<>();
    static Map<String, List<String>> keypadMap = new HashMap<>();

    public static List<String> letterCombinations(String digits) {


        if (digits == null || digits.length() == 0) {
            return l;
        }

        keypadMap.put("2", List.of("a", "b", "c"));
        keypadMap.put("3", List.of("d", "e", "f"));
        keypadMap.put("4", List.of("g", "h", "i"));
        keypadMap.put("5", List.of("j", "k", "l"));
        keypadMap.put("6", List.of("m", "n", "o"));
        keypadMap.put("7", List.of("p", "q", "r", "s"));
        keypadMap.put("8", List.of("t", "u", "v"));
        keypadMap.put("9", List.of("w", "x", "y", "z"));

        String[] digitArr = digits.split("");
        StringBuilder sb = new StringBuilder("");
        recursiveFunction(0, digitArr, sb);

        return l;
    }

    public static void recursiveFunction(int index, String[] digitArr, StringBuilder sb) {
        if (index == digitArr.length - 1) {
            l.add(String.valueOf(sb));
        }

        for (int i = 0; i < digitArr.length; i++) {
//            sb.append(keypadMap.get());
            recursiveFunction(0, digitArr, sb);
        }
    }

    public static boolean isValid(String s) {
        List<String> l = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                l.add(String.valueOf(s.charAt(i)));
                index = l.indexOf(String.valueOf(s.charAt(i)));
                index++;
            } else if (s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')') {
                String str = String.valueOf(s.charAt(i));
                if (s.charAt(i) == '}' && l.contains("{")) {
                    if (l.lastIndexOf("{") == l.size() - 1)
                        l.remove(l.lastIndexOf("{"));
                } else if (s.charAt(i) == '}' && !l.contains("{"))
                    return false;
                if (s.charAt(i) == ']' && l.contains("[")) {
                    if (l.lastIndexOf("[") == l.size() - 1)
                        l.remove(l.lastIndexOf("["));
                } else if (s.charAt(i) == ']' && !l.contains("["))
                    return false;
                if (s.charAt(i) == ')' && l.contains("(")) {
                    if (l.lastIndexOf("(") == l.size() - 1)
                        l.remove(l.lastIndexOf("("));
                } else if (s.charAt(i) == ')' && !l.contains("("))
                    return false;
            }

        }
        if (l.size() > 0)
            return false;
        else
            return true;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void print(ListNode l) {
//               int size=0;
        if (l != null)
            System.out.print(l.val);
        System.out.print(" ");
        while (l.next != null) {
            l = l.next;
            System.out.print(l.val);
            System.out.print(" ");

        }
//        if (l != null)
//            System.out.print(l.val);
        System.out.println("");
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 != null)
            return list2;
        else if (list1 != null && list2 == null)
            return list1;
        else if (list1 == null && list2 == null)
            return null;

        ListNode l = new ListNode();
        ListNode l2 = l;
        int flag=0;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                l.val = list1.val;
                if (list1.next != null){
                    list1 = list1.next;
                }else {
                    flag=1;
                    break;
                };
                l.next = new ListNode();
                l = l.next;
            } else {
                l.val = list2.val;
                if (list2.next != null){
                    list2 = list2.next;
                }else {
                    flag=2;
                    break;
                };
                l.next = new ListNode();
                l = l.next;
            }
        }

        while (list1 != null && flag!=1) {
            l.next = new ListNode(list1.val);
            if (list1.next != null){
                list1 = list1.next;
                l = l.next;
            }else break;

        }
        while (list2 != null && flag!=2) {
            l.next = new ListNode(list2.val);
            if (list2.next != null){
                list2 = list2.next;
                l = l.next;
            }else break;
        }


        return l2;
    }
    public static class TreeNode {
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
    static List<Integer> lis=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        readTree(root);
        return lis;
    }
    public static void readTree(TreeNode root){
        if(root==null)
            return;
        else{

                readTree(root.left);
            lis.add(root.val);
//            System.out.print(root.val +" ");
                readTree(root.right);
        }
    }

    static List<Integer> lis1=new ArrayList<>();
    static List<Integer> lis2=new ArrayList<>();
    static int leftNodes=0;
    static int rightNodes=0;
    static int leftNodes2=0;
    static int rightNodes2=0;
    public static void readTree1(TreeNode root){
        if(root==null)
            return;
        else{
            readTree1(root.left);
            leftNodes++;
            lis1.add(root.val);
            readTree1(root.right);
            rightNodes++;
        }
    }
    public static void readTree2(TreeNode root){
        if(root==null)
            return;
        else{
            readTree2(root.right);
            leftNodes2++;
            lis2.add(root.val);
            readTree2(root.left);
            rightNodes2++;
        }
    }
    public static boolean isSymmetric(TreeNode root) {
        if(root.left==null && root.right==null)
            return true;
        readTree1(root.left);
        readTree2(root.right);
        System.out.println(lis1);
        System.out.println(lis2);
        if(lis1.equals(lis2) && leftNodes==leftNodes2 && rightNodes==rightNodes2)
            return true;
        else
            return false;
    }
    public int[] buildArray(int[] nums) {
        int[] ans=new int[nums.length];
        for(int i=0;i< nums.length;i++){
            ans[i]=nums[nums[i]];
        }
        return ans;
    }
    public int[] getConcatenation(int[] nums) {
        int[] ans=new int[nums.length];
        for(int i=0;i< nums.length;i++){
            ans[i]=nums[i];
        }
        for(int i=nums.length;i< nums.length*2;i++){
            ans[i]=nums[i-nums.length];
        }
        return ans;
    }
    static void printPermutn(String str, String ans)
    {

        // If string is empty
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            // Recursive call
            printPermutn(ros, ans + ch);
        }
    }
//    public int distributeCandies1(int n, int limit) {
//
//        int[] arr=new int[3];
//        List<Integer> l= new ArrayList<>();
//        for(int i=0;i<3;i++){
//            if(limit<n)
//                arr[0]=limit;
//
//        }
//    }
    public static int distributeCandies2(int n, int limit) {
        int result = 0;

        for (int i = 0; i <= limit && i <= n; i++) {
            for (int j = 0; j <= limit && i + j <= n; j++) {
                if(i>n)
                    continue;
                if(i+j>n)
                    continue;
                int k = n - i - j;

                if (k <= limit) {
                    result++;
                }
            }
        }

        return result;
    }
    public static int distributeCandies3(int n, int limit) {
        int result = 0;

        for (int i = 0; i <= limit ; i++) {
            for (int j = 0; j <= limit ; j++) {
                if(i>n)
                    continue;
                if(i+j>n)
                    continue;
                int k = n - i - j;

                if (k <= limit) {
                    result++;
                }
            }
        }

        return result;
    }
    public static int distributeCandies(int n, int limit) {
        // Calculate the total ways using combinations formula
        long totalWays = nCr(n + 2, 2);

        // Subtract the invalid ways where any child gets more than the limit
        for (int i = 1; i <= limit; i++) {
            totalWays -= 3 * nCr(n - i + 2, 2);
        }

        return (int) totalWays;
    }
    public static int distributeCandies4(int n, int limit) {


        long maxCombinaations = 1;

        for (int i = 1; i <= 2; i++) {
            maxCombinaations = maxCombinaations * (n + 2 - i + 1) / i;
        }

        for (int i = 1; i <= limit; i++) {
            maxCombinaations = maxCombinaations- 3 * nCr(n - i + 2, 2);
        }

        return (int) maxCombinaations;
    }

    // Calculate combinations (n choose r)
    private static long nCr(int n, int r) {
        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }


        return result;
    }


    public int findSpecialInteger(int[] arr) {
        Map<Integer, Integer> counter=new TreeMap<>();
        for(int a:arr){
            if(counter.containsKey(a))
                counter.put(a,counter.get(a)+1);
            else
                counter.put(a,1);
        }
        int pivot=arr.length/4;

        for(int a:arr){
            if(counter.get(a)>pivot)
                return a;
        }
        return 0;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=m;i<(m+n);i++){
            nums1[i]=nums2[i-m];
        }
        Arrays.sort(nums1);

    }
    public int removeElement(int[] nums, int val) {

        int length=nums.length;
        for(int i=0;i< length;i++){
            if(nums[i]==val){
                for(int j=i+1;j<length;j++){
                    nums[j-1]=nums[j];
                }
                nums[length-1]=val;
                i=i-1;
                length=length-1;
            }
        }
        return length;
    }
    public int removeDuplicates(int[] nums) {

        int length=nums.length;
        for(int i=0;i< length;i++){
            if(i+1<nums.length && nums[i]==nums[i+1]){
                for(int j=i+1;j<length;j++){
                    nums[j-1]=nums[j];
                }
                i=i-1;
                length=length-1;
            }
        }
        return length+1;
    }
    public int remove2Duplicates(int[] nums) {
        int length=nums.length;
        for(int i=0;i< length;i++){
            if(i+1<length && i+2<length && nums[i]==nums[i+1] && nums[i]==nums[i+2]){
                for(int j=i+3;j<length;j++){
                    nums[j-1]=nums[j];
                }
                i=i-1;
                length=length-1;
            }
        }
        return length;
    }
    public int maxProduct(int[] nums) {
        int max=0;
        for(int i=0;i< nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int product=(nums[i]-1)*(nums[j]-1);
                if(product>max)
                    max=product;
            }
        }
        return max;
    }
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> m=new HashMap<>();
        for(int i:nums){
            if(m.containsKey(i)){
                m.put(i,m.get(i)+1);
            }else {
                m.put(i,1);
            }
        }
        int counter= nums.length/2;
        for(int i:m.keySet()){
            if(m.get(i)>counter)
                return i;
        }
        return 0;
    }
    public void rotate2(int[] nums, int k) {

        while(k>0){
            int lastElement=nums[nums.length-1];
            for(int i=nums.length-2;i>=0 ;i--){
                nums[i+1]=nums[i];
            }
            nums[0]=lastElement;
            k--;
        }
    }
    public void rotate(int[] arr, int k) {
        int n = arr.length;
        k = k%n;
        System.out.println(k);
        if(n <= k || k == 0) {
            return;
        }

        reverseNum(arr, 0, n-1);

        reverseNum(arr, 0, k-1);
        reverseNum(arr, k, n-1);
    }

    private void reverse(int[] arr, int i, int j) {
        while(i<j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
            i++;
            j--;
        }
    }
    public void reverseNum(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }}
    public int maxProfit(int[] prices) {
        int profit=0;
        int leastValueTillNow=Integer.MAX_VALUE;
        int todaysProfit=0;

        for(int i=0;i<prices.length;i++){
            if(prices[i]<leastValueTillNow)
                leastValueTillNow=prices[i];

            todaysProfit=prices[i]-leastValueTillNow;
            if(todaysProfit>profit)
                profit=todaysProfit;


        }
        return profit;
    }
//    122. Best Time to Buy and Sell Stock II
//Medium
//12.9K
//2.6K
//Companies
//You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
//On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
//
//Find and return the maximum profit you can achieve.
//
//
//
//Example 1:
//
//Input: prices = [7,1,5,3,6,4]
//Output: 7
//Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//Total profit is 4 + 3 = 7.
//Example 2:
//
//Input: prices = [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//Total profit is 4.
//Example 3:
//
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
    public int maxProfit2(int[] prices) {
        int profit=0;
        int leastValueTillNow=Integer.MAX_VALUE;
        int todaysProfit=0;

        for(int i=0;i<prices.length;i++){
            if(prices[i]<leastValueTillNow)
                leastValueTillNow=prices[i];

            todaysProfit=prices[i]-leastValueTillNow;
            if(todaysProfit>profit)
                profit=todaysProfit;


        }
        return profit;
    }
    public int numSpecial(int[][] mat) {
        int counter=0;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j]==1 && check(mat,i,j))
                    counter++;
            }
        }
        return counter;
    }
    public boolean check(int[][] mat,int i, int j){
        for(int k=0;k<mat[i].length;k++){
            if(mat[i][k]==1 && k!=j)
                return false;
        }
        for(int l=0;l<mat.length;l++){
            if(mat[l][j]==1 && l!=i)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        int result1 = distributeCandies3(5, 2);
//        System.out.println("Output 1: " + result1);
//        int result2 = distributeCandies3(3, 3);
//        System.out.println("Output 2: " + result2);
//        System.out.println(letterCombinations("23"));
//        System.out.println(isValid("[([]])"));
//        ListNode l = new ListNode(1, new ListNode(2, new ListNode(3)));
//        ListNode l2 = new ListNode(4, new ListNode(5, new ListNode(6,new ListNode(7))));
//        print(l);
//        print(l2);
//        print(mergeTwoLists(l, l2));
//        readTree(new TreeNode(1, new TreeNode(), new TreeNode(2,new TreeNode(3),new TreeNode())) );
//        System.out.println(lis);
//        System.out.println(isSymmetric(new TreeNode(1,
//                new TreeNode(2,new TreeNode(2),null),
//                new TreeNode(2,new TreeNode(2),null) )));
//        //it doesnt work for [1,2,2,2,null,2] when left and right are not mirror image but have same values
        Solution s =new Solution();
        int[] a=new int[]{1,2,3,4,5,6,7};
//        int length=s.remove2Duplicates(a);
//        System.out.println(length);
//        for(int i=0;i< length;i++){
//            System.out.print(a[i]+" ");
//        }
        s.rotate(a, 3);
        System.out.println();
        for(int i=0;i< a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
}