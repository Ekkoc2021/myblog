package com.ekko.myblog;

public class test2 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        fib(30);
        System.out.println(System.nanoTime() - start);
        //4158400
    }
    public static int fib(int n){
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }
}
