package com.example.roughwork.Extras;

public class rough {
    public static void main(String[] args) {
        String s="hello";
        char[] ch = s.toCharArray();
        ch[0]='y';
        for(char c:ch)
            System.out.print(c +" ");
    }
}
