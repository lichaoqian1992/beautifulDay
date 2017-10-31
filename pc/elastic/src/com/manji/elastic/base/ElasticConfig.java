package com.manji.elastic.base;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;

import com.jfinal.template.Engine;
import com.manji.elastic.controller.AppArticleController;
import com.manji.elastic.controller.AppShopController;
import com.manji.elastic.controller.BatchController;
import com.manji.elastic.controller.FileController;
import com.manji.elastic.controller.IndexController;
import com.manji.elastic.controller.PcArticleController;
import com.manji.elastic.controller.PcShopController;
import com.manji.elastic.controller.QueryController;



public class ElasticConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants con) {
		con.setEncoding("UTF-8");
		con.setDevMode(true);
		
	}

	@Override
	public void configRoute(Routes me) {

		me.add("/",IndexController.class,"/WEB-INF/html/index");
		me.add("/query",QueryController.class);
//		me.add("/batch",BatchController.class);
//		me.add("/file",FileController.class);
//		me.add("/app",AppController.class);
//		me.add("/shop",ShopController.class);
		
		
		me.add("/app/article",AppArticleController.class,"");
		me.add("/app/shop",AppShopController.class,"");
		me.add("/pc/article",PcArticleController.class,"");
		me.add("/pc/shop",PcShopController.class,"");
		
		
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins plu) {
//		PropKit.use("datasource.properties");
//
//		final String LOCAL_URL = PropKit.get("local_jdbcurl");
//
//		final String LOCAL_USERNAME = PropKit.get("local_user");
//		final String LOCAL_PASSWORD = PropKit.get("local_password");
//		final Integer LOCAL_INITIALSIZE = PropKit.getInt("local_initialSize");
//		final Integer LOCAL_MIDIDLE = PropKit.getInt("local_minIdle");
//		final Integer LOCAL_MAXACTIVEE = PropKit.getInt("local_maxActivee");
//		final String LOCAL_DB_TYPE = PropKit.get("local_db_type");
//
//		DruidPlugin localDruidPlugin = new DruidPlugin(LOCAL_URL, LOCAL_USERNAME, LOCAL_PASSWORD);
//		localDruidPlugin.set(LOCAL_INITIALSIZE, LOCAL_MIDIDLE, LOCAL_MAXACTIVEE);
//
//		WallFilter localWallFilter = new WallFilter();
//		localWallFilter.setDbType(LOCAL_DB_TYPE);
//		localDruidPlugin.addFilter(localWallFilter);
//		localDruidPlugin.addFilter(new StatFilter());
//
//		plu.add(localDruidPlugin);
//
//		ActiveRecordPlugin localActiveRecordPlugin = new ActiveRecordPlugin("circle", localDruidPlugin);
//		localActiveRecordPlugin.setDialect(new MySqlServerDialect());
//		localActiveRecordPlugin.setShowSql(true);
//	
//		plu.add(localActiveRecordPlugin);
		
		
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
