package com.example.roughwork.Prep.SlidingWindow;

public class Sample {
    public static void main(String[] args) {
        System.out.println();
        int a=5,b=7;
        System.out.println("a&b = " + (a & b));

        // bitwise or
        // 0101 | 0111=0111 = 7
        System.out.println("a|b = " + (a | b));

        // bitwise xor
        // 0101 ^ 0111=0010 = 2
        System.out.println("a^b = " + (a ^ b));

        // bitwise not
        // ~00000000 00000000 00000000 00000101=11111111 11111111 11111111 11111010
        // will give 2's complement (32 bit) of 5 = -6
        System.out.println("~a = " + ~a);

        // can also be combined with
        // assignment operator to provide shorthand
        // assignment
        // a=a&b
        a &= b;
        System.out.println("a= " + a);
    }
    public int maximumStrongPairXor(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
//                if((nums[i]^nums[j])>res && Math.abs(nums[i]-nums[j])<=Math.min(nums[i],nums[j]))res=Math.max(res, nums[i]^nums[j]);
//            The above has a better time because it first checks if they are better and then check the condition
                if(Math.abs(nums[i]-nums[j])<=Math.min(nums[i],nums[j]))
                    res=Math.max(res,nums[i]^nums[j]);
            }
        }
        return res;
    }
}
