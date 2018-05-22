package com.liangs.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liangs.test.entity.PtQxUser;
import com.liangs.test.service.PtQxUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liangs on 2018/2/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-config.xml"})
public class MPtest {

    @Autowired
    private PtQxUserService ptQxUserService;
    @Test
    public void testMp(){
        PtQxUser ptQxUser = new PtQxUser();
        ptQxUser.setUsername("梁帅");
        ptQxUser.setMtype(1);
        ptQxUser.setEmail("603@qq.com");
        ptQxUser.setLoginname("liangs");
        ptQxUser.setPassword("liangs");
        ptQxUser.setSeclevel(1);
        ptQxUser.setCreatename("1");
        ptQxUser.setUpdatename("1");
        ptQxUser.setCreatetime(new Date());
        ptQxUser.setUpdatetime(new Date());
        ptQxUser.setSex("1");
        ptQxUser.setPhone("1");
        ptQxUser.insert();
    }
    @Test
    public void testMp1(){
        PtQxUser ptQxUser = new PtQxUser();
//        ptQxUser.setUserid("f35df889126d466a8615493f362a8564");
//        ptQxUser.setUsername("梁帅1");
//        ptQxUser.setMtype(1);
//        ptQxUser.setEmail("603@qq.com");
//        ptQxUser.setLoginname("liangs");
//        ptQxUser.setPassword("liangs");
//        ptQxUser.setSeclevel(1);
//        ptQxUser.setCreatename("2");
//        ptQxUser.setUpdatename("2");
//        ptQxUser.setCreatetime(new Date());
//        ptQxUser.setUpdatetime(new Date());
//        ptQxUser.setSex("2");
//        ptQxUser.setPhone("2");
        int i = ptQxUser.selectCount(new EntityWrapper<PtQxUser>() {
                }.where("username={0}", "梁帅")
        );
        System.out.println(i);
    }
    @Test
    public void testMp2(){
        //insert
        ptQxUserService.insertBatch(null);
        ptQxUserService.insertOrUpdate(null);
        ptQxUserService.insert(null);
        ptQxUserService.insertBatch(null,30);
        ptQxUserService.insertBatch(null);
        ptQxUserService.insertAllColumn(null);
        ptQxUserService.insertOrUpdateAllColumnBatch(null);
        ptQxUserService.insertOrUpdateAllColumnBatch(null,10);
        ptQxUserService.insertOrUpdateBatch(null);
        ptQxUserService.insertOrUpdateBatch(null, 10);
        ptQxUserService.insertOrUpdateAllColumn(null);

        //update
        PtQxUser ptQxUser = null;
        EntityWrapper<PtQxUser> wrapper = new EntityWrapper<PtQxUser>();
        Page<PtQxUser> page = new Page<PtQxUser>(10,100,"username");
        ptQxUserService.update(ptQxUser,wrapper);
        ptQxUserService.updateAllColumnBatchById(null);
        ptQxUserService.updateAllColumnById(ptQxUser);
        ptQxUserService.updateBatchById(null,10);
        //select
        ptQxUserService.selectOne(wrapper);
        ptQxUserService.selectBatchIds(null);
        ptQxUserService.selectByMap(null);
        ptQxUserService.selectMap(wrapper);
        ptQxUserService.selectMaps(wrapper);
        ptQxUserService.selectMapsPage(null,wrapper);
        ptQxUserService.selectObj(wrapper);
        ptQxUserService.selectObjs(wrapper);
        ptQxUserService.selectPage(null,wrapper);


    }
    @Test
    public void testMp5(){
//        EntityWrapper<PtQxUser> entityWrapper = new EntityWrapper<PtQxUser>();
//        entityWrapper.where("username={0}" , "梁帅").or("username={0} or username={1} or username={2}","梁帅1","王五","王五1").orderBy("username",false);
//        System.out.println(entityWrapper.getSqlSegment());
//        PtQxUser ptQxUser = ptQxUserService.selectOne(entityWrapper);
//        System.out.println(ptQxUser);
        System.out.println(111111111);
    }
}
