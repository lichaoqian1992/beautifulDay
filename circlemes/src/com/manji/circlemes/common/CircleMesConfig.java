package com.manji.circlemes.common;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class CircleMesConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants con) {

		con.setEncoding("UTF-8");
		con.setDevMode(true);
		con.setError404View("common/404.html");
		con.setError500View("common/500.html");
		
	}

	@Override
	public void configRoute(Routes route) {
		_RouteKit.routing(route);
		
	}

	@Override
	public void configEngine(Engine en) {
		
//		en.addSharedFunction("/common/_layout.html");
//		en.addSharedFunction("/common/_paginate.html");
		
	}

	@Override
	public void configPlugin(Plugins plu) {
		
		PropKit.use("datasource.properties");
		
		//平台数据源
		final String URL =PropKit.get("mj_jdbcurl");
		final String USERNAME = PropKit.get("mj_user");
		final String PASSWORD =PropKit.get("mj_password");
		final Integer INITIALSIZE = PropKit.getInt("mj_initialSize");
		final Integer MIDIDLE = PropKit.getInt("mj_minIdle");
		final Integer MAXACTIVEE = PropKit.getInt("mj_maxActivee");
		final String DB_TYPE = PropKit.get("mj_db_type");
		DruidPlugin druidPlugin = new DruidPlugin(URL,USERNAME,PASSWORD);
		druidPlugin.set(INITIALSIZE,MIDIDLE,MAXACTIVEE);
		
		WallFilter wallFilter = new WallFilter();
        wallFilter.setDbType(DB_TYPE);
        druidPlugin.addFilter(wallFilter);  
        druidPlugin.addFilter(new StatFilter());  
		
		plu.add(druidPlugin);
		
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin("manji",druidPlugin);
		activeRecordPlugin.setDialect(new SqlServerDialect());
		_MapKit.mapping(activeRecordPlugin);
		
		plu.add(activeRecordPlugin);
		
//		//本地数据源配置
//		final String LOCAL_URL =PropKit.get("local_jdbcurl");
//		final String LOCAL_USERNAME = PropKit.get("local_user");
//		final String LOCAL_PASSWORD =PropKit.get("local_password");
//		final Integer LOCAL_INITIALSIZE = PropKit.getInt("local_initialSize");
//		final Integer LOCAL_MIDIDLE = PropKit.getInt("local_minIdle");
//		final Integer LOCAL_MAXACTIVEE = PropKit.getInt("local_maxActivee");
//		final String LOCAL_DB_TYPE = PropKit.get("local_db_type");
//		
//		DruidPlugin localDruidPlugin = new DruidPlugin(LOCAL_URL,LOCAL_USERNAME,LOCAL_PASSWORD);
//		localDruidPlugin.set(LOCAL_INITIALSIZE,LOCAL_MIDIDLE,LOCAL_MAXACTIVEE);
//		
//		WallFilter localWallFilter = new WallFilter();
//		localWallFilter.setDbType(LOCAL_DB_TYPE);
//		localDruidPlugin.addFilter(localWallFilter);  
//		localDruidPlugin.addFilter(new StatFilter());  
//		
//		plu.add(localDruidPlugin);
//		
//		ActiveRecordPlugin localActiveRecordPlugin = new ActiveRecordPlugin("local",localDruidPlugin);
//		localActiveRecordPlugin.setDialect(new SqlServerDialect());
//		_MapKit.localMapping(localActiveRecordPlugin);
//		
//		plu.add(localActiveRecordPlugin);
//		
		
		
		
		
		
		//定时任务
		plu.add(new Cron4jPlugin(PropKit.use("cron.properties")));
		
	}

	@Override
	public void configInterceptor(Interceptors me) {

		
	}

	@Override
	public void configHandler(Handlers me) {

		
	}

	
	
}
