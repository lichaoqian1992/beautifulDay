package com.manji.financesystem.shiro;

import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.repository.PermissionRepository;
import com.manji.financesystem.secondaryDomain.repository.RoleRepository;
import com.manji.financesystem.secondaryDomain.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pudding on 2017-1-13.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     *
     * @param principalCollection
     * @return
     * @see ：本例中该方法的调用时机为需授权资源被访问时
     * @see : 并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("----------------执行shior权限认证-------------------");
        //获取当前用户userName
        String userName = (String) principalCollection.getPrimaryPrincipal();
        UserDO userDO = userRepository.findByUserName(userName);
        if (userDO != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            //用户的角色集合
            simpleAuthorizationInfo.setRoles(roleRepository.findByUserRoleNames(userDO.getId()));
            //用户权限集合
            List<String> permissions = permissionRepository.findByUserPermissions(userDO.getId());
            for (String str : permissions) {
                simpleAuthorizationInfo.addStringPermission(str);
            }
            return simpleAuthorizationInfo;

        }
        //返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("----------------执行shior登录认证-------------------");
        //UsernamePasswordToken是用来存放提交的用户信息的
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        logger.info("验证当前subject的UserName={}", token.getUsername());
        //查询用户
        UserDO userDO = userRepository.findByUserName(token.getUsername());
        //用户的角色集合
        simpleAuthorizationInfo.setRoles(roleRepository.findByUserRoleNames(userDO.getId()));
        if (userDO != null) {
            return new SimpleAuthenticationInfo(userDO.getUserName(), userDO.getPassword(), getName());
        }
        return null;

    }
}
