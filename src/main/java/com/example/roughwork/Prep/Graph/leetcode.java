package com.example.roughwork.Prep.Graph;

import java.util.*;

public class leetcode {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

//    1615. Maximal Network Rank
//    Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
//Output: 4
//Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1.
// The road between 0 and 1 is only counted once.
//    public int maximalNetworkRank(int n, int[][] roads) {
//        HashMap<Integer, List<Integer>> h=buildGraph(roads);
//        HashMap<Integer, Integer> a=new HashMap<>();
//        for(Integer i:h){
//            for()
//        }
//    }
public int maximalNetworkRank(int n, int[][] roads) {
    int[][] mat=new int[n][n];
    int[] count=new int[n];

    for(int[] pair:roads){
        mat[pair[0]][pair[1]]=1;
        mat[pair[1]][pair[0]]=1;
        ++count[pair[0]];
        ++count[pair[1]];
    }

    int ans=0;
    for(int i=0;i<count.length;i++){
        for(int j=i+1;j<count.length;j++){
            ans=Math.max(ans, count[i]+count[j]-mat[i][j]);
        }
    }

    return ans;
}


    public static HashMap<Integer, List<Integer>> buildGraph(int[][] roads){
        HashMap<Integer, List<Integer>> h=new HashMap<>();
        for(int[] i:roads ){
            if(!h.containsKey(i[0])) {
                List<Integer> l=new ArrayList<>();
                l.add((i[1]));
                h.put(i[0], l);
            }
            else{
                List<Integer> l=h.get(i[0]);
                l.add((i[1]));
                h.put(i[0], l);
            }
            if(!h.containsKey(i[1])) {
                List<Integer> l=new ArrayList<>();
                l.add((i[0]));
                h.put(i[1], l);
            }
            else{
                List<Integer> l=h.get(i[1]);
                l.add((i[0]));
                h.put(i[1], l);
            }
        }
        return h;
    }

    public static void main(String[] args) {
        int[][] n={{0,1},{0,3},{1,2},{1,3}};
        System.out.println(buildGraph(n));
    }
}
