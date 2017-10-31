package com.manji.finance.base;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.system.SystemRepository;

public class ShiroRealm extends AuthorizingRealm{
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("--------------执行登录权限验证开始-------------------");
		/**本来这里应该根据当前登录人来判断登录人的角色和权限，但是在本项目中，只有出纳拥有权限，所以不比查询数据库，直接写死*/
		/*Set<String> roles = new HashSet<String>();//角色
		Set<String> permissions = new HashSet<String>();//权限
		
		roles.add("admin");//出纳
		permissions.add("/finance/rec/confirmRec");//确定充值的实现方法
		
		//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        System.out.println("--------------执行登录权限验证结束-------------------");
        return simpleAuthorizationInfo;*/
        
		Set<String> roles = new HashSet<String>();//角色
		//Set<String> permissions = new HashSet<String>();//权限
		//获取登录的用户名
		String userName = (String) arg0.getPrimaryPrincipal();
		Record user = new SystemRepository().findByUserName(userName);
		if(user != null){
			
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            roles = new SystemRepository().findRoleByUserName(userName);
          //查询用户角色
            simpleAuthorizationInfo.setRoles(roles);
            return simpleAuthorizationInfo;
		}
		
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("--------------执行登录验证-------------------");
		//执行登录验证
		//UsernamePasswordToken是用来存放提交的用户信息的
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //查询用户
        Record user = new SystemRepository().findByUserName(token.getUsername());
		if (user==null){
			return null;
		}
        //用户的角色集合
        if (user != null) {
        	simpleAuthorizationInfo.setRoles(new SystemRepository().findRoleByUserName(user.get("USERNAME").toString()));
            return new SimpleAuthenticationInfo(user.get("USERNAME"), user.get("PASSWORD"), getName());
        }
        
        return null;
	}

}
