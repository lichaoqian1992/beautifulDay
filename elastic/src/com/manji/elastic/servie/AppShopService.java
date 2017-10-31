package com.manji.elastic.servie;

import org.apache.log4j.Logger;

import com.manji.elastic.base.IndexFinal;
import com.manji.elastic.model.request.app.AppShopQuery;
import com.manji.elastic.utils.ElasticClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AppShopService {

	private static Logger logger = Logger.getLogger(AppShopService.class);

	private static final String SHOPINDEX = IndexFinal.ShopIndex;
	private static final String HOTINDEX = IndexFinal.HotIndex;
	private static final String EXTRAINDEX = IndexFinal.ExtraIndex;

	public String queryShopList(AppShopQuery query) {

		// 获取传入的地理位置
		String location = query.getLocation();
		String lat = "";
		String lon = "";

		if ("" != location) {
			lat = location.split(",")[0];
			lon = location.split(",")[1];
		} else {
			return "{\"data\":\"地理位置未传\"}";
		}

		int from = (query.getPageNum() - 1) * query.getSize();
		int end =query.getPageNum()* query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\": {\"bool\": {\"must\": [");

		// 关键字
		if (!"".equals(query.getQueryStr())) {
			sb.append("{ \"match\": { \"name\": {\"query\":\"" + query.getQueryStr() + "\",\"operator\": \"and\"}} }");
//			sb.append("{ \"match\": { \"name\": \"" + query.getQueryStr() + "\" } }");
		}

		// 商家主营分类
		if (!"".equals(query.getBusy_id())) {
			sb.append(",{ \"match\": { \"main_business\": \"" + query.getBusy_id() + "\" } }");
		}

		// 商家分类
		if (!"".equals(query.getCate_id())) {
			sb.append(",{ \"match\": { \"scope_values\": \"" + query.getCate_id() + "\" } }");
		}

		// 是否签约
		if (0 != query.getSign_flag()) {
			sb.append(",{ \"match\": { \"is_sign_up\": \"" + query.getSign_flag() + "\" } }");
		}
		
		if(1 ==query.getOpen_flag()){
			sb.append(",{ \"match\": { \"dpkg\": \"" + 1 + "\" } }");
		}

		// //查询关键字 +商家分类
		// if (!"".equals(query.getQueryStr()) &&
		// !"".equals(query.getCate_id())) {
		//
		// sb.append("{ \"match\": { \"shopinfo_index\": \"" +
		// query.getQueryStr()
		// + "\" } },{ \"match\": { \"scope_values\": \"" + query.getCate_id() +
		// "\" } }");
		// }else {
		// if (!"".equals(query.getQueryStr())) {
		// sb.append("{ \"match\": { \"shopinfo_index\": \"" +
		// query.getQueryStr() + "\" } }");
		// }
		// if (!"".equals(query.getCate_id())) {
		// sb.append("{ \"match\": { \"scope_values\": \"" + query.getCate_id()
		// + "\" } }");
		// }
		// }
//		// 商家编号 shop_id
//		if (0 != query.getShop_id()) {
//
//			sb.append(",{\"match\": { \"shop_id\": \"" + query.getShop_id() + "\" } }");
//		}

		// 区域
		if (!"".equals(query.getArea_code())&&query.getArea_code() !=null) {

			sb.append(",{\"match\": { \"area_code\": \"" + query.getArea_code() + "\" } }");
		}

		sb.append("]");

		// 搜索附近最大区域
		if (0 == query.getDistance_max()) {

			sb.append(",\"filter\":{\"geo_distance\":{\"distance\":\"10000000m\",\"latlng\":{\"lat\":" + lat + ",\"lon\":"
					+ lon + "}}}");
		} else {

			sb.append(",\"filter\":{\"geo_distance\":{\"distance\":\"" + query.getDistance_max()
					+ "m\",\"latlng\":{\"lat\":" + lat + ",\"lon\":" + lon + "}}}");
		}

		sb.append("}}");

		// 排序方式
		if (0 == query.getSort_flag()) {

			sb.append(",\"sort\":{\"_geo_distance\":{\"latlng\":{\"lat\":\"" + lat + "\",\"lon\":\"" + lon
					+ "\"},\"order\": \"asc\",\"unit\":\"m\"}}");
		} else {
			// service_review_score
			sb.append(",\"sort\":{\"review_score\":\"desc\",\"_geo_distance\":{\"latlng\":{\"lat\":\"" + lat + "\",\"lon\":\"" + lon
					+ "\"},\"order\": \"asc\",\"unit\":\"m\"}}");
		}

		StringBuffer tempSb =new StringBuffer(sb.toString());
		
		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}");

		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);

		JSONObject returnObj = obj.getJSONObject("hits");
		
		
		
		
		//签约商家查询完毕
		int signCount =returnObj.getInt("total");
		
		
		//签约商家数量不足
		if(end >signCount){
			
			
			if(end -signCount<query.getSize()){
				
				JSONArray hits =returnObj.getJSONArray("hits");
				
				int hitsCount =0;
				
				if(null !=hits){
					hitsCount =hits.size();
				}
				
				int deCount =query.getSize() -hitsCount;
				
				tempSb.append(",\"size\": " + deCount + ",\"from\": " + 0 + "}");
			

				String tempEsReturn = loaderExtra(tempSb);

				JSONObject tempObj = JSONObject.fromObject(tempEsReturn);

				JSONObject tempReturnObj = tempObj.getJSONObject("hits");
				
				JSONArray extraHits =tempReturnObj.getJSONArray("hits");
				
				for(int i =0;i<extraHits.size();i++){
					
					JSONObject extraObj =extraHits.getJSONObject(i);
					
					returnObj.getJSONArray("hits").add(i+hitsCount, extraObj);
				}
				
			}else{
				
				int extraStart =end -signCount-query.getSize();
				
				tempSb.append(",\"size\": " + query.getSize() + ",\"from\": " + extraStart + "}");
				
				String tempEsReturn = loaderExtra(tempSb);

				JSONObject tempObj = JSONObject.fromObject(tempEsReturn);

				JSONObject tempReturnObj = tempObj.getJSONObject("hits");
				
				returnObj =tempReturnObj;
				
			}
			
			
		}
		
		
		
		
		
		
		//返回信息

		return JSONObject.fromObject(returnObj).toString();
	}

	public String queryHot(AppShopQuery query) {

		// // 获取传入的地理位置
		// String location = query.getLocation();
		// String lat = "";
		// String lon = "";
		//
		// if ("" != location) {
		// lat = location.split(",")[0];
		// lon = location.split(",")[1];
		// } else {
		// return "{\"data\":\"地理位置未传\"}";
		// }

		String area_code = query.getArea_code();
		int len = area_code.length();

		String areaCode = "";

		if (len == 0) {
			return "{\"data\":\"推荐区域码未传\"}";
		}

		switch (len) {

		case 6:
			String temp61 = area_code.substring(0, 1);
			String temp62 = area_code.substring(0, 3);

			areaCode = temp61 + " " + temp62 + " " + area_code;
			break;
		case 4:
			String temp41 = area_code.substring(0, 1);
			areaCode = temp41 + " " + area_code;
			break;
		case 2:
			if (!area_code.equals("00")) {
				areaCode = "00 " + area_code;
			} else {
				areaCode = area_code;
			}

			break;

		default:
			areaCode = "00";
		}

		int from = (query.getPageNum() - 1) * query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\": {\"bool\": {\"must\": [");

		// 关键字
		if (!"".equals(query.getQueryStr())) {
//			sb.append(",{ \"match\": { \"name\": \"" + query.getQueryStr() + "\" } }");
			sb.append("{ \"match\": { \"name\": {\"query\":\"" + query.getQueryStr() + "\",\"operator\": \"and\"}} }");
		}
		// 分类ID
		if (!"".equals(query.getCate_id())) {

			sb.append(",{ \"match\": { \"scope_values\": \"" + query.getCate_id() + "\" } }");
		}
		// 商家店铺开启状态
		if(1 ==query.getOpen_flag()){
			sb.append(",{ \"match\": { \"dpkg\": \"" + 1 + "\" } }");
		}

		// 区域

		sb.append(",{\"match\": { \"area_code\": \"" + areaCode + "\" } }");

		// if (!"".equals(query.getArea_code())) {
		//
		// sb.append(",{\"match\": { \"area\": \"" + query.getArea_code() + "\"
		// } }");
		// }

		sb.append("]");

		sb.append("}}");

		sb.append(",\"sort\":{\"hot\":\"asc\"}");

		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}");

		String esReturn = loaderHot(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);

		JSONObject returnObj = obj.getJSONObject("hits");

		return JSONObject.fromObject(returnObj).toString();

	}

	public static String loader(StringBuffer sb) {

		logger.info("==============================querybody查询语句：      " + sb.toString());

		String queryBody = sb.toString();

		queryBody = queryBody.replace("must\": [,", "must\": [");
		logger.info(queryBody);
		long t3 = System.currentTimeMillis();
		String esReturn = ElasticClient.postMethodWithQeuryBody(SHOPINDEX + "/_search", queryBody);
		long t4 = System.currentTimeMillis();

		logger.info("=======================ES返回数据：     " + esReturn);
		logger.info("=======================调起ES耗费时间:  " + (t4 - t3) + "ms");
		return esReturn;

	}

	public static String loaderHot(StringBuffer sb) {

		logger.info("==============================querybody查询语句：      " + sb.toString());

		String queryBody = sb.toString();

		queryBody = queryBody.replace("must\": [,", "must\": [");
		logger.info(queryBody);
		long t3 = System.currentTimeMillis();
		String esReturn = ElasticClient.postMethodWithQeuryBody(HOTINDEX + "/_search", queryBody);
		long t4 = System.currentTimeMillis();

		logger.info("=======================ES返回数据：     " + esReturn);
		logger.info("=======================调起ES耗费时间:  " + (t4 - t3) + "ms");
		return esReturn;

	}
	
	public static String loaderExtra(StringBuffer sb) {

		logger.info("==============================querybody查询语句：      " + sb.toString());

		String queryBody = sb.toString();

		queryBody = queryBody.replace("must\": [,", "must\": [");
		logger.info(queryBody);
		long t3 = System.currentTimeMillis();
		String esReturn = ElasticClient.postMethodWithQeuryBody(EXTRAINDEX + "/_search", queryBody);
		long t4 = System.currentTimeMillis();

		logger.info("=======================ES返回数据：     " + esReturn);
		logger.info("=======================调起ES耗费时间:  " + (t4 - t3) + "ms");
		return esReturn;

	}

}
