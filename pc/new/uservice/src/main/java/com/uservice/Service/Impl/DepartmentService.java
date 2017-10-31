package com.uservice.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.uservice.Dto.Department;
import com.uservice.Dto.Group;
import com.uservice.Dto.User;
import com.uservice.Mapper.DepartmentMapper;
import com.uservice.Mapper.GroupMapper;
import com.uservice.Service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private GroupMapper groupMapper;

    /**
     * 查询全部部门
     * @return
     */
    @Override
    public List<Department> findAllDepartment() {
        return departmentMapper.findAllDepartment();
    }

    /**
     * 添加部门
     * @param deptname
     * @return
     */
    @Override
    public boolean insertDepartment(String deptname) {
        Department department=new Department();
        department.setDeptname(deptname);
        try {
            departmentMapper.insertDepartment(department);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * 通过部门id查询此部门下全部用户信息
     * @param departmentId
     * @return
     */
    @Override
    public JSONArray findUserByDeptId(int departmentId) {
        List<User> userList=departmentMapper.findUserByDeptId(departmentId);

        for (int i=0;i<userList.size();i++){
            String group_name="";
            List<Group> groupList=groupMapper.findGroupByUserIdDft(userList.get(i).getId());//查询此用户默认组
            if (groupList.size()==0){//如果此用户暂时还没有组
                group_name="没有加入任何组";
            }else{//开始处理组名称
                for (int j=0;j<groupList.size();j++){
                    group_name+=groupList.get(j).getGroup_name()+",";
                }
                group_name=group_name.substring(0,group_name.length()-1);
            }
            userList.get(i).setGroup_name(group_name);
        }

        String j=JSONArray.toJSONString(userList, SerializerFeature.WriteMapNullValue);
        JSONArray jsonArray=JSONArray.parseArray(j);//转换成jsonArray
        return jsonArray;
    }
}
