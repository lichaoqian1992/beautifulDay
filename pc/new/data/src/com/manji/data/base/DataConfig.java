package com.manji.data.base;

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
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.manji.data.intercepter.LocalIntercepter;



public class DataConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants con) {
		con.setEncoding("UTF-8");
		con.setDevMode(true);
		con.setError404View("WEB-INF/html/common/404.html");
		con.setError500View("WEB-INF/html/common/500.html");
		
	}

	@Override
	public void configRoute(Routes route) {
		_RouteKit.routing(route);
		
	}

	@Override
	public void configEngine(Engine en) {
		en.addSharedFunction("/WEB-INF/html/common/_layout.html");		
	}

	@Override
	public void configPlugin(Plugins plu) {
		
		PropKit.use("datasource.properties");
		
		
		//本地数据库
		final String LOCAL_URL =PropKit.get("manji_jdbcurl");

		final String LOCAL_USERNAME = PropKit.get("manji_user");
		final String LOCAL_PASSWORD =PropKit.get("manji_password");
		final Integer LOCAL_INITIALSIZE = PropKit.getInt("manji_initialSize");
		final Integer LOCAL_MIDIDLE = PropKit.getInt("manji_minIdle");
		final Integer LOCAL_MAXACTIVEE = PropKit.getInt("manji_maxActivee");
		final String LOCAL_DB_TYPE = PropKit.get("manji_db_type");
		
		DruidPlugin localDruidPlugin = new DruidPlugin(LOCAL_URL,LOCAL_USERNAME,LOCAL_PASSWORD);
		localDruidPlugin.set(LOCAL_INITIALSIZE,LOCAL_MIDIDLE,LOCAL_MAXACTIVEE);
		
		WallFilter localWallFilter = new WallFilter();
		localWallFilter.setDbType(LOCAL_DB_TYPE);
		localDruidPlugin.addFilter(localWallFilter);  
		localDruidPlugin.addFilter(new StatFilter());  
		
		plu.add(localDruidPlugin);
		
		
		ActiveRecordPlugin localActiveRecordPlugin = new ActiveRecordPlugin("local",localDruidPlugin);
		localActiveRecordPlugin.setDialect(new MySqlServerDialect());
		localActiveRecordPlugin.setShowSql(true);
		

		
		plu.add(localActiveRecordPlugin);
		plu.add(new Cron4jPlugin(PropKit.use("cron.properties")));
		
		//平台数据库
		
		/*final String MANJI_URL =PropKit.get("manji_jdbcurl");

		final String MANJI_USERNAME = PropKit.get("manji_user");
		final String MANJI_PASSWORD =PropKit.get("manji_password");
		final Integer MANJI_INITIALSIZE = PropKit.getInt("manji_initialSize");
		final Integer MANJI_MIDIDLE = PropKit.getInt("manji_minIdle");
		final Integer MANJI_MAXACTIVEE = PropKit.getInt("manji_maxActivee");
		final String MANJI_DB_TYPE = PropKit.get("manji_db_type");
		
		DruidPlugin manjiDruidPlugin = new DruidPlugin(MANJI_URL,MANJI_USERNAME,MANJI_PASSWORD);
		manjiDruidPlugin.set(MANJI_INITIALSIZE,MANJI_MIDIDLE,MANJI_MAXACTIVEE);
		
		WallFilter manjiWallFilter = new WallFilter();
		manjiWallFilter.setDbType(MANJI_DB_TYPE);
		manjiDruidPlugin.addFilter(manjiWallFilter);  
		manjiDruidPlugin.addFilter(new StatFilter());  
		
		plu.add(manjiDruidPlugin);
		
		ActiveRecordPlugin manjiActiveRecordPlugin = new ActiveRecordPlugin("manji",manjiDruidPlugin);
		manjiActiveRecordPlugin.setDialect(new MySqlServerDialect());
		manjiActiveRecordPlugin.setShowSql(true);
		

		
		plu.add(manjiActiveRecordPlugin);*/
		
		
//		localActiveRecordPlugin.addMapping("cir_msg","msgid",Message.class);
		//定时任务
//		plu.add(new Cron4jPlugin(PropKit.use("cron.properties")));
		
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
		me.add(new SessionInViewInterceptor());
		//me.add(new LocalIntercepter());
	}

	@Override
	public void configHandler(Handlers me) {
		
		 me.add(new ContextPathHandler("ctx"));    
		
	}

}
