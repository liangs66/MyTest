package com.liangs.test;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liangs on 2018/3/23.
 */
public class BlockingQueueTest {


    BlockingQueue<String> blockingQueue;
    DelayQueue delayQueue;

    @Test
    public void test1() throws InterruptedException {
        blockingQueue = new ArrayBlockingQueue<String>(10);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        blockingQueue.take();
                        Thread.sleep(1000L);
                        System.out.println("TTTTTTTTTTTTTTTTTTTTTT");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        blockingQueue.put("1");
                        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPP");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(100000L);
    }

    @Test
    public void test2() throws InterruptedException {
        delayQueue = new DelayQueue<DelayedEle>();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000L);
                    while (true) {
                        delayQueue.take();
                        System.out.println("TTTTTTTTTTTTTTTTTTTTTT");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    delayQueue.put(new DelayedEle());
                    System.out.println("PPPPPPPPPPPPPPPPPPP");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(5000L);
        System.out.println(delayQueue.size());
    }

    class DelayedEle implements Delayed {

        /**
         * 剩余时间=到期时间-当前时间
         * 剩余时间>0 说明还不能取
         * 当剩余时间<=0时，可以取出
         */
        public long getDelay(TimeUnit unit) {
            return unit.convert(-1000, TimeUnit.MILLISECONDS);
        }

        /**
         * 优先队列里面优先级规则
         */
        public int compareTo(Delayed o) {
            return new Random().nextInt(100);
        }

    }

    @Test
    public void teste() throws InterruptedException {
        final PriorityBlockingQueue<String> pq = new PriorityBlockingQueue<String>(10);
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        System.out.println(pq.take());
                        Thread.sleep(200L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int i=100;
                while (true){
                    i--;
                    pq.put(i+"");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(10000L);
    }
}
