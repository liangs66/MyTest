package com.liangs.test;

import com.liangs.test.rabbitmq.MessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by liangs on 2018/2/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-config.xml"})
public class MQtest {

    @Autowired
    MessageProducer messageProducer;

    @Test
    public void testMQ() throws IOException, TimeoutException {
        for (int i = 0; i < 100; i++) {
            messageProducer.sendMessage(i);
        }
        try {
            Thread.currentThread().sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testMQ1(){
        System.out.println("1111111111111111111");
    }
}
