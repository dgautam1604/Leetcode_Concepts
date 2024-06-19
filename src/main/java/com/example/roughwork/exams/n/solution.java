package com.example.roughwork.exams.n;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class solution {
    public static int solution(int[] D, String[] T) {
        List<Integer> P=new ArrayList<>();
        List<Integer> G=new ArrayList<>();
        List<Integer> M=new ArrayList<>();
        List<Integer> TimeTaken=new ArrayList<>();
        if(D.length>0)
            TimeTaken.add(D[0]);
        for(int i=1;i<D.length;i++){
            TimeTaken.add(D[i]+TimeTaken.get(i-1));
        }
        // Implement your solution here
        for(int i=0;i<D.length;i++){
            HashMap<String,Integer> h=new HashMap<>(3);
            h.put("P",0);
            h.put("G",0);
            h.put("M",0);

            for(int j=0;j<T[i].length();j++){
                h.put(T[i].substring(j,j+1),h.get(T[i].substring(j,j+1))+1);
            }

            int totalTimeForP= h.get("P");
            int totalTimeForG= h.get("G");
            int totalTimeForM= h.get("M");

            if(h.get("P")!=0) P.add(totalTimeForP);
            else P.add(0);
            if(h.get("G")!=0) G.add(totalTimeForG);
            else G.add(0);
            if(h.get("M")!=0) M.add(totalTimeForM);
            else M.add(0);
        }
        int sumOfP=0;
        int sumOfG=0;
        int sumOfM=0;
        for(int i = 0; i < P.size(); i++){
            sumOfP=sumOfP+P.get(i);
            sumOfG=sumOfG+G.get(i);
            sumOfM=sumOfM+M.get(i);
        }
        for(int j=P.size()-1;j>0;j--){
            if(P.get(j)!=0) {
                sumOfP = sumOfP + TimeTaken.get(j) * 2;
                break;
            }
        }
        for(int j=G.size()-1;j>0;j--){
            if(G.get(j)!=0) {
                sumOfG = sumOfG + TimeTaken.get(j) * 2;
                break;
            }
        }
        for(int j=M.size()-1;j>0;j--){
            if(M.get(j)!=0) {
                sumOfM = sumOfM + TimeTaken.get(j) * 2;
                break;
            }
        }
//        System.out.println(sumOfP);
//        System.out.println(sumOfG);
//        System.out.println(sumOfM);
        return Math.max(Math.max(sumOfP,sumOfG),Math.max(sumOfG,sumOfM));
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,2,4},new String[]{"MPM","","G"}));
        System.out.println(solution(new int[]{2,1,1,1,2},new String[]{"","PP","PP","GM",""}));
        // 1. Empty Arrays
        System.out.println(solution(new int[]{}, new String[]{}));

        // 2. Empty Tasks and Durations
        System.out.println(solution(new int[]{0, 0, 0}, new String[]{"", "", ""}));

        // 3. Maximum Values
        System.out.println(solution(new int[]{100000, 100}, new String[]{"PPPGMG", "", "PPPPM"}));

        // 4. Single Task with Maximum Duration
        System.out.println(solution(new int[]{100}, new String[]{"PPGGGG"}));

        // 5. Single Task with Minimum Duration
        System.out.println(solution(new int[]{0}, new String[]{"G"}));

        // 6. Equal Durations for All Tasks
        System.out.println(solution(new int[]{5, 5, 5}, new String[]{"PGM", "PG", "GM"}));

        // 7. Alternate Empty and Non-Empty Tasks
        System.out.println(solution(new int[]{2, 3, 1, 4}, new String[]{"", "PG", "", "GM"}));

        // 8. Random Durations and Tasks
        System.out.println(solution(new int[]{3, 2, 4}, new String[]{"MPM", "", "G"}));

        // 9. All Tasks Empty
        System.out.println(solution(new int[]{1, 2, 3}, new String[]{"", "", ""}));

        // 10. All Tasks Non-Empty, Minimum Durations
        System.out.println(solution(new int[]{0, 0, 0}, new String[]{"P", "G", "M"}));

    }
}
