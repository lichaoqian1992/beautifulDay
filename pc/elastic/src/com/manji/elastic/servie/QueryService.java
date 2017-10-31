package com.manji.elastic.servie;

import com.manji.elastic.model.request.pc.Goods4Query;
import com.manji.elastic.model.request.pc.GoodsQuery;
import com.manji.elastic.utils.ElasticClient;

import net.sf.json.JSONObject;

public class QueryService {

	public String queryGoods(GoodsQuery query) {

		String returnJson ="";
		
		if(query.getP()==0){
			
			query.setP(1);
		}
		if(query.getS()==0){
			
			query.setS(20);
		}
		
		int from =query.getS()*(query.getP()-1);

	
		
		
		StringBuffer sb =new StringBuffer("{\"query\": {\"bool\": {\"must\": [");
		
		if(!"".equals(query.getQ())&&!"".equals(query.getC())){
			
			sb.append("{ \"match\": { \"article_category_index\": \""+query.getQ()+"\" } },{ \"match\": { \"class_list\": \""+query.getC()+"\" } }");
			
		}else{
			
			
			if(!"".equals(query.getQ())){
				sb.append("{ \"match\": { \"article_category_index\": \""+query.getQ()+"\" } }");
				
			}
			
			if(!"".equals(query.getC())){
				
				sb.append("{ \"match\": { \"class_list\": \""+query.getC()+"\" } }");
				
			}
			
			
			
			
		}
		
		sb.append("]}}  ,\"size\": "+query.getS()+",\"from\": "+from+"}}");
//		System.out.println(sb.toString());
		
		long t3 =System.currentTimeMillis();
		
		String esReturn =ElasticClient.postMethodWithQeuryBody("test/_search", sb.toString());
//		System.out.println(esReturn);
		long t4 =System.currentTimeMillis();
		
		System.out.println("chaxunhaoshi :"+(t4-t3));
		
		
		JSONObject obj =JSONObject.fromObject(esReturn);
		
		JSONObject returnObj =obj.getJSONObject("hits");
		
//		int total =returnObj.getInt("total");
////		int pages =total/query.getS()+1;
		
		
		
		return JSONObject.fromObject(returnObj).toString();
	}

	public String query4Goods(Goods4Query query) {
		
		String returnJson ="";
		
		if(query.getP()==0){
			
			query.setP(1);
		}
		if(query.getS()==0){
			
			query.setS(20);
		}
		
		int from =query.getS()*(query.getP()-1);

	
//		{\"query\": {\"bool\": {\"must\": [{ \"match\": { \"article_category_index\": \"衬衫\" } }],\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \"100\",\"lte\":\"200\" }}}] }}}
		
		StringBuffer sb =new StringBuffer("{\"query\": {\"bool\": {\"must\": [");
		
		if(!"".equals(query.getQ())&&!"".equals(query.getC())){
			
			sb.append("{ \"match\": { \"article_category_index\": \""+query.getQ()+"\" } },{ \"match\": { \"class_list\": \""+query.getC()+"\" } }");
			
		}else{
			
			if(!"".equals(query.getQ())){
				sb.append("{ \"match\": { \"article_category_index\": \""+query.getQ()+"\" } }");
			}
			if(!"".equals(query.getC())){
				
				sb.append("{ \"match\": { \"class_list\": \""+query.getC()+"\" } }");
			}
		}
		
		
			
		
		if(query.getShipping() ==1){
		
			sb.append("{ \"match\": { \"is_free\": \""+query.getShipping()+"\" } }");
		}
		sb.append("]");
		
		
		
		if(null !=query.getsPrice()||null !=query.getePrice()){
			
			sb.append(",\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \""+query.getsPrice()+"\",\"lte\":\""+query.getePrice()+"\" }}}]");
		}
		
		
		sb.append("}}");
		
		
		sb.append(",\"sort\": { \"article_review_score\": \"desc\"  ");
		
		if(query.getPrice_f()==1){
			sb.append(",\"article_sell_price\":\"desc\"");
		}else if(query.getPrice_f()==2){
			sb.append(",\"article_sell_price\":\"asc\"");
		}
		
		if(query.getCount_f() ==1){
			sb.append(",\"article_order_times\":\"desc\"");
		}else if(query.getCount_f() ==2){
			sb.append(",\"article_order_times\":\"asc\"");
		}
		
		sb.append("}");
		
		
		sb.append(",\"size\": "+query.getS()+",\"from\": "+from+"}}");
		
//		,\"sort\": { \"article_sell_price\": \"desc\"  }
		
		System.out.println(sb.toString());
		
		
		
		
		long t3 =System.currentTimeMillis();
		
		String esReturn =ElasticClient.postMethodWithQeuryBody("test/_search", sb.toString());
//		System.out.println(esReturn);
		long t4 =System.currentTimeMillis();
		
		System.out.println("chaxunhaoshi :"+(t4-t3));

		JSONObject obj =JSONObject.fromObject(esReturn);
		
		JSONObject returnObj =obj.getJSONObject("hits");
		
	
		return JSONObject.fromObject(returnObj).toString();

	}

	
	
	
	
	
	
	
}
