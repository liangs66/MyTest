package com.liangs.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liangs on 2018/3/23.
 */
public class MultiThreadTest {

    ExecutorService executorService = Executors.newCachedThreadPool();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
    ExecutorService executorService1 = Executors.newFixedThreadPool(10);
    ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(50);
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Semaphore semaphore = new Semaphore(2);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    CountDownLatch countDownLatch = new CountDownLatch(50);
    Exchanger<String> exchanger = new Exchanger<String>();
    @Test
    public void test1() throws InterruptedException {
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println(1);
            }
        });
    }

    @Test
    public void test2() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            final int a = i;
            executorService1.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"================"+a);
                }
            });
        }
        Thread.sleep(1000);
    }
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ScheduledFuture<Object> future = scheduledExecutorService.schedule(new Callable<Object>() {
            public Object call() throws Exception {
                return null;
            }
        }, -1000, TimeUnit.MILLISECONDS);
        System.out.println(future.get());
    }
    @Test
    public void test4() throws ExecutionException, InterruptedException {
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                int i;
            }
        }, -1000, TimeUnit.MILLISECONDS);
        System.out.println(schedule.get());
    }
    int a;
    @Test
    public void test5() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    lock.lock();
                    try {
                        a++;
                    }finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
        Thread.sleep(1000L);
        System.out.println(a);
    }
    @Test
    public void test9() {
        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    System.out.println("1================="+(sdf.format(new Date())));
                    condition.await();
                    Thread.sleep(1000L);
                    System.out.println("3================="+(sdf.format(new Date())));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    System.out.println("2================="+(sdf.format(new Date())));
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signalAll();
                lock.unlock();
            }
        }).start();
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test6() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(2);
                }
            }).start();
            System.out.println(i);
        }
        System.out.println(new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
        Thread.sleep(3000L);
        System.out.println(new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
        for (int i = 0; i < 50; i++) {
            countDownLatch.countDown();
        }
        Thread.sleep(10000L);
    }
    @Test
    public void test7() throws InterruptedException {
        System.out.println(new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(3000));
                        cyclicBarrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(2+"=================="+new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
                }
            }).start();
            System.out.println(i);
        }
        Thread.sleep(10000L);
    }
    @Test
    public void test8() throws InterruptedException {
        System.out.println(new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(3000));
                        cyclicBarrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(2+"=================="+new SimpleDateFormat("HH:mm:ss SSS").format(new Date()));
                }
            }).start();
            System.out.println(i);
        }
        Thread.sleep(10000L);
    }
    @Test
    public void test10() throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    soutTime();
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                }
            }).start();
        }
        Thread.sleep(10000L);
    }
    @Test
    public void test11() throws InterruptedException {
        soutTime();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000L);
                    String thread1 = exchanger.exchange("thread1");
                    soutTime();
                    System.out.println("thread1==============="+thread1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000L);
                    String thread2 = exchanger.exchange("thread2");
                    soutTime();
                    System.out.println("thread2===============" + thread2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(5000L);
    }

    private void soutTime() {
        System.out.println(sdf.format(new Date()));
    }
}
