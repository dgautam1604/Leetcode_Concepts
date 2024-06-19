package com.example.roughwork.Prep.SlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question 713
//Given an array of integers nums and an integer k, return the number of
// contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

//        Example 1:
//
//        Input: nums = [10,5,2,6], k = 100
//        Output: 8
//        Explanation: The 8 subarrays that have product less than 100 are:
//        [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
//        Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

//        Example 2:
//
//        Input: nums = [1,2,3], k = 0
//        Output: 0
public class numSubarrayProductLessThanK {
    public static int numSubarrayProductLessThanK3(int[] nums, int k) {
        if (k <= 1)
            return 0;
        int count = 0;
        int product = 1;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k)
            {
                product /= nums[left];
                left += 1;
            }
                count += right - left + 1;

        }
        return count;
    }



    public static void main(String[] args) {
        int[] nums = {10,5,2,6};int k = 100;
        System.out.println(numSubarrayProductLessThanK3(nums,k));

    }
}
