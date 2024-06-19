package com.example.roughwork.exams.amazon;

import java.util.*;

public class Sol {

    public static int max2(List<Integer> riceBags){
        Collections.sort(riceBags);
        int count=0;
        for(Integer y: riceBags){
            int num=y;
            int m=1;
            while(riceBags.contains(num*num)){
                m++;
                num=num*num;
            }
            if(m>count)
                count=m;
        }
        if(count>1)
            return count;
        else
            return -1;
    }

    public static void main(String[] args) {
        List<Integer> l=new ArrayList<>();
        l.addAll(Arrays.asList(3,9,4,2,16));
        System.out.println(max2(l));
    }
}
