package com.example.roughwork.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ssol {
    public static int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        arr1.add(nums[0]);
        arr2.add(nums[1]);

        for (int i = 1; i < n-1; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i+1]);
            } else {
                arr2.add(nums[i+1]);
            }
        }

        int[] result = new int[n];

        for (int i = 0; i < arr1.size(); i++) {
            result[i] = arr1.get(i);
        }

        for (int i = 0; i < arr2.size(); i++) {
            result[arr1.size() + i] = arr2.get(i);
        }

//        for(int i:result){
//            System.out.println(i);
//        }
        return result;
    }
    static int greaterCount(int[] arr, int val){
        int count=0;
        for(int i:arr){
            if(i>val)
                count++;
        }
    return count;
    }
    public static int minimumLength(String s) {
        String str=s;

        int l=0;
        int r=str.length()-1;
        while(l<r ){
            l=0;
            r=str.length()-1;
            int n=str.length();
            if(r<=0)
                break;
            String x=str.substring(0,1);
            String y=str.substring(n-1);
            if(!x.equals(y))
                break;
//            if()
            for(int i=1;i<n-1;i++){
                if(x.equals(str.substring(i,i+1))){
                    l++;
                }else{
                    break;
                }
            }

            for(int i=n-2;i>0;i--){
                if(y.equals(str.substring(i,i+1))){
                    r--;
                }else{
                    break;
                }
            }
            if(l<r)
                str=str.substring(l+1,r);
            else
                str="";

        }
        System.out.println(str.length());
        return str.length();
    }



    public static void main(String[] args) {
//        resultArray(new int[]{2,1,3});
        minimumLength("bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb");
    }
}
