package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.base.MenuCss;
import com.manji.cusystem.dao.common.Menu;
import com.manji.cusystem.feign.UServiceFeignService;
import com.manji.cusystem.service.SystemsettingsService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.utils.MD5util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-9-6.(系统设置业务逻辑层)(YYR)
 */
@Service
public class SystemsettingsServiceImpl implements SystemsettingsService{

    private Logger logger = Logger.getLogger(SystemsettingsServiceImpl.class);

    @Autowired
    private UServiceFeignService uServiceFeignService;

    private static final int deptId=5;//定义客户部门ID

    private static final String pjt_code="0001";//定义项目code

    BaseResult baseResult=new BaseResult();

    /**
     * 注册用户分配组
     * @param username
     * @param password
     * @param name
     * @param job
     * @param vsername
     * @param mobile
     * @param email
     * @param role_id
     * @param groups_id
     * @return
     */
    @Override
    @Transactional
    public BaseResult RegisteredUser(String username,String password,String name,String job,String vsername,String mobile,String email,int role_id,int[] groups_id,int dft_group) {

        boolean userisok=uServiceFeignService.RegisteredUser(username, MD5util.getMD5(password),name,job,vsername,mobile,email,role_id);//注册用户

        if (userisok){//如果注册成功了开始绑定组

            JSONObject userjson=uServiceFeignService.getUser(username,MD5util.getMD5(password));//获取刚刚注册成功的账户

            boolean isgroup=true;
            //获取刚刚注册的账户
            for (int i=0;i<groups_id.length;i++){
                isgroup=uServiceFeignService.bindGroupUser(groups_id[i],userjson.getInteger("id")); //绑定组
                if (!isgroup){
                    break;
                }
            }
            if (!isgroup){//验证分组时是否发生错误
                baseResult.setCode("500");
                baseResult.setContent("绑定组时失败");
                return baseResult;
            }

            boolean isdft=uServiceFeignService.updateGroupUserdft(userjson.getInteger("id"),dft_group);//设置默认组
            if (!isdft){//验证设置默认组时是否发生错误
                baseResult.setCode("500");
                baseResult.setContent("设置默认组时失败");
                return baseResult;
            }

            baseResult.setCode("200");
            baseResult.setContent("注册成功");
        }else{//注册账户失败
            baseResult.setCode("500");
            baseResult.setContent("注册账号失败");
        }
        baseResult.setResult("");
        return baseResult;
    }

    /**
     * 查询全部角色
     * @return
     */
    @Override
    public BaseResult findRoleByDept(int pageNum, int pageSize) {
        try {
            JSONObject jsonArray=uServiceFeignService.findRoleByDept(deptId,pageNum,pageSize);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部角色失败");
        }
        return baseResult;
    }



    /**
     * 添加角色
     * @param rolename
     * @param menu_id
     * @return
     */
    @Override
    @Transactional
    public BaseResult insertRole(String rolename,int[] menu_id) {
        try{
            int roleCount=uServiceFeignService.findRoleBydeptandnameCount(deptId,rolename);
            if (roleCount!=0){
                baseResult.setCode("500");
                baseResult.setContent("添加角色失败,已经有此角色了");
                return baseResult;
            }

            boolean isokrole=uServiceFeignService.insertRole(deptId,rolename,pjt_code);//添加角色
            if (!isokrole){//添加角色失败
                baseResult.setCode("500");
                baseResult.setContent("添加角色失败");
                return baseResult;
            }
            JSONObject role=uServiceFeignService.findRoleBydeptandname(deptId,rolename);//获取刚才添加的角色

            boolean isok=uServiceFeignService.insertRoleMenu(role.getInteger("id"),menu_id);//角色绑定权限
            if (!isok){
                baseResult.setCode("500");
                baseResult.setContent("绑定权限时失败");
                return baseResult;
            }
            baseResult.setCode("200");
            baseResult.setContent("添加角色成功");
            return baseResult;

        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("添加角色失败");
        }
        return baseResult;
    }

    /**
     * 查询全部权限
     * @return
     */
    @Override
    public BaseResult findMenus() {
        try {
            JSONArray jsonArray=uServiceFeignService.findMenus(pjt_code);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部权限失败");
        }
        return baseResult;
    }

    /**
     * 查询全部组
     * @return
     */
    @Override
    public BaseResult findGroups(int pageNum,int pageSize) {
        try {
            JSONObject jsonArray=uServiceFeignService.findGroupByDeptId(deptId,pageNum,pageSize);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部组失败");
        }
        return baseResult;
    }

