package com.manji.backstage.controller;

import com.manji.backstage.service.SqlService;
import com.manji.backstage.utils.ExcelUtils;
import com.manji.backstage.utils.SQLUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pudding on 2017-5-8.
 */
@Controller
public class SqlQueryController {

    @Autowired
    private SqlService sqlService;

    @RequestMapping("/sqlQuery")
    public String  sqlQuery(){
        return "sql/sql_query";
    }


    @RequestMapping("/sqlfind")
    @ResponseBody
    public Map<String,Object> sqlfind(String sql){
        Map<String,Object> map=new HashMap<String,Object>();

        int is=sqlService.sqlstatic(sql);

        try {
        switch (is){
            //查询
            case 1:
                Map<String,Object> findmap=sqlService.findsql(sql);
                map.put("val",findmap.get("val"));
                map.put("column",findmap.get("column"));
                map.put("isok","success");
                break;
            //修改
            case 2:
                if (sqlService.updatesql(sql)){
                    map.put("isok","successupdate");
                }
                break;
            //新增
            case 3:
                if (sqlService.updatesql(sql)){
                    map.put("isok","successinsert");
                }
                break;
            //删除
            case 4:
                if (sqlService.updatesql(sql)){
                    map.put("isok","successdelect");
                }
                break;
            default:
        }
        }catch (Exception SQLException){
            SQLException.printStackTrace();
            map.put("isok",SQLException.toString());
        }
        return map;
    }

    //导出excl
    @RequestMapping(value ="/exclSqlfind" )
    public ResponseEntity<byte[]> exclSqlfind(String sql, HttpServletResponse response){
        int is=sqlService.sqlstatic(sql);
        if (is==1){
            try {
               String path= ExcelUtils.refoundDetailExcel(sql);
                File file=new File(path);
                HttpHeaders headers = new HttpHeaders();
                String downloadFielName = new String("Sql查询结果集.xls".getBytes("UTF-8"),"iso-8859-1");
                headers.setContentDispositionFormData("attachment", downloadFielName);
                return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers , HttpStatus.CREATED);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }







}
