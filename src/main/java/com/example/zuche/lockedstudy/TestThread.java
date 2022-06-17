package com.example.zuche.lockedstudy;


/**
 * @Author : chengzhang
 * @Date : 2022/6/16 17:13
 */
public class TestThread {


    public static void main(String[] args) {
        /**
         * 当两个并发线程(thread1和thread2)访问同一个对象 (syncThread)中的synchronized代码块时,在同一时刻只能有一个线程得到执行,另一个线程受阻塞
         * ,必须等待当前线程执行完这个代码块以后才能执行该代码块。thread1 和thread2 是互斥的,因为在执行synchronized代码块时会锁定当前对象,只有执行完该代码块才能释放该对象锁
         */
//        SyncThread syncThread = new SyncThread();
//
//        Thread thread1 = new Thread(syncThread, "SyncThread1");
//
//        Thread thread2 = new Thread(syncThread, "SyncThread2");


        /**
         *  不是说一个线程执行synchronized代码块时其它的线程受阻塞吗? 为什么上面的例子中thread1和thread2同时在执行.这是因为synchronized只锁定对象,每个对象只有一个锁(lock)与之相关联
         *  下面的代码等同于
         *
         *         SyncThread syncThread1 = new SyncThread();
         *         SyncThread syncThread2 = new SyncThread();
         *
         *         Thread thread1 = new Thread(syncThread1,"SyncThread1");
         *         Thread thread2 = new Thread(syncThread2,"SYncThread2");
         *         这时创建了两个SyncThread的对象syncThread1 和syncThread2,线程thread1执行的是syncThread1对象中的synchronized代码(run),
         *         而线程thread2执行的是syncThread2对象中的synchronized代码(run)；我们知道sychronized锁定的是对象,这时会有两把锁分别锁定syncThread1对象
         *         和syncThread2对象,而这两把锁是互不干扰的,不形成互斥,所以两个线程可以同时执行
         *
         */


//        Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
//        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
//        thread1.start();
//        thread2.start();

        Counter counter = new Counter();

        Thread thread1 = new Thread(counter, "A");

        Thread thread2 = new Thread(counter, "B");

        thread1.start();
        thread2.start();


    }
}
