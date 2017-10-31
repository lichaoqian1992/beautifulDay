package com.life.base;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.life.controller.ExcelController;

public class LifeConfig extends JFinalConfig{

	@Override
	/**配置JFinal的常量值，用于开发环境，会输出请求的URl、Controller、Method*/
	public void configConstant(Constants me) {
		// 配置JFinal的常量值，用于开发环境，会输出请求的URl、Controller、Method
		me.setEncoding("UTF-8");
		me.setDevMode(true);
		me.setError404View("WEB-INF/html/common/404.html");
		me.setError500View("WEB-INF/html/common/500.html");
		
	}

	@Override
	public void configRoute(Routes me) {
		//配置路由    第一个参数配置的访问controller的路径，后面的是配置返回页面的路径
		me.add("/excel",ExcelController.class,"/WEB-INF/html/manji");
		
	}

	@Override
	public void configEngine(Engine me) {
		// 配置页面公共的部分
		me.addSharedFunction("/WEB-INF/html/common/_layout.html");
		
	}

	@Override
	public void configPlugin(Plugins plu) {
		// 配置数据库
		PropKit.use("datasource.properties");//获取配置文件
		
		//MYSQL
		final String URL =PropKit.get("jdbcurl");
		final String USERNAME = PropKit.get("user");
		final String PASSWORD =PropKit.get("password");
		final Integer INITIALSIZE = PropKit.getInt("initialSize");
		final Integer MIDIDLE = PropKit.getInt("minIdle");
		final Integer MAXACTIVEE = PropKit.getInt("maxActivee");
		final String DB_TYPE = PropKit.get("db_type");
		DruidPlugin druidPlugin = new DruidPlugin(URL,USERNAME,PASSWORD);
		druidPlugin.set(INITIALSIZE,MIDIDLE,MAXACTIVEE);
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType(DB_TYPE);
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter());
		plu.add(druidPlugin);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin("wages",druidPlugin);
		activeRecordPlugin.setDialect(new MysqlDialect());
		_MapKit.Mysqlmapping(activeRecordPlugin);
		plu.add(activeRecordPlugin);
		
		
		//加载定时器配置文件
		//plu.add(new Cron4jPlugin(PropKit.use("cron.properties")));
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// 配置拦截器,这样配置以后，控制层就不需要在配置，想要取消某个控制层的拦截器，使用@clear
		me.add(new SessionInViewInterceptor());//配置Session,前端使用#(session)即可获得session
		
	}

	@Override
	public void configHandler(Handlers me) {
		// 配置项目名
		me.add(new ContextPathHandler("ctx"));//使用方法：页面中#(ctx)
		
	}

}
