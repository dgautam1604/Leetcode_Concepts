package com.example.roughwork.Prep;

import java.util.*;
import java.util.stream.Collectors;

public class OtherLeetcode {
    public String removeDigit(String number, char digit) {
        int maxNumber=0;
        for(int i=0;i<number.length();i++){
            char x=number.charAt(i);
            if(digit==x){
                String newNumber="";
                if(i==0)
                    newNumber=number.substring(i+1);
                else if(i==number.length())
                    newNumber=number.substring(0,i);
                else
                    newNumber=number.substring(0,i)+number.substring(i+1);
                if(maxNumber<Integer.parseInt(newNumber))
                    maxNumber=Integer.parseInt(newNumber);
            }

        }
        return String.valueOf(maxNumber);
    }
    public static String removeDigit2(String number, char digit) {
        int index=0;
        int n = number.length();
        for(int i=0;i<n;i++){
            if(number.charAt(i)==digit){
                index=i;
                if(i+1<n && digit < number.charAt(i+1))
                    break;
            }
        }
        return number.substring(0,index) + number.substring(index+1,n);
    }
    public static String reverseVowels(String s) {

        Character[] cc={'a','e','i','o','u','A','E','I','O','U'};
        List<Character> l=Arrays.asList(cc);
//        System.out.println(l);
        char arr[]=s.toCharArray();
        int start=0,end= s.length()-1;
        while(start<end){
            if(!l.contains(s.charAt(start)))
                start++;
            if(!l.contains(s.charAt(end)))
                end--;
            if(l.contains(s.charAt(start)) && l.contains(s.charAt(end))){

                char temp=arr[end];
                arr[end]=arr[start];
                arr[start]=temp;
                start++;end--;
            }
        }
        return s;

    }

