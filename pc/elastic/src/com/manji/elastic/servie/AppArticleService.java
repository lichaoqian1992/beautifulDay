package com.manji.elastic.servie;

import org.apache.log4j.Logger;

import com.manji.elastic.base.IndexFinal;
import com.manji.elastic.model.request.app.AppArticleQuery;
import com.manji.elastic.model.request.app.AppShopArticleQuery;
import com.manji.elastic.utils.ElasticClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AppArticleService {

	
	private static Logger logger = Logger.getLogger(AppArticleService.class);
	
	private static final String GOODSINDEX = IndexFinal.ArticleIndex;

	// 公共调起ES服务方法
	public static String loader(StringBuffer sb) {

		logger.info("==============================querybody查询语句：      " + sb.toString());

		String queryBody = sb.toString();

		queryBody = queryBody.replace("must\": [,", "must\": [");
		logger.info(queryBody);
		long t3 = System.currentTimeMillis();

		String esReturn = ElasticClient.postMethodWithQeuryBody(GOODSINDEX + "/_search", queryBody);
		long t4 = System.currentTimeMillis();
		logger.info("==============================ES调用耗费时间 :     " + (t4 - t3));

		logger.info("==============================ES返回数据：     " + esReturn);
		return esReturn;

	}

	/**
	 * 综合查询商品
	 * 
	 * @param query
	 * @return
	 */
	public String queryArticle(AppArticleQuery query) {

		int from = (query.getPageNum() - 1) * query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\": {\"bool\": {\"must\": [");



		
		//关键字
		if (!"".equals(query.getQueryStr())) {
//			sb.append("{ \"match\": { \"article_title\": \"" + query.getQueryStr() + "\" } }");
			sb.append("{ \"match\": { \"article_title\": {\"query\":\"" + query.getQueryStr() + "\",\"operator\": \"and\"}} }");
		}
		
		if (!"".equals(query.getCate_id())) {
			
			sb.append(",{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
		}
		
		// 是否包邮逻辑段
		if (query.getShip_flag() == 1) {

			sb.append(",{ \"match\": { \"is_free\": \"" + query.getShip_flag() + "\" } }");
			if (!"".equals(query.getDis_area_code())) {
				sb.append(",{ \"match\": { \"article_freeshipping_area\": \"" + "1 " + query.getDis_area_code()
						+ "\" } }");
			} else {

				sb.append(",{ \"match\": { \"article_freeshipping_area\": \"" + "1 " + "\" } }");
			}

			// sb.append(",{ \"match\": { \"article_freeshipping_area\": \"" +
			// query.getDis_area_code() + "\" } }");
		} else if (!"".equals(query.getDis_area_code())) {

			sb.append(",{ \"match\": { \"article_distribution_area\": \"" + "1 " + query.getDis_area_code() + "\" } }");

		}

		// 折扣
		if (query.getSale_flag() != 0) {

			sb.append(",{ \"match\": { \"article_activity_type\": \"" + "1 2 3 4 5 6 7 8" + "\" } }");
		}

		// 商家区域
		if (!"".equals(query.getArea_code())) {

			sb.append(",{ \"match\": { \"left_shop_send_area\": \"" + query.getArea_code() + "\" } }");
		}

		// 品牌ID
		if ("" != query.getBrand_code()) {

			sb.append(",{ \"match\": { \"article_brand_id\": \"" + query.getBrand_code() + "\" } }");

		}

		sb.append("]");

		// 商品价格过滤
		sb.append(",\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \"" + query.getPrice_start() + "\"");

		if (0 != query.getPrice_end()) {

			sb.append(",\"lte\":\"" + query.getPrice_end() + "\" }}}]");

		} else {

			sb.append("}}}]");
		}

		sb.append("}");
		// if (query.getSale_flag() != 0) {
		//
		// sb.append(",\"must_not\": [{\"match\": {\"article_activity_type\":
		// \"None\"}]");
		// }

		sb.append("}");

		
//		if(0!=query.getSort_flag()){
		// 排序方式
		sb.append(",\"sort\": {");

		
		
		switch (query.getSort_flag()) {

		case 1:
			sb.append("\"article_order_times\": \"desc\"");
			break;
		case 2:
			sb.append("\"article_sell_price\":\"desc\"");
			break;
		case 3:
			sb.append("\"article_sell_price\":\"asc\"");
			break;
		case 4:
			sb.append("\"shop_review_score\":\"desc\"");
			break;
		case 5:
			sb.append("\"shop_order_times\":\"desc\"");
			break;
		
		default:
			sb.append("\"article_review_score\": \"desc\"");
			break;
		}

		sb.append("}");
		
//	}

		// 分页
		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "");

//		if("".equals(query.getCate_id())){
//			sb.append(",\"min_score\": 20");
//		}
		sb.append("}");
		
		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);
		JSONObject returnObj = obj.getJSONObject("hits");

		return JSONObject.fromObject(returnObj).toString();

	}

	public String querySatisfiedArticle(AppArticleQuery query) {

		int from = (query.getPageNum() - 1) * query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\":  { \"match_all\": {} }");

		sb.append(",\"sort\": [{\"article_order_times\":{\"order\":\"desc\"}},{\"article_review_score\":{\"order\":\"desc\"}}]");

		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}");

		System.out.println("==============================querybody查询语句：      " + sb.toString());

		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);

		JSONObject returnObj = obj.getJSONObject("hits");

		return JSONObject.fromObject(returnObj).toString();

	}

	public String querySimilarArticle(AppArticleQuery query) {

		int from = (query.getPageNum() - 1) * query.getSize();
		StringBuffer sb = new StringBuffer(
				"{\"query\": {\"bool\": {\"must\": [{\"match\": {\"article_id\":\"" + query.getGoods_id() + "\"}}]}}}");

		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);

		JSONObject returnObj = obj.getJSONObject("hits");

		JSONArray array = returnObj.getJSONArray("hits");

		JSONObject hit = array.getJSONObject(0);

		String cate_id = hit.getJSONObject("_source").getString("article_category_id");

		StringBuffer sb2 = new StringBuffer(
				"{\"query\": {\"bool\": {\"must\": [{\"match\": {\"article_category_id\":\"" + cate_id + "\"}}");

		
		if(0!=query.getShop_id()){
			sb2.append(",{ \"match\": { \"shop_id\": \"" + query.getShop_id() + "\" } }");
		}
		
		sb2.append("]}}");
		sb2.append(",\"sort\":[{\"article_add_time\":{\"order\":\"desc\"}}]");

		sb2.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}");

		String esReturn2 = loader(sb2);

		JSONObject obj2 = JSONObject.fromObject(esReturn2);

		JSONObject returnObj2 = obj2.getJSONObject("hits");

		return JSONObject.fromObject(returnObj2).toString();

	}

	public String queryArticleGroupByShop(AppArticleQuery query) {

		int from = (query.getPageNum() - 1) * query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\": {\"bool\": {\"must\": [");

