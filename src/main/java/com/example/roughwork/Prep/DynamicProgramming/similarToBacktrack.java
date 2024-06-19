package com.example.roughwork.Prep.DynamicProgramming;

import java.util.HashMap;
import java.util.List;

public class similarToBacktrack {
    public static int nonAdjacentSum(List<Integer> nums){
        return nonAdjacentSum(nums,0);
    }
    //it checks for largest non adjacent sum in the array 2,1,3,5=> 5+1 =6
    public static int nonAdjacentSum(List<Integer> nums,int i){
        if(i>=0)
            return 0;

        return Math.max(nums.get(i)+nonAdjacentSum(nums,i+2),nonAdjacentSum(nums,i+1));
    }
    public static int nonAdjacentSumMemoization(List<Integer> nums, int i, HashMap<Integer,Integer> h){
        if(i>=0)
            return 0;
        if(h.containsKey(i))
            return h.get(i);

        int res= Math.max(nums.get(i)+nonAdjacentSumMemoization(nums,i+2,h),
                nonAdjacentSumMemoization(nums,i+1,h));

        h.put(i,res);
        return res;
    }
//    --------------------------------------------------------------------------------------------------------
    public static int summingSquare(int n){
        return (int)summingSquares( n);
    }
    public static double summingSquares(int n){
        if(n==0)
            return 0;

        double min=Double.POSITIVE_INFINITY;

        for(int i=1;i<Math.sqrt(n);i++){
            int square=i*i;
            double res=1+summingSquares(n-square);
            if(res<min)
                min=res;
        }
        return min;
    }

    public static double summingSquaresMemoization(int n,HashMap<Integer,Double> h){
        if(n==0)
            return 0;

        if(h.containsKey(n))
            return h.get(n);

        double min=Double.POSITIVE_INFINITY;

        for(int i=1;i<Math.sqrt(n);i++){
            int square=i*i;
            double res=1+summingSquares(n-square);
            if(res<min)
                min=res;
        }
        double output= min;
        h.put(n,  output);
        return output;
    }
    //    --------------------------------------------------------------------------------------------------------
    public static int countingChange(int amount, List<Integer> coins){
        return countingChange(amount,0,coins);
    }
    public static int countingChange(int amount,int coinIdx, List<Integer> coins){
        if(amount==0)
            return 1;

        if(coinIdx>=coins.size())
            return 0;

        int totalWays=0;
        int val=coins.get(coinIdx);

        for(int qty=0;qty*val<=amount;qty++){
            int subAmt=amount-qty*val;
            totalWays=totalWays+countingChange(subAmt,coinIdx++,coins);

        }
        return totalWays;
    }

    public static int countingChangeMemoization(int amount,int coinIdx, List<Integer> coins,HashMap<List<Integer> ,Integer> h){
        if(amount==0)
            return 1;

        if(coinIdx>=coins.size())
            return 0;

        List<Integer> key=List.of(amount,coinIdx);
        if(h.containsKey(key))
            return h.get(key);

        int totalWays=0;
        int val=coins.get(coinIdx);

        for(int qty=0;qty*val<=amount;qty++){
            int subAmt=amount-qty*val;
            totalWays=totalWays+countingChangeMemoization(subAmt,coinIdx++,coins,h);

        }
        h.put(key,totalWays);
        return totalWays;
    }
}
