package com.liangs.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三题
 * Created by liangs on 2018/3/27.
 */
public class BestWayTest {

    List<String> routeResList = new ArrayList<String>();

    //在这里定义节点：A1表示节点A，权重1
    String[] pointArr = {"A1","B2","C2","D3","E4"};
    Map<String,Integer> pointMap = new HashMap<String, Integer>();//存放节点信息
    //在这里定义路径:AB则表示A到B.
    String[] routeArr = {"AB","BC","BD","BE","ED","DC","CA","DE","EC"};
    //在这里定义开始节点
    String startPoint = "A";

    @Test
    public void test(){
        int max = getMax();
        System.out.println("max="+max);
    }

    /**
     * 遍历所有路径，算出权重之和的最大值
     * @return
     */
    private int getMax() {
        setRouteResList("", startPoint);
        System.out.println("RouteResList="+JSON.toJSON(routeResList));
        initPointMap();
        int max = 0;
        String maxRoute = "";
        for (String route:routeResList){
            int temp = 0;
            for (int i = 0; i < route.length(); i++) {
                temp+=pointMap.get(route.charAt(i)+"");
            }
            if(temp>max){
                max = temp;
                maxRoute = route;
            }else if(temp==max){
                maxRoute+=","+route;
            }
        }
        System.out.println("maxRoute=["+maxRoute+"]");
        return max;
    }

    /**
     * 获取所有可能路径，存入routeResList
     * @param route 已有路径
     * @param startPoint 要拼接的节点
     */
    private void setRouteResList(String route,String startPoint){
        route+=startPoint;
        String temp = startPoint;//存住startPoint的值，因为第74行会改变startPoint的值，第69行再回复
        int count = 0;
        for (int i = 0; i < routeArr.length; i++) {
            startPoint = temp;//回复startPoint的值
            String item = routeArr[i];
            //1.判断是否可以连接该route；2.做死循环验证，判断不会死循环（只要当前线路串中没有重复节点point，则不会死循环）
            if(item.startsWith(startPoint)&&!route.contains(item.charAt(1)+"")){
                //验证通过，连接新线路
                startPoint=item.charAt(1)+"";//startPoint的值被改变
                setRouteResList(route, startPoint);
            }else{
                count++;
                continue;
            }
        }
        if(count==routeArr.length){//判断未连接新路线的次数，以此判断路线是否连接完成
            routeResList.add(route);//路线连接完成，保存
        }
    }

    /**
     * 初始化节点Map
     */
    private void initPointMap() {
        pointMap = new HashMap<String, Integer>();
        for (int i = 0; i < pointArr.length; i++) {
            String point = pointArr[i];
            pointMap.put(point.charAt(0)+"",Integer.valueOf(point.charAt(1) + ""));
        }
    }
}
