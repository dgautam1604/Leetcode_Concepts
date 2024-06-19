package com.example.roughwork.leetcode;

import com.example.roughwork.Extras.Solution;

import java.util.HashMap;
import java.util.Map;

public class solution {
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
//    both maxprofit 2, 3 and 4 are the solution
    public int maxProfit2(int[] prices) {
        int profit=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }

    public int maxProfit3(int[] prices) {

        // It is impossible to sell stock on first day,
        // set -infinity as initial value for current_hold
        int hold = -Integer.MAX_VALUE;
        int notHold = 0;

        for( int stockPrice : prices ){

            int prevHold = hold;
            int prevNotHold = notHold;

            // either keep hold, or buy in stock today at stock price
            hold = Math.max(prevHold, prevNotHold - stockPrice);

            // either keep not-hold, or sell out stock today at stock price
            notHold = Math.max(prevNotHold, prevHold + stockPrice);

        }

        // maximum profit must be in not-hold state
        return notHold;

    }

    public int maxProfit4(int[] prices) {
        int i = 0, buy, sell, profit = 0, N = prices.length - 1;
        while (i < N) {
            while (i < N && prices[i + 1] <= prices[i]) i++;
            buy = prices[i];

            while (i < N && prices[i + 1] > prices[i]) i++;
            sell = prices[i];

            profit += sell - buy;
        }
        return profit;
    }
//    -----------------------------------------------------------------------
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
    public int[][] onesMinusZeros(int[][] grid) {
        int[][] grid2=new int[grid.length][grid[0].length];
        for(int i=0;i< grid.length;i++){
            for(int j=0;j< grid[i].length;j++){
                grid2[i][j]=value(grid,i,j);
            }
        }
        return grid2;
    }
    public Map<Integer,Integer> onesRowiMap=new HashMap<>();
    public Map<Integer,Integer> zerosRowiMap=new HashMap<>();
    public Map<Integer,Integer> onesColjMap=new HashMap<>();
    public Map<Integer,Integer> zerosColjMap=new HashMap<>();
    public int value(int[][] grid, int i,int j){
        int onesRowi=0;
        int zerosRowi=0;
        int onesColj=0;
        int zerosColj=0;
        if(onesRowiMap.containsKey(i)){
            onesRowi=onesRowiMap.get(i);
            zerosRowi=zerosRowiMap.get(i);

        }else {
            for (int k = 0; k < grid[i].length; k++) {
                if (grid[i][k] == 1)
                    onesRowi++;
                else if (grid[i][k] == 0)
                    zerosRowi++;

            }
            onesRowiMap.put(i,onesRowi);
            zerosRowiMap.put(i,zerosRowi);
        }

        if(onesColjMap.containsKey(j)){
            onesColj=onesColjMap.get(j);
            zerosColj=zerosColjMap.get(j);
        }else {
            for (int l = 0; l < grid.length; l++) {
                if (grid[l][j] == 1)
                    onesColj++;
                else if (grid[l][j] == 0)
                    zerosColj++;

            }
            onesColjMap.put(j,onesColj);
            zerosColjMap.put(j,zerosColj);
        }
        int diff = onesRowi + onesColj - zerosRowi - zerosColj;
        return diff;
    }
//    diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj

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
