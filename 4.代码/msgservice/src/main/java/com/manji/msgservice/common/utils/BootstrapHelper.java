package com.manji.msgservice.common.utils;

/**
* @ClassName: BootstrapHelper
*/
public class BootstrapHelper {
    /** 
    * @Fields DEFAULT_PORT: 默认端口
    */
    private static final int DEFAULT_PORT = 8080;
    /** 
    * @Fields DEFULT_ENV: 默认环境
    */
    private static String DEFULT_ENV = "dev";
    /** 
    * @Fields port: 启动端口
    */
    private static int port = DEFAULT_PORT;

    /**
     * @Title: TomcatBootstrapHelper
     * @Description: 构造方法
     * @param @param port				端口
     */
    public BootstrapHelper() {
        System.setProperty("spring.profiles.active", DEFULT_ENV);
        System.setProperty("server.port", String.valueOf(port));
    }

    /**
     * @Title: TomcatBootstrapHelper
     * @Description: 构造方法
     * @param @param port				端口
     */
    public BootstrapHelper(int port) {
        System.setProperty("spring.profiles.active", DEFULT_ENV);
        System.setProperty("server.port", String.valueOf(port));
        this.port = port;
    }

    /** 
    * @Title: TomcatBootstrapHelper 
    * @Description: 构造方法
    * @param @param port				端口
    * @param @param env					环境
    */
    public BootstrapHelper(int port, String env) {
        System.setProperty("spring.profiles.active", env);
        System.setProperty("server.port", String.valueOf(port));
        this.port = port;
    }
    /**
     * @Title: log
     * @Description:打印启动日志
     * @param time
     * @return void
     */
    public static void log(long time) {
        System.out.println("**********************************************************************");
        System.out.println("启动成功: http://127.0.0.1:" + port + "   in:" + time + "ms");
        System.out.println("swagger地址: http://127.0.0.1:" + port + "/swagger-ui.html");
        System.out.println("**********************************************************************");
    }
}
