package com.liangs.test;

import com.liangs.test.elasticsearch.domain.Account;
import com.liangs.test.elasticsearch.service.AccountService;
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
public class EStest {

    @Autowired
    AccountService accountService;

    @Test
    public void testES(){
        Account account = new Account();
        account.setEmail("603553@qq.com");
        account.setId(1234567l);
        account.setAge(28l);
        account.setFirstname("liangs");
        accountService.saveAccount(account);
    }
    @Test
    public void testES1(){
        System.out.println(111);
    }
}
