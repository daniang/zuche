package com.example.zuche.lockedstudy;

/**
 * 当一个线程访问对象的一个synchronized(this)同步代码块时,另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块.
 * <p>
 * 【Demo2】:多个线程访问synchronized和非synchronized代码块
 *
 * @Author : chengzhang
 * @Date : 2022/6/16 19:56
 */
public class Counter implements Runnable {

    private int count;


    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //非synchronized代码块,未对count进行读写操作,所以可以不用synchronized

    public void printCount() {

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "count: " + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        if (threadName.equals("A")) {
            countAdd();
        } else if (threadName.equals("B")) {
            printCount();
        }
    }
}
