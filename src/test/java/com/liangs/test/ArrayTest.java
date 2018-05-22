package com.liangs.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * 第二题
 * Created by liangs on 2018/3/27.
 */
public class ArrayTest {

    ArrayList<Map<String,String>> array;
    String text = "";

    @Test
    public void testStore(){
        init();//初始化数组
        String store = store(array);//转为字符串
        System.out.println(store);
    }
    @Test
    public void testLoad(){
        text = "key1=value1;key2=value2\nkeyA=valueA\nkeyB=valueB;keyC=valueC;keyD=valueD\n";//初始化文本字符串
        array = load(text);//加载字符串到数组
        System.out.println(JSON.toJSON(array));
    }

    private String store(List<Map<String,String>> array) {
        for (int i = 0; i < array.size(); i++) {
            Map<String, String> map = array.get(i);
            Set<String> keySet = map.keySet();
            for(String item:keySet){
                text+=item+"="+map.get(item)+";";
            }
            text = text.substring(0, text.length() - 1);
            this.text +="\n";
        }
        return text;
    }

    private ArrayList<Map<String,String>> load(String text){
        array = new ArrayList<Map<String, String>>();
        String[] mapArr = text.split("\n");
        for (int i = 0; i < mapArr.length; i++) {
            String mapStr = mapArr[i];
            if(StringUtils.isNotBlank(mapStr)){
                Map<String,String> map = new HashMap<String, String>();
                String[] entryArr = mapStr.split(";");
                for (int j = 0; j < entryArr.length; j++) {
                    String entryStr = entryArr[j];
                    if(StringUtils.isNotBlank(entryStr)){
                        String[] kvArr = entryStr.split("=");
                        map.put(kvArr[0],kvArr[1]);
                    }
                }
                array.add(map);
            }
        }
        return array;
    }

    public void init(){
        HashMap<String,String> map1 = new HashMap<String, String>();
        HashMap<String,String> map2 = new HashMap<String, String>();
        HashMap<String,String> map3 = new HashMap<String, String>();
        map1.put("key1","value1");
        map1.put("key2","value2");
        map2.put("keyA","valueA");
        map3.put("keyB","valueB");
        map3.put("keyC","valueC");
        map3.put("keyD","valueD");
        array = new ArrayList<Map<String, String>>();
        array.add(map1);
        array.add(map2);
        array.add(map3);
    }
}
