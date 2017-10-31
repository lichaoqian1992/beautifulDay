package com.manji.elastic.servie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class FileService {

	
	
	
	
	
	public List<Record> generateGoods(){
		
		List<Record> goods =new ArrayList<Record>();
		
		List<Record> articles =new ArrayList<Record>();
		
		articles =Db.find("select  * from dt_article where channel_id=7");
		
		for(int i=0;i<articles.size();i++){
			
			Record rec =articles.get(i);
			
			
			//channel
			int channel_id =rec.get("channel_id");
			
			
			Record  channel =Db.findFirst("select * from dt_channel where id ='"+channel_id+"'" );
			if(null ==channel){
				continue;
			}
			
			String channel_title =channel.get("title");
			
			rec.set("channel_title", channel_title);
			
			
			//category
			
			int category_id =rec.getInt("category_id");
			
			String category_all =CategoryAll(category_id);
			
			rec.set("category_all", category_all);
			
			//shop
			int shop_id =rec.get("user_role_value");
			
			Record sr =Db.findFirst("select * from dt_user_role_shopinfo where id="+shop_id);
			if(null ==sr){
				continue;
			}
			String shop_name =sr.get("name");
			
			String shop_address =sr.get("address");
			
			rec.set("shop_name", shop_name);
			rec.set("shop_address", shop_address);
			
			//info
			
			int article_id =rec.get("id");
			
			Record info =Db.findFirst("select * from dt_article_info where article_id ="+article_id);
			if(null ==info){
				continue;
			}
			String market_price =info.getBigDecimal("market_price")+"";
			String sell_price =info.getBigDecimal("sell_price")+"";
			String goods_describe =info.get("goods_describe");
			String brand_id =info.get("brand")+"";
			
			Record brandinfo =Db.findFirst("select * from dt_brand where id="+brand_id);
			if(null ==brandinfo){
				continue;
			}
			
			String brand_name =brandinfo.get("name");
			rec.set("market_price", market_price);
			rec.set("sell_price", sell_price);
			rec.set("goods_describe", goods_describe);
			rec.set("brand_name", brand_name);

			
			
			goods.add(rec);
			
		}
		
		
		
		
		return goods ;
	}
	
	
	public static String CategoryAll(int category_id ){
		
//		Map<String,String> map =new HashMap<String,String>();
		String category_all ="";
		
		Record third =Db.findFirst("select * from dt_article_category where id='"+category_id+"'");
		if(null ==third){
			return "";
		}
		
		
		String class_list =third.get("class_list");
		class_list ="("+class_list.substring(1, class_list.length()-1)+")";
		
		List<Record> list = Db.find("select * from dt_article_category where id in "+class_list+" order by class_layer");
		
		for(int i =0;i<list.size();i++){
			
			Record rec =list.get(i);
			String title =rec.get("title");
			category_all +=title+"-";
			
		
			
		}
		
		category_all =category_all.substring(0,category_all.length()-1);
		
		
		
		return category_all;
	}
	
	
	
	
	
}
