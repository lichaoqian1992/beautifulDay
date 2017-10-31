package com.manji.elastic.controller;

import com.jfinal.core.Controller;
import com.manji.elastic.model.request.pc.Goods4Query;
import com.manji.elastic.model.request.pc.GoodsQuery;
import com.manji.elastic.servie.QueryService;

public class QueryController extends Controller{

	private QueryService service =new QueryService();
	
	public void index(){
		
		renderText("QuerySB");
		
		
		
		
	}
	
	public void goods2(){
		long  t1 =System.currentTimeMillis();
		
		GoodsQuery query =getBean(GoodsQuery.class,"");
		
		String returnJson =service.queryGoods(query);
		
		long t2 =System.currentTimeMillis();
		System.out.println("  总耗费时间:  "+(t2-t1));
		renderText(returnJson);
	}
	
	
	public void goods(){
		
		long  t1 =System.currentTimeMillis();
		
		Goods4Query query =getBean(Goods4Query.class,"");
		
		String returnJson =service.query4Goods(query);
		
		long t2 =System.currentTimeMillis();
		System.out.println("  总耗费时间:  "+(t2-t1));
		renderText(returnJson);
		
		
		
	}
	
}
