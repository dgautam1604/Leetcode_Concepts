package com.example.roughwork.leetcode;

import java.util.Arrays;

public class solution1367 {
    public static int maxWidthOfVerticalArea(int[][] points) {
        int[] arr=new int[points.length];
        int counter=0;
        for(int[] p:points){
            arr[counter++]=p[0];
        }
        Arrays.sort(arr);
        int diff=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]-arr[i-1]>diff)
                diff=arr[i]-arr[i-1];
        }
        return diff;
    }

    public static void main(String[] args) {
//        Solution s =new Solution();
        int[][] a=new int[][]{{3,1},{9,0},{1,0},{1,4},{5,3},{8,8}};
//        s.ma
        System.out.println(maxWidthOfVerticalArea(a));
    }
}
