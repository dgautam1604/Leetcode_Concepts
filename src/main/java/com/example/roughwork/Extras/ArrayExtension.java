package com.example.roughwork.Extras;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.*;

public class ArrayExtension {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter length of initial array: ");
        int baseArrayLength = sc.nextInt();
        int[] arr = generateRandomArray(baseArrayLength);
        int[] extendedArray = extendArray(arr);
        System.out.println("*** Initial array ***");
        System.out.println(Arrays.toString(arr));
        System.out.println("*** Extended array ***");
        System.out.println(Arrays.toString(extendedArray));
    }

    /**
     * The method extends array.
     * If array {1, 2, 3} has been passed to this method then array {1, 2, 3, 2, 4, 6}
     * is returned from this method.
     *
     * @param arr - base of extension. Extended array contains elements from this array
     * and additionally contains the same elements multiplied by two.
     * @return extended array.
     */
    public static int[] extendArray(int[] arr) {

        int[] arr2 = new int[arr.length*2];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        for (int i = arr.length; i < arr2.length; i++) {
            arr2[i] = arr[i-arr.length]*2;
        }
        return arr2;
    }

    public static int[] generateRandomArray(int amountOfElements) {
        if(amountOfElements==0)
            return new int[0];
        Random rd = new Random(); // creating Random object
        int[] arr = new int[amountOfElements];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
        return arr;
    }
    public static String firstCharToTitleCase(String string) {

        char[] characters=string.toCharArray();
        String str="";
        int index=0;
        for(char c:characters){
            if(index==0) {
                String s= String.valueOf(c);
               str= str+s.toUpperCase();
            } else {
                String s= String.valueOf(c);
                str= str+s.toLowerCase();
            }
        }

        return str;
    }

}
