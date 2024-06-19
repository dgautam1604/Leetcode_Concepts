package com.example.roughwork.Prep.Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class backtrack {
    public static List<List<Integer>> subsets(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> l=new ArrayList<>();
//        backtracker(l,new ArrayList<>(),nums,0);
        backtrackerPermutationWithDuplicates(l,new ArrayList<>(),nums,new boolean[nums.length]);
        return l;
    }
    public static void backtracker(List<List<Integer>> l, List<Integer> temp, int[] nums, int start){
        if(temp.size()==nums.length)
            l.add(new ArrayList<>(temp));
//        System.out.println(temp);
    for (int i=start;i<nums.length;i++){
//        if(i>start && nums[i]==nums[i-1])
//            continue;
        temp.add(nums[i]);
        backtracker(l,temp,nums,i+1);
        temp.remove(temp.size()-1);
     }
    }
    public static void backtrackerPermutation(List<List<Integer>> l, List<Integer> temp, int[] nums){
        if(temp.size()==nums.length)
            l.add(new ArrayList<>(temp));
//        System.out.println(temp);
        else {
            for (int i = 0; i < nums.length; i++) {
                if(temp.contains(nums[i]))
                    continue;
                temp.add(nums[i]);
                backtrackerPermutation(l, temp, nums);
                temp.remove(temp.size() - 1);
            }
        }
    }
    public static void backtrackerPermutationWithDuplicates(List<List<Integer>> l, List<Integer> temp, int[] nums,boolean[] used){
        if(temp.size()==nums.length)
            l.add(new ArrayList<>(temp));
//        System.out.println(temp);
        else {
            for (int i = 0; i < nums.length; i++) {
                if(used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1])
                    continue;
                used[i]=true;
                temp.add(nums[i]);
                backtrackerPermutationWithDuplicates(l, temp, nums,used);
                used[i]=false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums={1,2,2};
        System.out.println(subsets(nums));
    }
}
