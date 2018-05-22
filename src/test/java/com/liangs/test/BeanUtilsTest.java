package com.liangs.test;

import com.liangs.test.entity.PtQxUser;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;

/**
 * Created by liangs on 2018/4/3.
 */
public class BeanUtilsTest {

    @Test
    public void test(){
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(PtQxUser.class);
        for (PropertyDescriptor p:propertyDescriptors){
            System.out.println(p.getName()+","+p.getReadMethod()+p.isBound()+","+p.isConstrained());
        }
    }

}
