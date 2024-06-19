package com.example.roughwork.Prep.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class dp {
    public static int fib(int n){
        if(n==0 || n==1)
            return n;
        int result=fib(n-1)+fib(n-2);
        return result;
    }
    public static int fibWithMemoization(int n, HashMap<Integer,Integer> h){
        if(n==0 || n==1)
            return n;
        if(h.containsKey(n))
            return h.get(n);
        int result=fibWithMemoization(n-1,h)+fibWithMemoization(n-2,h);
        h.put(n,result);
        return result;
    }
//---------------------------------------------------------------------------------------------------------------

    public static int tribonacci(int n){
        if(n==0 || n==1)
            return 0;
        if(n==2)
            return 1;
        int result=tribonacci(n-1)+tribonacci(n-2)+tribonacci(n-3);
        return result;
    }

    public static int tribWithMemoization(int n, HashMap<Integer,Integer> h){
        if(n==0 || n==1)
            return 0;
        if(n==2)
            return 1;
        if(h.containsKey(n))
            return h.get(n);
        int result=tribWithMemoization(n-1,h)+tribWithMemoization(n-2,h)+tribWithMemoization(n-3,h);
        h.put(n,result);
        return result;
    }
//---------------------------------------------------------------------------------------------------------------
//    To check if we can reach the amount using the numbers in the list. Sol: You keep reducing the value and checking if it leads to zero
    public static boolean sumPossible(int amount, List<Integer> numbers){
        if(amount==0)
            return true;
        if(amount<0)
            return false;
        for(int num:numbers){
            int subAmount=amount-num;
            if(sumPossible(subAmount,numbers))
                return true;
        }
        return false;
    }

    public static boolean sumPossibleMemoization(int amount, List<Integer> numbers, HashMap<Integer,Boolean> h){
        if(amount==0)
            return true;
        if(amount<0)
            return false;

        if(h.containsKey(amount))
            return h.get(amount);

        for(int num:numbers){
            int subAmount=amount-num;
            if(sumPossibleMemoization(subAmount,numbers,h)) {
                h.put(amount,true);
                return true;
            }
        }
        h.put(amount,false); //its always the final value that we need to save
        return false;
    }

//---------------------------------------------------------------------------------------------------------------
//    its similar to sumPossible but there we just checked if its possible to make up the amount using list values, here
//    we also check whats the minimum values we can add to make up to the sum amount
    public static int minChange(int amount, List<Integer> coins){
         if(amount==0)
             return 0;
        if(amount<0)
            return -1;

        int minCount=-1;
        for(int num:coins){
             int subAmount=amount-num;
             int res=minChange(subAmount,coins);

             if(res != -1) {
                 int currentCoinCount=res+1;
                 if (currentCoinCount < minCount || minCount == -1) {
                     minCount = currentCoinCount;
                 }
             }

         }

        return minCount;
    }

    public static int minChangeMemoization(int amount, List<Integer> coins,HashMap<Integer,Integer> h){
        if(amount==0)
            return 0;
        if(amount<0)
            return -1;

        if(h.containsKey(amount))
            return h.get(amount);

        int minCount=-1;
        for(int num:coins){
            int subAmount=amount-num;
            int res=minChange(subAmount,coins);

            if(res != -1) {
                int currentCoinCount=res+1;
                if (currentCoinCount < minCount || minCount == -1) {
                    minCount = currentCoinCount;
                }
            }

        }

        h.put(amount,minCount);
        return minCount;
    }

//---------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println(fib(6));
        System.out.println(fibWithMemoization(6,new HashMap<>()));
        //0 1 1 2 3 5 8
        List<Integer> l= Arrays.asList(new Integer[]{1,2,3});
        System.out.println(sumPossible(6,l));
        System.out.println(sumPossibleMemoization(7,l,new HashMap<>()));
    }
}
