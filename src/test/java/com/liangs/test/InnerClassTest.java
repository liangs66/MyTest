package com.liangs.test;

import com.liangs.test.entity.PtQxUser;
import com.rabbitmq.client.AMQP;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by liangs on 2018/3/21.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/spring-task.xml"})
public class InnerClassTest {

    private static Logger logger = Logger.getLogger("Timer");

    static {
        logger.addAppender(new ConsoleAppender(new Layout() {
            @Override
            public String format(LoggingEvent loggingEvent) {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(loggingEvent.getTimeStamp());
            }

            @Override
            public boolean ignoresThrowable() {
                return false;
            }

            public void activateOptions() {
            }
        }));
    }

    //    @Autowired
    static ThreadPoolTaskScheduler scheduler;

    static {
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(40);
        scheduler.initialize();
    }

    Map<String, String> map;

    @Test
    public void test() throws InterruptedException {
        map = new HashMap<String, String>();
        map.put("key", "0");
        final ScheduledFuture<?> schedule = scheduler.schedule(new Runnable() {
            public void run() {
                System.out.println(1);
                if (true) {
                    map.put("key", "1");
                }
            }
        }, new CronTrigger("0/1 * * * * ?"));
        scheduler.schedule(new Runnable() {
            public void run() {
                if ("1".equals(map.get("key"))) {
                    schedule.cancel(true);
                }
            }
        }, new CronTrigger("0/1 * * * * ?"));
        scheduler.schedule(new Runnable() {
            public void run() {
                if (!schedule.isCancelled()) {
                    schedule.cancel(true);
                }
            }
        }, DateUtils.addMinutes(new Date(), 1));
        Thread.sleep(100000L);
    }

    @Test
    public void test2() {
        new Thread(new Runnable() {
            public void run() {
                System.out.println(1);
            }
        }) {
            public void run() {
                System.out.println(2);
            }
        }.run();
    }

    @Test
    public void test3() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerTask");
            }
        }, 1000, 1000);
        Thread.sleep(10000L);
    }

    String name = "abcdefg";
    String a = "XXX";

    public void test5() {
        int length = name.length();
        synchronized (a) {
            for (int i = 0; i < length; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }

    @Test
    public void test4() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                test5();
            }
        }, 1, 1);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                test5();
            }
        }, 1, 1);
        Thread.sleep(100000L);
    }

    @Test
    public void test6() throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {

                for (int j = 0; j < 50; j++) {
                    synchronized (a) {
                        for (int i = 0; i < 10; i++) {
                            System.out.print("第" + (j+1) + "次循环:");
                            System.out.println("线程1:" + (i+1));
                        }
                        a.notify();
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {

                for (int j = 0; j < 50; j++) {
                    synchronized (a) {
                        for (int i = 0; i < 20; i++) {
                            System.out.print("第" + (j+1) + "次循环:");
                            System.out.println("线程2:" + (i+1));
                        }
                        a.notify();
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        Thread.sleep(100000L);

    }
    Integer count = 0;
    @Test
    public void test10() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    synchronized (count){
                        count++;
                    }
                }
            }).start();
            new Thread(new Runnable() {
                public void run() {
                    synchronized (count){
                        count--;
                    }
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(count);
    }
    class DataA {
        int aaa = 0;
        public synchronized void add(){
            aaa++;
        }
        public synchronized void remove(){
            aaa--;
        }
    }
    @Test
    public void test11() throws InterruptedException {
        final DataA dataA = new DataA();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    dataA.add();
                }
            }).start();
//            new Thread(new Runnable() {
//                public void run() {
//                    dataA.remove();
//                }
//            }).start();
        }
        Thread.sleep(10000L);
        System.out.println(dataA.aaa);
    }
}
