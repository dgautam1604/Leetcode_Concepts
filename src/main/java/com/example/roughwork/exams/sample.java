package com.example.roughwork.exams;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Qualifier("ss")
public  class sample<K,V> {
    public void trip(K d){

    }
    public void trip2(){

    }

    public static void main(String[] args){
        int[] a={1,2,3,4,9,8,7,6};

        Integer[] valueToInteger=Arrays.stream(a).boxed().toArray(Integer[]::new);
        Integer[] valueToIntegerUsingIntStream= IntStream.of(a).boxed().toArray(Integer[]::new);
        Arrays.sort(a);

        int[] arr = { 13, 7, 6, 45, 21, 9, 101, 102 };
        Integer[] arrToInteger= Arrays.stream(arr).boxed().toArray(Integer[]::new);
        // Applying sort() method over to above array
        // by passing the array as an argument
        Arrays.sort(arr);

        List<Integer> toSortList = Arrays.stream(a).boxed().collect(Collectors.toList());
        Collections.sort(toSortList);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(55, "John");
        map.put(22, "Apple");
        map.put(66, "Earl");
        Collections.sort(toSortList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        ExecutorService e= Executors.newFixedThreadPool(5);
        e.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    System.out.println(i);
                }
            }
        });
        e.shutdown();
    }
}

