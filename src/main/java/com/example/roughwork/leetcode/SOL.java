package com.example.roughwork.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SOL {
    static List<List<Integer>> res=new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        recursor(candidates,0, new ArrayList<>() ,0,target);
        return res;
    }
    public static void recursor(int[] candidates,int sum, List<Integer> tempList, int start,int target){
        if(sum==target){
            res.add(tempList);
            return;
        }
        if(start> candidates.length || sum>target){
            return ;
        }

        tempList.add(candidates[start]);
        recursor(candidates,sum+candidates[start], tempList,start,target);
        tempList.remove(candidates[start]);
        recursor(candidates,sum, tempList,start++,target);

    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7},7));
    }

}
