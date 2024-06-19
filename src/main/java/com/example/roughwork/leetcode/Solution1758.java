package com.example.roughwork.leetcode;

public class Solution1758 {
    public int minOperations2(String s) {
        char[] ch = s.toCharArray();
        int flag=0;
        int min=0;
        for(int i=1;i< ch.length;i++){
            if((ch[i-1]=='0' && ch[i]=='1') || (ch[i-1]=='1' && ch[i]=='0')) {
                if(flag==1 && (ch[i-3]=='0' && ch[i-2]=='1')){
                    ch[i-1]='0';
                    ch[i]='1';
                    flag = 0;
                    min+=2;
                    continue;
                } else if(flag==1 && (ch[i-3]=='1' && ch[i-2]=='0')){
                    ch[i-1]='1';
                    ch[i]='0';
                    flag = 0;
                    min+=2;
                    continue;
                }
                else {
                    flag = 1;
                    i++;
                    continue;
                }
            }
//            || ch[i-1]=='1' && ch[i]=='1')
            if(ch[i-1]=='0' && ch[i]=='0')
                if(i+1< ch.length && ch[i+1]=='0')
                    ch[i]='1';
                else if(i+1< ch.length && ch[i+1]=='1')
                    ch[i-1]='1';
                else
                    ch[i]='1';
            else if(ch[i-1]=='1' && ch[i]=='1')
                if(i+1< ch.length && ch[i+1]=='1')
                    ch[i]='0';
                else if(i+1< ch.length && ch[i+1]=='0')
                    ch[i-1]='0';
                else
                    ch[i]='0';
                min++;
            i++;
                flag=0;
        }
        return min;
    }
    public int minOperations(String s) {
        int counter1=0;
        int counter0=0;
        char[] ch = s.toCharArray();
        for(int i=0;i< ch.length;i++){
            if(i%2==0) {
                if (ch[i] == '0')
                    counter1++;
                else if (ch[i] == '1')
                    counter0++;
            }
            else {
                if (ch[i] == '0')
                    counter0++;
                else if (ch[i] == '1')
                    counter1++;
            }
        }
        return Math.min(counter0,counter1);
    }
}
