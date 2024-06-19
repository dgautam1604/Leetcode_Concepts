package com.example.roughwork.Prep.String;

import java.util.Stack;

public class stringSample {
    public boolean checkValidString(String s) {
        Stack<Integer> leftIndices = new Stack<>();
        Stack<Integer> starIndices = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftIndices.push(i);
            } else if (c == '*') {
                starIndices.push(i);
            } else { // c == ')'
                if (!leftIndices.isEmpty()) {
                    leftIndices.pop();
                } else if (!starIndices.isEmpty()) {
                    starIndices.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftIndices.isEmpty() && !starIndices.isEmpty()) {
            if (leftIndices.pop() > starIndices.pop()) {
                return false;
            }
        }

        return leftIndices.isEmpty();
    }
}
