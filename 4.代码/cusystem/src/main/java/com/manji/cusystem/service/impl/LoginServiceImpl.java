package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.dao.common.Group;
import com.manji.cusystem.dao.common.Menu;
import com.manji.cusystem.dao.common.Role;
import com.manji.cusystem.feign.UServiceFeignService;
import com.manji.cusystem.service.LogService;
import com.manji.cusystem.service.LoginService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.utils.MD5util;
import com.manji.cusystem.vo.common.LogVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
@Service
public class LoginServiceImpl implements LoginService{

    private static final Logger logger = Logger.getLogger(ConversationServiceImpl.class);

    @Autowired
    private LogService service;

    @Autowired
    private UServiceFeignService uServiceFeignService;

    BaseResult result = new BaseResult();

    /**
     * 校验登录
     * @return
     */
    @Override
    public BaseResult checkUser(String userName,String password) {

        Account account = new Account();
        List<Role> roleList = new ArrayList<Role>();
        List<Menu> menuList = new ArrayList<Menu>();
        List<Group> groupList = new ArrayList<Group>();
        try{
            logger.info("=====调用checkUser接口开始，调用时间："+new DatesUtils().time()+"==============");
            //1.判断参数
            if(userName == null || "".equals(userName)){
                result.setCode("404");
                result.setContent("账号不能为空");
                return result;
            }
            if(password == null || "".equals(password)){
                result.setCode("404");
                result.setContent("密码不能为空");
                return result;
            }
            //2.处理参数
            String code = "0001";
            //password = "96E79218965EB72C92A549DD5A330112";
            String sign = MD5util.getMD5(userName+ MD5util.getMD5(password)+code+"manji");
            //3.发送请求
            JSONObject back = uServiceFeignService.checkUser(userName,MD5util.getMD5(password),code,sign);
            //4.处理返回值 {"result":"{\"result\":8,\"muen\":\"\",\"code\":\"1006\",\"role\":\"\",\"id\":5,\"prompt\":\"秘钥验证失败\",\"user\":\"\"}"}
            System.out.println(sign+"============================++++++++++++++++==");
            if(back.get("result").toString().equals("0")){
                if(back.get("code").toString().equals("1000")) {
                    //处理返回数据
                    //System.out.println(back.getJSONObject("user"));
                    JSONObject user = back.getJSONObject("user");
                    account.setUserId(user.getString("id"));
                    account.setUserName(user.getString("username"));
                    account.setRealName(user.getString("vsername"));
                    account.setMobile(user.getString("mobile"));
                    account.setEmail(user.getString("email"));
                    account.setJob(user.getString("job"));
                    account.setSessionId(back.getString("sessionId"));
                    //处理角色信息
                    JSONArray role = back.getJSONArray("role");//    JSONArray.parseArray(back.get("role").toString());
                    for (int i = 0; i < role.size(); i++) {
                        Role role1 = new Role();
                        JSONObject object = role.getJSONObject(i);
                        role1.setDeptName(object.getString("dept_name"));
                        role1.setRoleName(object.getString("role_name"));
                        roleList.add(role1);
                    }
                    //处理菜单信息
                    JSONArray menuArray = back.getJSONArray("menu");//JSONArray.parseArray(back.getString("menu"));
                    /*for (int j = 0; j < menuArray.size(); j++) {
                        Menu menu = new Menu();
                        JSONObject object = menuArray.getJSONObject(j);
                        if(object.getString("hierarchy").equals("1")){
                            if(object.getString("title").indexOf("首页")!= -1){
                                menu.setCss(MenuCss.main);
                            }else if(object.getString("title").indexOf("会话管理") != -1){
                                menu.setCss(MenuCss.chat);
                            }else if(object.getString("title").indexOf("工单管理") != -1){
                                menu.setCss(MenuCss.order);
                            }else if(object.getString("title").indexOf("客服工具") != -1){
                                menu.setCss(MenuCss.examine);
                            }else if(object.getString("title").indexOf("客户管理") != -1){
                                menu.setCss(MenuCss.user);
                            }else if(object.getString("title").indexOf("系统设置") != -1){
                                menu.setCss(MenuCss.system);
                            }else if(object.getString("title").indexOf("统计") != -1){
                                menu.setCss(MenuCss.statistics);
                            }
                            menu.setPath(object.getString("path"));
                            menu.setHierarchy(object.getString("hierarchy"));
                            menu.setSort(object.getString("sort"));
                            menu.setTitle(object.getString("title"));
                            menu.setTypeTitle(object.getString("type_title"));
                            List<Menu> menusList = new ArrayList<Menu>();
                            for(int k=0;k<menuArray.size();k++){
                                Menu menus = new Menu();
                                JSONObject objectk = menuArray.getJSONObject(k);
                                if(objectk.getString("hierarchy").equals("2")){
                                    if(objectk.getString("sort").split(",")[0].equals(object.getString("sort"))){
                                        menus.setPath(objectk.getString("path"));
                                        menus.setHierarchy(objectk.getString("hierarchy"));
                                        menus.setSort(objectk.getString("sort"));
                                        menus.setTitle(objectk.getString("title"));
                                        menus.setTypeTitle(objectk.getString("type_title"));
                                        menusList.add(menus);
                                    }
                                }
                            }
                            menu.setMenus(menusList);

                            menuList.add(menu);
                        }
                    }*/
                    //处理分组信息
                    JSONArray groupArray = back.getJSONArray("group");//JSONArray.parseArray(back.getString("group"));
                    for (int m = 0; m < groupArray.size(); m++) {
                        Group group = new Group();
                        JSONObject object = groupArray.getJSONObject(m);
                        if(object != null){

                            group.setGroupId(object.getString("id"));
                            group.setGroupName(object.getString("group_name"));
                            group.setDeptId(object.getString("dept_id"));
                            group.setGroupCount(object.getString("groupCount"));
                            groupList.add(group);
                        }
                    }
                    account.setRole(roleList);
                    account.setMenu(menuList);
                    account.setGroup(groupList);
                }
                    //5.记录日志信息
                    LogVo log = new LogVo();
                    log.setCus_type("select");
                    log.setCus_content(account.getUserName()+"在"+new DatesUtils().time()+"登录系统");
                    log.setCus_user_id(Integer.parseInt(account.getUserId()));
                    log.setCus_user_name(account.getUserName());
                    log.setCus_ctime(new DatesUtils().time());
                    log.setCus_isdel(1);
                    boolean b = service.addLog(log);
                    result.setCode("200");
                    result.setContent("登录成功");
                    result.setResult(account);
            }else if(back.get("result").toString().equals("8")){
                    result.setCode("403");
                    result.setContent(back.get("prompt").toString());
                    result.setResult(back);
            }
        }catch (Exception e){
            System.out.println(e.getClass().getName()+"====================================");
            e.printStackTrace();
            result.setCode("500");
            result.setContent("服务暂不可用！");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }

        logger.info("=====调用checkUser接口开始，结束时间："+new DatesUtils().time()+"返回值："+ JSONObject.toJSONString(result)+"==============");
        return result;
    }
}
