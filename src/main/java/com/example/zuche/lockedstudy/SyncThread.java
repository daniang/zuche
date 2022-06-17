package com.example.zuche.lockedstudy;

/**
 * @Author : chengzhang
 * @Date : 2022/6/16 17:02
 */

/**
 * 同步线程
 */
public class SyncThread implements Runnable {

    //修饰一个代码块

    /**
     * 一个现场称访问一个对象中的synchronized(this)同步代码块时,其他试图访问该对象的线程将被阻塞.
     */


    private static int count;

    public SyncThread() {
        count = 0;
    }


    @Override
    public void run() {
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

}
