package com.example.roughwork.Extras;

import java.util.ArrayList;
import java.util.List;

public class permutation {
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


    public static String swap(String a,
                              int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
    // Driver code
    public static void main(String[] args)
    {
//        String s = "abb";
//        printPermutn(s, "");
//        System.out.println();
//        String str = "ABB";
//        int n = str.length();
//        permute(str, 0, n-1);
        int[] nums={1,2,3};
        System.out.println(permute(nums));
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>  l=new ArrayList<>();
        backtrack(l, nums, 0);

        return l;
    }

    private static void backtrack(List<List<Integer>> l, int[] nums, int startIndex) {
        if(startIndex==nums.length){
            List<Integer> l1=new ArrayList<>();
            for(int i:nums)
                l1.add(i);
            l.add(l1);
        }else{
            for (int i=startIndex;i<nums.length;i++){
                swap(nums, i, startIndex);
                backtrack(l, nums, startIndex+1);
                swap(nums, i, startIndex);
            }
        }

    }
    public static void swap(int[] nums, int startIndex, int currentIndex){
        int temp=nums[startIndex];
        nums[startIndex]=nums[currentIndex];
        nums[currentIndex]=temp;
    }
}
