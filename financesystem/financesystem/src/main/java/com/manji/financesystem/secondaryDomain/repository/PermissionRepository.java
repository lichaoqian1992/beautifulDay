package com.manji.financesystem.secondaryDomain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pudding on 2017-1-16.
 */
@Repository
public class PermissionRepository {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<String> findByUserPermissions(int userId) {
        List<String> permissions = jdbcTemplate.queryForList("select t_permission.PERMISSION_NAME  from t_user_role INNER JOIN t_role on t_user_role.ROLE_ID=t_role.ID INNER JOIN t_permission on t_permission.ROLE_ID=t_role.ID where t_user_role.USER_ID=?", new Object[]{userId}, String.class);
        return permissions;
    }


}