    static void find_duplicate(int a[], int n)
    {
        int p = 0;
        while (p != n) {
            if (a[p] == -1) {
                p++;
            }
            else {
                if (a[a[p] - 1] == -1) {
                    System.out.println(a[p]);
                    break;
                }
                else {
                    a[p] = a[a[p] - 1];
                    a[a[p] - 1] = -1;
                }
            }
        }
    }
    public int[] plusOne(int[] digits) {
        int x=digits[digits.length-1];
//199
        int i= digits.length-1;
        while(i>=0 && digits[i]==9){
            digits[i]=0;
            i--;
        }
        if(i>=0) {
            digits[i] = digits[i] + 1;
            return digits;
        }
        else{
            int newDigits[]=new int[digits.length+1];
            newDigits[0]=1;
            int j=1;
            while(j< newDigits.length-1){
                newDigits[j++]=0;
            }
            return newDigits;
        }
//        return digits;


    }
    public int strStr(String haystack, String needle) {
        if(haystack.length()<needle.length())
            return -1;
        if(haystack.equals(needle))
            return 0;
        int i=0,j=0;

        while(i<haystack.length()-needle.length()+1){
            if(haystack.charAt(i)!=needle.charAt(0))
                i++;
            else{
                if(haystack.substring(i,i+needle.length()).equals(needle)){
                    return i;
                }else{
                    i++;
                }
            }

        }
        return -1;
    }
    public static boolean isPalindrome(String s) {
        String str1 = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int l=0;
        int r=str1.length()-1;
        while(l<r){
            if(str1.charAt(l)!=str1.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
    public static int longestIdealString(String s, int k) {

        int res = 0;
        int n = s.length();
        int dp[] = new int[150];

        for (int ci = 0; ci < n; ++ci) {
            int i = s.charAt(ci);
            for (int j = i - k; j <= i + k; ++j)
                dp[i] = Math.max(dp[i], dp[j]);
            res = Math.max(res, ++dp[i]);
        }
        return res;
    }
    public int longestIdealString2(String s, int k) {
        return longestIdealStringHelper(s,' ',0,k);
    }
    public static int longestIdealStringHelper(String s,char prev, int i,int k) {

        if(i==s.length())
            return 0;


        int res=longestIdealStringHelper(s,prev,i+1,k);//        skip s.charAt[i]
        int x = Math.abs((int) s.charAt(i) - (int) prev);
        if (prev==' ' || x <= k)
            res=Math.max(res,longestIdealStringHelper(s,s.charAt(i),i+1,k));//        include s.charAt[i]

        return res;
    }
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort intervals based on start time

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                // If the list is empty or there's no overlap with the last interval in the list
                merged.add(interval);
            } else {
                // Merge overlapping intervals
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '0') {
                    count++;
                    shrink(grid, i, j);
                }
            }
        }
        return count;
    }

    void shrink(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        shrink(grid, i, j+1);
        shrink(grid, i, j-1);
        shrink(grid, i+1, j);
        shrink(grid, i-1, j);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> l=new ArrayList<>();
        int i=0,j=0;
        while(i<nums1.length){
            l.add(nums1[i]);
            i++;
        }
        while(j<nums2.length){
            l.add(nums2[j]);
            j++;
        }
        Collections.sort(l);
        int mid=l.size()/2;
        if(l.size()%2==0){
            double midVal=((double) l.get(mid)+(double) l.get(mid-1))/2;
            return midVal;
        }else{
            double midVal=((double) l.get(mid));
            return midVal;
        }

    }
    public int longestCommonSubsequence(String text1, String text2) {

        String first="";
        String second="";
        if(text1.length()>text2.length()) {
            first=text1;
            second=text2;
        }
        else {
            first=text2;
            second=text1;
        }

        int count=0;
        int i=0,j=0;

        while(i<first.length()){
            while(second.substring(j).contains(first.substring(i,i+1)) && j<second.length()){
                if(first.charAt(i)==second.charAt(j)){
                    count++;
                    j++;
                    break;
                }
                j++;
            }
            i++;
        }

        return count;

    }
    public List<String> fizzBuzz(int n) {
        List<String> l=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(i%3==0 && i%5==0)
                l.add("FizzBuzz");
            else if(i%3==0)
                l.add("Fizz");
            else if(i%5==0)
                l.add("Buzz");
            else
                l.add(String.valueOf(i));
        }
        return l;
    }
    public boolean containsDuplicate(int[] nums) {
        Map<Integer,Integer> m=new HashMap<>();
        for(int n:nums){
            if(m.containsKey(n))
                return false;
            m.put(n,1);
        }
        return true;
    }
    public void wonderfulSubstrings(String word) {
        Map<Character ,Integer> h=new HashMap<>();
        for(char c:word.toCharArray()){
            if(h.containsKey(c))
                h.put(c,h.get(c)+1);
            else
                h.put(c,1);
        }
        int count=h.size();

    }
    public void reverseString(char[] s) {
        int n=s.length-1;
        int i=0;
        while(i<n){
            char c=s[i];
            s[i]=s[n];
            s[n]=c;
            i++;
            n--;
        }

    }
    public int scoreOfString(String s) {
        int[] x=new int[s.length()];
        for(int i=0;i<s.length();i++){
            x[i]=s.charAt(i);
        }
        int prev=x[0];
        int score=0;
        for(int i=1;i<x.length;i++){
            score=score+Math.abs(x[i]-prev);
            prev=x[i];
        }
        return score;
    }
    public int appendCharacters(String s, String t) {
        int tIndex=0;
        int tBest=0;
        for(int i=0;i<s.length();i++){
            while(i<s.length() && tIndex<t.length() && t.charAt(tIndex)==s.charAt(i)){
                tIndex++;
                i++;
            }
        }
        s=s+t.substring(tIndex);
        return t.length()-tIndex;
        //Case 1: You find the common values in s and t and increment till the point the value is common
        //Case 2: If there are repeated characters like 2 matches in t and then 3 matches at a later time
    }

    public static int specialArray(int[] nums) {

      Arrays.sort(nums);
      int n=nums.length;
      for(int i=0;i<=nums.length;i++){
          if(i==n)
              return i;

          n=n-1;
      }
      return  -1;
    }
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

    public static void main(String[] args) {
//        int[][] a={{1,3},{2,6},{8,10},{15,18}};
        int[] nums={1,2,3,4,5};
        String s="";


//        System.out.println(specialArray(nums));


//        merge(a);
//        System.out.println(longestIdealString("acfgbd",5));
//lopjigba

//        isPalindrome("A man, a plan, a canal: Panama");
//        System.out.println(removeDigit2("1231",'1'));
//        Character[] cc={'a','e','i','o','u'};
//        List<Character> l=Arrays.asList(cc);
//        System.out.println(reverseVowels("hello"));

//        int[] a = { 1, 2, 4, 3, 4, 5, 6, 3 };
//        int n = a.length;
//
//        find_duplicate(a, n);
//
//        int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
//
//        for (int i = 0; i < myNumbers.length; ++i) {
//            for (int j = 0; j < myNumbers[i].length; ++j) {
//                System.out.println(myNumbers[i][j]);
//            }
//        }
//        //or
//        for (int[] row : myNumbers) {
//            for (int i : row) {
//                System.out.println(i);
//            }
//        }


    }
}
