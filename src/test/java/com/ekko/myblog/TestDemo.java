package com.ekko.myblog;

import java.util.HashMap;
import java.util.Map;

public class TestDemo {
//    public static void main(String[] args) {
//
//    }
//    public static int solution(int n, String s) {
//        char[] studentchar = s.toCharArray();
//        int girlAn = 0, boyAn = 0;
//        int k = 0;
//        for(int i = 0; i < n; i++) {
//            if(studentchar[i] == 'F') {
//                boyAn = boyAn + i - k;
//                k++;
//            }
//        }
//        k = 0;
//        for(int i = 0; i < n; i++) {
//            if(studentchar[i] == 'M') {
//                girlAn = girlAn + i - k;
//                k++;
//            }
//        }
//        if(girlAn > boyAn) {
//            return boyAn;
//        } else {
//            return girlAn;
//        }
//    }
public static void main(String[] args) {
    //记录开始时间
    long start = System.nanoTime();
    int num = 100000000;
    for (int i = 0; i < num; i++) {
        BubbleSort(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
    //打印耗时时间
    System.out.println(System.nanoTime() - start);
}
    //排序
    public static void BubbleSort(int... arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
