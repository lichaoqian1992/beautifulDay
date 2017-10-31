package com.manji.finance.base;

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
import com.jfinal.ext.plugin.shiro.NewShiroPlugin;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class FinanceConfig extends JFinalConfig{

	Routes rou;
	
	@Override
	public void configConstant(Constants con) {

		con.setEncoding("UTF-8");
		con.setDevMode(true);
		con.setError404View("common/404.html");
		con.setError500View("common/500.html");
		
	}

	@Override
	public void configRoute(Routes route) {
		rou =route;
		_RouteKit.routing(route);
		
		
	}

	@Override
	public void configEngine(Engine en) {
		
		en.addSharedFunction("/common/_layout.html");
		en.addSharedFunction("/common/_paginate.html");
	}

	@Override
	public void configPlugin(Plugins plu) {
		
		PropKit.use("datasource.properties");

		//SQLSERVER
		final String MJ_URL =PropKit.get("mj_jdbcurl");
		final String MJ_USERNAME = PropKit.get("mj_user");
		final String MJ_PASSWORD =PropKit.get("mj_password");
		final Integer MJ_INITIALSIZE = PropKit.getInt("mj_initialSize");
		final Integer MJ_MIDIDLE = PropKit.getInt("mj_minIdle");
		final Integer MJ_MAXACTIVEE = PropKit.getInt("mj_maxActivee");
		final String MJ_DB_TYPE = PropKit.get("mj_db_type");
		DruidPlugin MJ_druidPlugin = new DruidPlugin(MJ_URL,MJ_USERNAME,MJ_PASSWORD);
		MJ_druidPlugin.set(MJ_INITIALSIZE,MJ_MIDIDLE,MJ_MAXACTIVEE);
		WallFilter MJ_wallFilter = new WallFilter();
		MJ_wallFilter.setDbType(MJ_DB_TYPE);
		MJ_druidPlugin.addFilter(MJ_wallFilter);
		MJ_druidPlugin.addFilter(new StatFilter());
		plu.add(MJ_druidPlugin);
		ActiveRecordPlugin MJ_activeRecordPlugin = new ActiveRecordPlugin("manji",MJ_druidPlugin);
		MJ_activeRecordPlugin.setDialect(new MySqlServerDialect());
		_MapKit.SqlServermapping(MJ_activeRecordPlugin);
		plu.add(MJ_activeRecordPlugin);


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
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin("finance",druidPlugin);
		activeRecordPlugin.setDialect(new MyMysqlDialect());
		_MapKit.Mysqlmapping(activeRecordPlugin);
		plu.add(activeRecordPlugin);


		//加载定时器配置文件
		plu.add(new Cron4jPlugin(PropKit.use("cron.properties")));
		
		plu.add(new NewShiroPlugin(rou));
		
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		

		me.add(new ShiroInterceptor());		
		me.add(new SessionInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

	
	
}
