package com.manji.sheet.base;

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
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.manji.sheet.intercepter.LocalIntercepter;


public class SheetConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants con) {
		con.setEncoding("UTF-8");
		// con.setDevMode(true);
		con.setError404View("WEB-INF/404.html");
		con.setError500View("WEB-INF/500.html");
		con.setMaxPostSize(100000000);//设置post的最大上传文件为100M
	}

	@Override
	public void configRoute(Routes route) {
		
		_RouteKit.routing(route);

	}

	@Override
	public void configEngine(Engine en) {
		en.addSharedFunction("/WEB-INF/html/common/_pc_layout.html");

	}

	@Override
	public void configPlugin(Plugins plu) {
		PropKit.use("datasource.properties");

		final String LOCAL_URL = PropKit.get("local_jdbcurl");

		final String LOCAL_USERNAME = PropKit.get("local_user");
		final String LOCAL_PASSWORD = PropKit.get("local_password");
		final Integer LOCAL_INITIALSIZE = PropKit.getInt("local_initialSize");
		final Integer LOCAL_MIDIDLE = PropKit.getInt("local_minIdle");
		final Integer LOCAL_MAXACTIVEE = PropKit.getInt("local_maxActivee");
		final String LOCAL_DB_TYPE = PropKit.get("local_db_type");

		DruidPlugin localDruidPlugin = new DruidPlugin(LOCAL_URL, LOCAL_USERNAME, LOCAL_PASSWORD);
		localDruidPlugin.set(LOCAL_INITIALSIZE, LOCAL_MIDIDLE, LOCAL_MAXACTIVEE);

		WallFilter localWallFilter = new WallFilter();
		localWallFilter.setDbType(LOCAL_DB_TYPE);
		localDruidPlugin.addFilter(localWallFilter);
		localDruidPlugin.addFilter(new StatFilter());

		plu.add(localDruidPlugin);

		ActiveRecordPlugin localActiveRecordPlugin = new ActiveRecordPlugin("circle", localDruidPlugin);
		localActiveRecordPlugin.setDialect(new MySqlServerDialect());
		localActiveRecordPlugin.setShowSql(true);

		plu.add(localActiveRecordPlugin);

		// localActiveRecordPlugin.addMapping("cir_msg","msgid",Message.class);
		// 定时任务
//		plu.add(new Cron4jPlugin(PropKit.use("cron.properties")));

	}

	@Override
	public void configInterceptor(Interceptors me) {

		me.add(new Tx());//添加事务控制
		me.add(new SessionInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {

		me.add(new ContextPathHandler("ctx"));

	}

}
