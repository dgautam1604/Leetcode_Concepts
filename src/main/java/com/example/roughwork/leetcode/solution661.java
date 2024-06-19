package com.example.roughwork.leetcode;

import java.util.Arrays;

public class solution661 {
    public int[][] imageSmoother(int[][] img) {
        int res[][] = new int[img.length][img[0].length];
        for(int i = 0; i < img.length; i++) {
            for(int j = 0; j < img[0].length; j++) {
                res[i][j] = smoothen(img, i, j);
            }
        }
        return res;
    }

    int smoothen(int[][] img, int x, int y) {
        int m = img.length;
        int n = img[0].length;
        int sum = 0;
        int count = 0;
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                sum += img[nx][ny];
                count++;
            }
        }
        return sum/count;

    }
    public int[][] imageSmoother2(int[][] img) {
        int rows = img.length;
        int cols = img[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int totalSum = 0;
                int count = 0;

                for (int x = Math.max(0, i-1); x < Math.min(rows, i+2); ++x) {
                    for (int y = Math.max(0, j-1); y < Math.min(cols, j+2); ++y) {
                        totalSum += img[x][y];
                        count += 1;
                    }
                }

                result[i][j] = totalSum / count;
            }
        }

        return result;
    }
    //2,3,1,1,4


}
