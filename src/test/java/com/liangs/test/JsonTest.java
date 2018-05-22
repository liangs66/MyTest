package com.liangs.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 第一题
 * Created by liangs on 2018/3/27.
 */
public class JsonTest {

    String input = "{\"a\":1,\"b\":{\"c\":2,\"d\":[3,4],\"e\":{\"f\":5,\"g\":6}}}";
    Map<String,Object> destMap = new HashMap<String, Object>();

    @Test
    public void test(){
        //把字符串转为map
        Map<String,Object> oriMap = JSON.parseObject(input, Map.class);
        //解析map（oriMap），结果封装为新map（destMap）
        transform(oriMap,"");
        //输出结果
        System.out.println(JSON.toJSON(destMap));
    }

    /**
     * 递归循环map,把树形结构改为列表结构，存入destMap
     * @param map 原始map
     * @param pkey 所有上级key组合字符串
     */
    private void transform(Map<String, Object> map,String pkey) {
        Set<String> keySet = map.keySet();
        for (String item:keySet ){
            Object value = map.get(item);
            if(value instanceof Map){
                transform((Map<String,Object>)value,pkey+item+".");
            }else{
                destMap.put(pkey+item,value);
            }
        }
    }
}
