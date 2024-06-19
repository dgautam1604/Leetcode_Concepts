//package com.example.roughwork.exams.n;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class solution {
//    public static int solution(int[] D, String[] T) {
//        List<Integer> timeTaken = new ArrayList<>();
//        if (D.length > 0) {
//            timeTaken.add(D[0]);
//        }
//
//        for (int i = 1; i < D.length; i++) {
//            timeTaken.add(D[i] + timeTaken.get(i - 1));
//        }
//
//        List<Integer> totalTimeForP = new ArrayList<>();
//        List<Integer> totalTimeForG = new ArrayList<>();
//        List<Integer> totalTimeForM = new ArrayList<>();
//
//        for (int i = 0; i < D.length; i++) {
//            int countP = 0, countG = 0, countM = 0;
//
//            for (int j = 0; j < T[i].length(); j++) {
//                char type = T[i].charAt(j);
//                if (type == 'P') {
//                    countP++;
//                } else if (type == 'G') {
//                    countG++;
//                } else if (type == 'M') {
//                    countM++;
//                }
//            }
//
//            totalTimeForP.add(countP);
//            totalTimeForG.add(countG);
//            totalTimeForM.add(countM);
//        }
//
//        int sumOfP = calculateSum(timeTaken, totalTimeForP);
//        int sumOfG = calculateSum(timeTaken, totalTimeForG);
//        int sumOfM = calculateSum(timeTaken, totalTimeForM);
//
//        System.out.println("Sum of P: " + sumOfP);
//        System.out.println("Sum of G: " + sumOfG);
//        System.out.println("Sum of M: " + sumOfM);
//
//        return Math.max(Math.max(sumOfP, sumOfG), sumOfM);
//    }
//
//    private static int calculateSum(List<Integer> timeTaken, List<Integer> typeTotal) {
//        int sum = 0;
//        for (int j = typeTotal.size() - 1; j > 0; j--) {
//            if (typeTotal.get(j) != 0) {
//                sum = sum + timeTaken.get(j) * 2;
//                break;
//            }
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(solution(new int[]{3, 2, 4}, new String[]{"MPM", "", "G"}));
//        System.out.println(solution(new int[]{2, 1, 1, 1, 2}, new String[]{"", "PP", "PP", "GM", ""}));
//    }
//}
//
