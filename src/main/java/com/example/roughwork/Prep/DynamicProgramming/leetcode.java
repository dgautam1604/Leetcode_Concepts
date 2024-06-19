package com.example.roughwork.Prep.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode {
    public int trap(int[] h) {
        int l = 0;
        int r = h.length - 1;
        int lmax = Integer.MIN_VALUE;
        int rmax = Integer.MIN_VALUE;
        int ans = 0;
        while (l < r) {
            lmax = Math.max(lmax, h[l]);
            rmax = Math.max(rmax, h[r]);
            ans += (lmax < rmax) ? lmax - h[l++] : rmax - h[r--];
        }
        return ans;
    }
//5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); ++i) {
            dp[i][i] = true;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }

//    ---------better solution for palindrome-----------
int start = 0, end = 0;
    public String longestPalindrome1(String s) {
        computePalindrome(s,0);
        return s.substring(start, end+1);
    }
    private void computePalindrome(String s, int i) {
        int n = s.length();
        if(i>=n)
            return;
        int a = i, b = i;
        while ((b+1)<n && s.charAt(b) == s.charAt(b+1)) {
            b++;
        }
        i=b;
        while(a>0 && b<n-1 && s.charAt(a-1) == s.charAt(b+1)) {
            a--;
            b++;
        }
        if((end-start) < (b-a)) {
            start = a;
            end = b;
        }
        computePalindrome(s,i+1);
    }
    //--------------------------------------------------------------------------------------------
//    max sum subarray
    public int maxSubArrayWithKadane(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int start=0,end=0;
        int tempStart=0;

        for(int i=0;i<n;i++){
            sum += nums[i];
            if(sum > max){
                max = sum;
                start = tempStart;
                end = i;
            }
            if(sum < 0){
                sum = 0;
                tempStart = i+1;
            }
        }

        return max;
    }
    public int maxSubArrayWithDp(int[] nums) {
        int storage []=  new int[nums.length];
        int max  =  nums[0];
        storage[0] =  nums[0];
        for( int i=1;i<nums.length ;i++  ){
            storage[i] =  Math.max( storage[i-1]+nums[i] ,  nums[i]);
            if(storage[i]>max){
                max=  storage[i];
            }
        }
        return max;
    }
//--------------------------------------------------------------------------------------------------
    public List<String> generateParenthesis(int n) {
        List<String>[] dp = new List[n+1];
        dp[0] = Arrays.asList("");
        dp[1] = Arrays.asList("()");

        for(int i = 2; i < n+1; i++){
            dp[i] = new ArrayList<>();
            int inner = i-1;
            int outer = 0;

            while(inner >= 0 && outer <= i-1){
                for(String in : dp[inner]){
                    for(String out : dp[outer]){
                        StringBuilder sb = new StringBuilder();
                        sb.append("(");
                        sb.append(in);
                        sb.append(")");
                        sb.append(out);
                        dp[i].add(sb.toString());
                    }
                }
                inner--;
                outer++;
            }
        }
        return dp[n];
    }

    public static int robwithDP(int[] nums) {
        if(nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];

        int dp[]=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);

        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);

        }
        return dp[nums.length-1];
    }

    public int rob2(int[] nums) {
        int rob = 0; //max monney can get if rob current house
        int norob = 0; //max money can get if not rob current house

        for(int i=0; i<nums.length; i++) {
            int newRob = norob + nums[i];
            int newNoRob = Math.max(norob, rob);
            rob = newRob;
            norob = newNoRob;
        }
        return Math.max(rob, norob);
    }

    public static void main(String[] args) {
        int[] nums={1,1,1,2};
//        System.out.println(rob(nums));
    }
}
