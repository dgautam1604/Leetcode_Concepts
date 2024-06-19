package com.example.roughwork.leetcode;

import java.util.Arrays;

public class solution2 {
    public boolean canJump3(int[] nums) {
        int index=0;
        for(int i=0;i< nums.length;i++){
            for(int j=0;j< i;j++){
                index=i+j;
                if(index== nums.length-1){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canJump(int[] nums) {
        boolean[] numsdp=new boolean[nums.length];
        Arrays.fill(numsdp,false);
        return canJumpRecursive(nums,0, numsdp);
    }
    public boolean canJumpRecursive(int[] nums, int idx, boolean[] numsdp) {
        if(idx== nums.length-1){
            return true;
        }
        if(nums[idx]== 0){
            return false;
        }
        if(numsdp[idx] != false)
            return numsdp[idx];

        int maxReach=idx+nums[idx]; //now minimum reach is 1 and max reach is this
        for(int jump=idx+1;jump<= maxReach;jump++){
            if(jump<nums.length && canJumpRecursive(nums,jump,numsdp))
                return numsdp[idx] = true;
        }
        return numsdp[idx]=false;
    }
    public boolean canJump2(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
}
//INTERVIEW-SCENARIO(recursion, memoization, dp, greedy)
//user2085X
//Sep LeetCoding Challenge
//362
//15550
//Aug 03, 2022
//Dynamic Programming
//Greedy
//Recursion
//Memoization
//Suppose you get this question during an interview, let's do a walk-through.
//Interviewer : Quotes the question...
//
//let's get started
//Ask clarifying questions, examples, and scenarios. The more you ask, interviewer gets more and more intrigued, for good.
//
//umm, at every index starting from the 0th, you can make jumps ranging from 1 till nums[index]. Greedily making a choice of jump won't work in subtle cases, for example, if from 0th index I take a jump to the index with maximum jump value, it might lead me to a postion with value of jumps equal to 0, and I'll be stuck.
//As I've got a lot of options to make a jump, I'd love to the explore the recurring idea behind this question(i.e. I can make a jump ranging from index(idx) to postions ranging from : (idx + 1 till idx + nums[idx]), and the same thing can be done from all these indexes.
//If by following any route I reach last index, I'll return true. Otherwise if none of the index can lead me to last index, I'll return a false.
//
//While solving the problem, think out loud and let the interviewer know what you are thinking.
//recursion approach :
//tc : O(N^N) ~ exponential
//sc : O(N)
//
//class Solution {
//public:
//    bool canJump(vector<int>& nums) {
//        return create(nums, 0);
//    }
//private:
//    bool create(vector<int>& nums, int idx) {
//        if(idx == nums.size() -1) return true;  //if I reach the last index, I should return true;
//        if(nums[idx] == 0) return false; //if at any  point I reach an index with jump value = 0
//		//,I'll get stuck and hence will return a false.
//
//        int reach = idx + nums[idx]; //the max jump that I can make
//		//I can make jumps ranging from idx + 1, till reach, and hence will run a loop
//		//to cover all those possbile jumps
//        for(int jump=idx + 1; jump <= reach; jump++) {
//		//if true, it means taking this jump led me to the last index.
//            if(jump < nums.size() && create(nums, jump))
//                return  true;
//        }
//
//		//if I reach  here,  it means none of the jumps led  me to the  last index
//		//and hence returning, false.
//        return  false;
//    }
//};
//There's no chance that this question will not give a T.L.E, it's exponential, it'll have many overlapping subproblems, and hence I can memoize it using a 1d dp array.
//
//memoized :
//tc : O(N* N) -> for each index, I can have at max N jumps, hence O(N* N).
//sc : O(N) + O(N) -> stack space plus dp array size.
//
//class Solution {
//public:
//    bool canJump(vector<int>& nums) {
//        vector<int> dp(nums.size(), -1);
//        return create(nums, 0, dp);
//    }
//private:
//    bool create(vector<int>& nums, int idx, vector<int>& dp) {
//        if(idx == nums.size() -1) return true;
//        if(nums[idx] == 0) return false;
//
//        if(dp[idx] != -1) return dp[idx]; //overlapping subproblems
//        int reach = idx + nums[idx];
//        for(int jump=idx + 1; jump <= reach; jump++) {
//            if(jump < nums.size() && create(nums, jump, dp))
//                return dp[idx] = true; //memoizing for particular index.
//        }
//
//        return dp[idx] = false; //memoizing for particular index.
//    }
//};
//I'm not a huge fan of that extra stack space, let me just convert this whole soution into a dp(tabulation) solution, so that extra O(N) space can be chucked out.
//
//tc : O(N* N)
//sc : O(N) -> dp array size
//
//class Solution {
//public:
//    bool canJump(vector<int>& nums) {
//        int n = nums.size();
//        vector<int> dp(n, -1);
//        dp[n-1] = 1; //base case;
//
//        for(int idx = n-2; idx >= 0; idx--) {
//            if(nums[idx] == 0) {
//                dp[idx] = false;
//                continue;
//            }
//
//            int flag = 0;
//            int reach = idx + nums[idx];
//            for(int jump=idx + 1; jump <= reach; jump++) {
//                if(jump < nums.size() && dp[jump]) {
//                    dp[idx] = true;
//                    flag = 1;
//                    break;
//                }
//            }
//            if(flag == 1)
//                continue;
//
//            dp[idx] = false;
//
//        }
//        return dp[0];
//    }
//
//};
//Bonus, I can also think of a solution that kinda resembles Kadane's algorithm. For every index, I'm checking the max reach I can have till that element, if that reach is less than the value of my index, that means I can never reach this particular index and my answer should be false.
//
//tc : O(N)
//sc : O(1)
//
//The comments that I've made show my entire thought process that I'd be delivering to the interviewer.
//
//class Solution {
//public:
//    bool canJump(vector<int>& nums) {
//        //it shows at max what index can I reach.
//        //initially I can only reach index 0, hence reach = 0
//        int reach = 0;
//
//        for(int idx = 0; idx < nums.size(); idx++) {
//            //at every index I'll check if my reach was atleast able to
//            //reach that particular index.
//
//            //reach >= idx -> great, carry on. Otherwise,
//            if(reach < idx) return false;
//
//            //now as you can reach this index, it's time to update your reach
//            //as at every index, you're getting a new jump length.
//            reach = max(reach, idx + nums[idx]);
//        }
//
//        //this means that you reached till the end of the array, wohooo!!
//        return true;
//
//    }
//};
//Thank you, if you enjoyed this approach of writing soutions do give me an upvote, and please feel free to add suggestions. Viva - La - Vida.