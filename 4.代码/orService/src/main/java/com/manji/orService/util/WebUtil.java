package com.manji.orService.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class WebUtil {

    /**
     * 生成订单号
     *
     * @param
     * @return
     */
    public static String GeneratWorkerNumber() {

        //创建单号(StringBuffer)
        StringBuffer workerNum = new StringBuffer();
        workerNum.append("GD");

        //生成时间(拼接)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        workerNum.append(Long.toString(Long.parseLong(sdf.format(new Date())),32).toUpperCase());

        //随机生成四位数(拼接)
        int randomNumber;//定义两变量
        Random ne = new Random();//实例化一个random的对象ne
        randomNumber = ne.nextInt(9999 - 1000 + 1) + 1000;//为变量赋随机值1000-9999

        workerNum.append(randomNumber);

        return workerNum.toString();
    }

}