    /**
     * 添加组
     * @param group_name
     * @return
     */
    @Override
    public BaseResult addGroup(String group_name) {
        boolean isok;
        try{
            isok=uServiceFeignService.insertGroup(deptId,group_name);
            if (isok){
                baseResult.setCode("200");
                baseResult.setContent("添加组成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("添加组失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("添加组失败");
        }
        return baseResult;
    }

    /**
     * 删除组
     * @param groupId
     * @return
     */
    public BaseResult delGroup(String groupId){

        if(groupId == null || "".equals(groupId)){
            baseResult.setCode("404");
            baseResult.setContent("参数groupId不能为空");
            baseResult.setResult("");
            return baseResult;
        }

        logger.info("=========开始执行delUser接口，调用时间："+new DatesUtils().time()+",参数groupId="+groupId);

        boolean flag = uServiceFeignService.delGroup(groupId);
        if(flag){
            baseResult.setCode("200");
            baseResult.setContent("删除分组成功");
            baseResult.setResult("");
        }else{
            baseResult.setCode("500");
            baseResult.setContent("删除分组失败");
            baseResult.setResult("");
        }
        logger.info("=========结束开始执行delUser接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;



    }

    /**
     * 根据分组查询该组下面的人
     * @param groupId
     * @return
     */
    @Override
    public BaseResult findUserByGroup(String groupId) {

        if(groupId == null || "".equals(groupId)){
            baseResult.setCode("404");
            baseResult.setContent("参数groupId不能为空");
            baseResult.setResult("");
            baseResult.setPage(null);
            return baseResult;
        }

        logger.info("========开始调用findUserByGroup接口，调用时间："+new DatesUtils().time()+",参数值：groupId="+groupId);

        JSONArray back = uServiceFeignService.findUserByGroup(Integer.parseInt(groupId));
        if(back.size() > 0){

            baseResult.setCode("200");
            baseResult.setContent("查询成功");
            baseResult.setResult(back);
        }else{
            baseResult.setCode("404");
            baseResult.setContent("暂无数据");
            baseResult.setResult(back);
        }

        logger.info("========结束开始调用findUserByGroup接口，结束调用时间："+new DatesUtils().time()+",返回值：result="+JSONObject.toJSONString(baseResult));

        return baseResult;
    }

    /**
     * 修改分组信息
     * @param groupId
     * @param groupName
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public BaseResult editGroup(String groupId, String groupName, String userId) {

        //1.判断参数
        if(groupId == null || "".equals(groupId) || groupName == null || "".equals(groupName)){
            baseResult.setCode("404");
            baseResult.setContent("参数groupId、groupName不能为空");
        }
        //2.记录日志信息
        logger.info("========开始调用editGroup接口，调用时间："+new DatesUtils().time()+",参数值：groupId="+groupId+",groupName="+groupName+",userId="+userId);

        //3.修改分组信息
        //3.1修改组的名称
        boolean flag1 = uServiceFeignService.updateGroup(groupName,Integer.parseInt(groupId));
        //3.2修改组中人员数量
        boolean flag2 = false;
        if(userId == null || "".equals(userId)){
            flag2 = true;
        }else{
            String[] str = userId.split(",");//1,2,3,4,
            for(int i=0;i<str.length;i++){
                if(!str[i].equals("")){
                    flag2 = uServiceFeignService.deleteGroupUserByGroupId(Integer.parseInt(str[i]),Integer.parseInt(groupId));
                }
            }
        }
        if(flag1 && flag2){
            baseResult.setCode("200");
            baseResult.setContent("修改成功");
            baseResult.setResult(true);
            baseResult.setPage(null);
        }else{
            baseResult.setCode("500");
            baseResult.setContent("修改失败，请重试或联系开发人员");
            baseResult.setResult(false);
            baseResult.setPage(null);
        }

        return baseResult;
    }

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    @Override
    public BaseResult findUserById(String userId) {

        logger.info("========开始调用findUserById接口，调用时间："+new DatesUtils().time()+",参数值：userId="+userId);

        JSONObject back = uServiceFeignService.findUserById(Integer.parseInt(userId),deptId);

        baseResult.setCode("200");
        baseResult.setContent("查询成功");
        baseResult.setResult(back);

        logger.info("========结束开始调用findUserById接口，结束调用时间："+new DatesUtils().time()+",返回值：result="+JSONObject.toJSONString(baseResult));

        return baseResult;
    }

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    @Override
    public BaseResult findMenuByRoleId(int roleId) {

        logger.info("========开始调用findMenuByRoleId接口，调用时间："+new DatesUtils().time()+",参数值：roleId="+roleId);

        JSONArray back = uServiceFeignService.findMenuByRoleId(roleId);

        if(back != null && back.size() > 0){
            baseResult.setCode("200");
            baseResult.setContent("查询成功");
            baseResult.setResult(back);
        }
        logger.info("========结束调用findMenuByRoleId接口，结束调用时间："+new DatesUtils().time()+",参数值：result="+JSONObject.toJSONString(baseResult));

        return baseResult;
    }

    /**
     * 修改角色权限
     * @param roleId
     * @param roleName
     * @param menuId
     * @return
     */
    @Transactional
    public BaseResult updateRoles(int roleId, String roleName, int[] menuId, int[] delMenuId){

        System.out.println("2222222222222222222222222222222222222");
        logger.info("========开始调用updateRoles接口，调用时间："+new DatesUtils().time()+",参数值：roleId="+roleId+" roleName="+roleName+" menuId="+menuId);
        int mlength = 0,dlength = 0;
        if(menuId != null && menuId.length > 0){
            mlength = menuId.length;
        }
        if(delMenuId != null && delMenuId.length > 0){
            dlength = delMenuId.length;
        }
        int[] addMenu = new int[mlength];
        int[] delMenu = new int[dlength];

        //1.先修改角色名称
        boolean updFlag = uServiceFeignService.updateRole(roleId,roleName);

        //2.判断删除的权限是否存在，如果存在，那么直接删除，若不存在，则不操作
        int a = 0,b = 0;
        if(delMenuId != null && delMenuId.length > 0){
            for(int i=0;i<delMenuId.length;i++){
                   //原来存在，则加入删除列表
                boolean selFlag = uServiceFeignService.checkMenuById(roleId,delMenuId[i]*-1);
                //移除数组值为0的数据
                if(selFlag){
                    a++;//表示要删除的个数
                    delMenu[i] = delMenuId[i]*-1;
                }

            }
        }
        int[] delMenus = new int[a];
        int x = 0;
        for(int j=0;j<delMenu.length;j++){

            for(int k=x;k<delMenus.length;k++){
                if(delMenu[j] != 0){
                    x++;
                    delMenus[k] = delMenu[j];
                    break;
                }
            }
        }

        //删除该权限
        boolean delFlag = uServiceFeignService.deleteMenuById(roleId,delMenus);
        //2.修改角色权限
        if(menuId != null && menuId.length > 0){
            for(int m=0;m<menuId.length;m++){
                //判断是否存在，存在就不管，不存在就新增
                if(menuId[m] != 0){
                    boolean selFlag = uServiceFeignService.checkMenuById(roleId,menuId[m]);
                    if(!selFlag){
                        b++;
                        addMenu[m] = menuId[m];
                    }
                }
            }
        }
        int[] addMenus = new int[b];
        if(b > 0){
            int y = 0;
            for(int n=0;n<addMenu.length;n++){
                for(int k=y;k<addMenus.length;k++){
                    if(addMenu[n] != 0){
                        y++;
                        addMenus[k] = addMenu[n];
                        break;
                    }
                }
            }
        }
        //新增不存在的权限
        if(addMenus.length > 0){

            boolean addFlag = uServiceFeignService.insertRoleMenu(roleId,addMenus);
            if(updFlag && addFlag && delFlag){
                baseResult.setCode("200");
                baseResult.setContent("操作成功");
                baseResult.setResult(true);
            }
        }

        logger.info("========结束开始调用updateRoles接口，结束调用时间："+new DatesUtils().time()+",返回值：result="+JSONObject.toJSONString(baseResult));

        return baseResult;
    }

    /**
     * 查询菜单信息
     * @param userId
     * @return
     */
    @Override
    public BaseResult findMenu(int userId) {

        BaseResult baseResult = new BaseResult();
        logger.info("========开始调用findMenu接口，调用时间："+new DatesUtils().time()+",参数值：roleId="+userId);

        JSONObject back = uServiceFeignService.findUserById(userId,deptId);
        System.out.println(back);
        int roleId = back.getJSONObject("role").getInteger("id");
        JSONArray menuArray = uServiceFeignService.findMenuByRoleId(roleId);
        //处理菜单信息
        List<Menu> menuList = new ArrayList<Menu>();
        for (int j = 0; j < menuArray.size(); j++) {
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
                    if(!objectk.getString("type_id").equals("2")){
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
                }
                menu.setMenus(menusList);

                menuList.add(menu);
            }
        }

        if(menuArray != null && menuArray.size() > 0){
            baseResult.setCode("200");
            baseResult.setContent("查询成功");
            baseResult.setResult(menuList);
        }
        logger.info("========结束调用findMenu接口，结束调用时间："+new DatesUtils().time()+",参数值：result="+JSONObject.toJSONString(baseResult));

        return baseResult;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public BaseResult delUser(String userId) {

        if(userId == null || "".equals(userId)){
            baseResult.setCode("404");
            baseResult.setContent("参数userId不能为空");
            baseResult.setResult("");
            return baseResult;
        }

        logger.info("=========开始执行delUser接口，调用时间："+new DatesUtils().time()+",参数userId="+userId);

        boolean flag = uServiceFeignService.delUser(userId);
        if(flag){
            baseResult.setCode("200");
            baseResult.setContent("删除用户成功");
            baseResult.setResult("");
        }else{
            baseResult.setCode("500");
            baseResult.setContent("删除用户失败");
            baseResult.setResult("");
        }
        logger.info("=========结束开始执行delUser接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;
    }

    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    @Override
    public BaseResult updatePassword(String userId, String password) {

        if(userId == null || "".equals(userId) || password == null || "".equals(password)){
            baseResult.setCode("404");
            baseResult.setContent("参数userId,password不能为空");
            baseResult.setResult("");
            return baseResult;
        }

        logger.info("=========开始执行updatePassword接口，调用时间："+new DatesUtils().time()+",参数userId="+userId+"password="+password);

        boolean flag = uServiceFeignService.updatePassword(userId,MD5util.getMD5(password));
        baseResult = detailResult(flag);
        logger.info("=========结束开始执行updatePassword接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;
    }

    /**
     * 修改用户
     * @param userId
     * @param job
     * @param mobile
     * @param email
     * @param groups_id
     * @param dft_group
     * @return
     */
    @Override
    public BaseResult updateUser(int userId, String job, String mobile, String email, int[] groups_id, int dft_group,int roleId) {

        logger.info("=========开始执行updatePassword接口，调用时间："+new DatesUtils().time()+",参数userId="+userId+job+mobile+email+groups_id+dft_group);

        //1.修改用户信息
        boolean userisok=uServiceFeignService.updateUsersById(userId,job,mobile,email);
        if (!userisok){
            baseResult.setCode("500");
            baseResult.setContent("修改账户失败");
            return baseResult;
        }

        //2.修改用户绑定角色
        boolean updaterole=uServiceFeignService.updateUser_Role(userId,roleId);
        if (!updaterole){
            baseResult.setCode("500");
            baseResult.setContent("修改角色");
            return baseResult;
        }

        //2.将此用户的绑定组全部先移出
        boolean delg=uServiceFeignService.deleteGroupUser(userId);
        if (!delg){
            baseResult.setCode("500");
            baseResult.setContent("移出组失败");
            return baseResult;
        }

        boolean isgroup=true;
        if(groups_id.length > 0){

            for (int i=0;i<groups_id.length;i++){
                isgroup=uServiceFeignService.bindGroupUser(groups_id[i],userId); //绑定组
                if (!isgroup){
                    break;
                }
            }
            if (!isgroup){//验证分组时是否发生错误
                baseResult.setCode("500");
                baseResult.setContent("绑定组时失败");
                return baseResult;
            }
        }

        boolean isdft=uServiceFeignService.updateGroupUserdft(userId,dft_group);//设置默认组
        if (!isdft){//验证设置默认组时是否发生错误
            baseResult.setCode("500");
            baseResult.setContent("设置默认组时失败");
            return baseResult;
        }

        baseResult.setCode("200");
        baseResult.setContent("修改账户成功");
        logger.info("=========结束开始执行updatePassword接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;
    }

    /**
     * 查询客服中心下全部员工
     * @return
     */
    @Override
    public BaseResult findUserByDeptId(int pageNum, int pageSize, String name) {
        try {
            JSONObject jsonArray=uServiceFeignService.findUserByDeptId(deptId,pageNum,pageSize,name);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部客服失败");
            baseResult.setResult("异常类型："+e.getClass().getName().toString());
        }

        return baseResult;
    }

    /**
     * 统一处理返回结果
     * @param flag
     * @return
     */
    public BaseResult detailResult(boolean flag){

        if(flag){
            baseResult.setCode("200");
            baseResult.setContent("修改成功");
            baseResult.setResult("");
        }else{
            baseResult.setCode("500");
            baseResult.setContent("修改失败，请联系技术人员");
            baseResult.setResult("");
        }

        return baseResult;
    }


}
