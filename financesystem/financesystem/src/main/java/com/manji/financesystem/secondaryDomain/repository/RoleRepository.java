package com.manji.financesystem.secondaryDomain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pudding on 2017-1-16.
 */
@Repository
public class RoleRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Set<String> findByUserRoleNames(int userId){
        Set<String> roles=new HashSet<String>();
        List<String> list = jdbcTemplate.queryForList("SELECT t_role.ROLE_NAME FROM t_user_role INNER JOIN t_role ON t_user_role.ROLE_ID = t_role.ID WHERE USER_ID = ?",new Object[]{userId}, String.class);
        for (String str:list) {
            roles.add(str);
        }
        return roles;
    }



}