//		boolean query_flag = false;
		
		
		
//		// 关键字+分类ID
//		if (!"".equals(query.getQueryStr()) && !"".equals(query.getCate_id())) {
//
//			sb.append("{ \"match\": { \"shop_name\": \"" + query.getQueryStr()
//					+ "\" } },{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
//			query_flag = true;
//		} else {
//
//			if (!"".equals(query.getQueryStr())) {
//				sb.append("{ \"match\": { \"shop_name\": \"" + query.getQueryStr() + "\" } }");
//			}
//			if (!"".equals(query.getCate_id())) {
//
//				sb.append("{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
//			}
//			query_flag = true;
//		}
		// 关键字+分类ID
		if (!"".equals(query.getQueryStr())) {
//			sb.append("{ \"match\": { \"shop_name\": \"" + query.getQueryStr() + "\" } }");
			sb.append("{ \"match\": { \"shop_name\": {\"query\":\"" + query.getQueryStr() + "\",\"operator\": \"and\"}} }");
		}
		
		//分类ID
		if (!"".equals(query.getCate_id())) {

			sb.append(",{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
		}

		// 商家分类
		if ("" != query.getShop_cate_id()) {

			sb.append(",{\"match\": { \"scope_values\": \"" + query.getShop_cate_id() + "\" } }");
		}

		// 是否包邮逻辑段
		if (query.getShip_flag() == 1) {

			sb.append(",{ \"match\": { \"is_free\": \"" + query.getShip_flag() + "\" } }");
			if ("".equals(query.getDis_area_code())) {

				sb.append(",{ \"match\": { \"article_freeshipping_area\": \"" + "1" + "\" } }");
			} else {
				sb.append(",{ \"match\": { \"article_freeshipping_area\": \"" + "1 " + query.getDis_area_code()
						+ "\" } }");

			}

			// sb.append(",{ \"match\": { \"article_freeshipping_area\": \"" +
			// query.getDis_area_code() + "\" } }");
		} else if (!"".equals(query.getDis_area_code())) {

			sb.append(",{ \"match\": { \"article_distribution_area\": \"" + "1 " + query.getDis_area_code() + "\" } }");

		}

		// 折扣
		if (query.getSale_flag() != 0) {

			sb.append(",{ \"match\": { \"article_activity_type\": \"" + "1 2 3 4 5 6 7 8" + "\" } }");
		}

		// 商家区域
		if (!"".equals(query.getArea_code())) {

			sb.append(",{ \"match\": { \"left_shop_send_area\": \"" + query.getArea_code() + "\" } }");
		}

		// 品牌ID
		if ("" != query.getBrand_code()) {

			sb.append(",{ \"match\": { \"article_brand_id\": \"" + query.getBrand_code() + "\" } }");

		}

		sb.append("]");

		// 商品价格过滤
		sb.append(",\"filter\": [{ \"range\": { \"article_sell_price\": { \"gte\": \"" + query.getPrice_start() + "\"");

		if (0 != query.getPrice_end()) {

			sb.append(",\"lte\":\"" + query.getPrice_end() + "\" }}}]");

		} else {

			sb.append("}}}]");
		}

		sb.append("}}");

		sb.append(
				",\"collapse\":{\"field\":\"shop_id\",\"inner_hits\":{\"name\":\"top_rated\",\"size\":2,\"sort\":[{\"article_review_score\":\"desc\"}]}}");

		
		if (0!=query.getSort_flag()) {
		// 排序方式
		sb.append(",\"sort\": {");

		switch (query.getSort_flag()) {

		case 1:
			sb.append("\"article_order_times\": \"desc\"");
			break;
		case 2:
			sb.append("\"article_sell_price\":\"desc\"");
			break;
		case 3:
			sb.append("\"article_sell_price\":\"asc\"");
			break;
		case 4:
			sb.append("\"shop_review_score\":\"desc\"");
			break;
		case 5:
			sb.append("\"shop_order_times\":\"desc\"");
			break;
		default:
			sb.append("\"article_review_score\": \"desc\"");
			break;
		}

		sb.append("}");
		}
		
		// 分页
		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}}");

		// String sb
		// ="{\"query\":{\"match_all\":{}},\"collapse\":{\"field\":\"shop_id\",\"inner_hits\":{\"name\":\"top_rated\",\"size\":2,\"sort\":[{\"article_review_score\":\"desc\"}]}},\"sort\":[{\"article_review_score\":{\"order\":\"desc\"}}],\"size\":10,\"from\":0}";

		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);
		JSONObject returnObj = obj.getJSONObject("hits");

		return JSONObject.fromObject(returnObj).toString();

	}

	public String queryArticleByShop(AppShopArticleQuery query) {

		int from = (query.getPageNum() - 1) * query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\": {\"bool\": {\"must\": [");

		// 关键字+分类ID
		if (!"".equals(query.getQueryStr()) && !"".equals(query.getShop_cate_id())) {

			sb.append("{ \"match\": { \"article_category_index\": \"" + query.getQueryStr()
					+ "\" } },{ \"match\": { \"article_user_category_id\": \"" + query.getShop_cate_id() + "\" } }");

		} else {

			if (!"".equals(query.getQueryStr())) {
				sb.append("{ \"match\": { \"article_category_index\": \"" + query.getQueryStr() + "\" } }");
			}
			if (!"".equals(query.getShop_cate_id())) {

				sb.append("{ \"match\": { \"article_user_category_id\": \"" + query.getShop_cate_id() + "\" } }");
			}
		}

		// 折扣
		if (!"".equals(query.getAct_flag())) {

			sb.append(",{ \"match\": { \"article_activity_type\": \"" + query.getAct_flag() + "\" } }");
		}

		// 制定商家
		if (query.getShop_id() != 0) {

			sb.append(",{ \"match\": { \"shop_id\": \"" + query.getShop_id() + "\" } }");
		}

		sb.append("]");

		sb.append("}}");
		
		
//		if (0!=query.getSort_flag()) {
		// 排序方式
		sb.append(",\"sort\": {");

		switch (query.getSort_flag()) {

		case 1:
			sb.append("\"article_order_times\": \"desc\"");
			break;
		case 2:
			sb.append("\"article_sell_price\":\"desc\"");
			break;
		case 3:
			sb.append("\"article_sell_price\":\"asc\"");
			break;
		case 4:
			sb.append("\"article_add_time\":\"desc\"");
			break;
		default:
			sb.append("\"article_review_score\": \"desc\"");
			break;
		}

		sb.append("}");
//		}
		// 分页
		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}}");

		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);
		JSONObject returnObj = obj.getJSONObject("hits");

		return JSONObject.fromObject(returnObj).toString();
	}

}
