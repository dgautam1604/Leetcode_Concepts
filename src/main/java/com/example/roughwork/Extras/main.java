package com.example.roughwork.Extras;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        int height=sc.nextInt();
//        int height=4;
//        for(int i=1;i<=height;i++){
//            for(int j=1;j<=i;j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//        for(int i=height-1;i>0;i--){
//            for(int j=1;j<=i;j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }
        int a = 5;
        int b = 2;

        // bitwise and
        // 0101 & 0111=0101 = 5
        System.out.println("a&b = " + (2 ^ 2));
        for(int j=1;j<=9;j++){
            int temp=j^3;
            System.out.println(temp);
        }

    }

//        public int[] findArray(int[] pref) {
//            int[] num=new int[pref.length];
//
//        }
public int[] findArray(int[] pref) {
        int x= Arrays.stream(pref).max().getAsInt();;

    int[] num=new int[pref.length];
    for(int i=0;i<pref.length;i++){
        int n=pref[i];
        for(int j=1;j<=x;j++){
            if(i==0)
                num[i]=n;
            else if(i>0) {
                int temp = j ^ pref[i - 1];
                if (temp == pref[i])
                    num[i] = j;
            }
        }

    }
    return num;
}

}
