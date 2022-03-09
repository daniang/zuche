package com.example.zuche.vo;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/3/7 17:42
 */
public class Demo {
    static int i = 0;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int j = 0; j < 100; j++) {
                    System.out.println("Thread1  i = " + i);
                    i++;
            }
        });

        Thread t2 = new Thread(()->{
            for (int j = 0; j < 100; j++) {
                    System.out.println("Thread2  i = " + i);
                    i--;
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(i);
    }
}
