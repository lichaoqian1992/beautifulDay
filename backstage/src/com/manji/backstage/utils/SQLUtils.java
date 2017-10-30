package com.manji.backstage.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class SQLUtils {

    //    测试环境数据源
//    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//
//    private static String dbURL = "jdbc:sqlserver://192.168.0.31:1433;DatabaseName=manji";
//
//    private static String userName = "mjdev";
//
//    private static String userPwd = "546bkf@mj";


     //正式环境数据源
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static String dbURL = "jdbc:sqlserver://172.16.1.21:1433;DatabaseName=manji";

    private static String userName = "mjuser";

    private static String userPwd = "78Gxtw@#Dysq";

    private static Connection getCoonection() {
       // Properties pi=new Properties();
       // pi.load("/");
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
            return conn;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
            Connection conn = getCoonection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return rs;
    }

    public static boolean executeUpdate(String SQL) throws SQLException {
            Connection conn = getCoonection();

            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(SQL);

            if (result > 0){
                return true;
            }
        return false;
    }





}
