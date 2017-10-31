package com.manji.elastic.servie;

import org.apache.log4j.Logger;

import com.manji.elastic.base.IndexFinal;
import com.manji.elastic.model.request.pc.PcArticleQuery;
import com.manji.elastic.utils.ElasticClient;

import net.sf.json.JSONObject;

public class PcArticleService {

	private static Logger logger = Logger.getLogger(PcArticleService.class);
	
	private static final String GOODSINDEX =  IndexFinal.ArticleIndex;;

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

	public String queryArticle(PcArticleQuery query) {

		int from = (query.getPageNum() - 1) * query.getSize();

		StringBuffer sb = new StringBuffer("{\"query\": {\"bool\": {\"must\": [");

//		// 关键字+分类ID
//		if (!"".equals(query.getQueryStr()) && !"".equals(query.getCate_id())) {
//
//			sb.append("{ \"match\": { \"article_category_index\": \"" + query.getQueryStr()
//					+ "\" } },{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
//		} else {
//			if (!"".equals(query.getQueryStr())) {
//				sb.append("{ \"match\": { \"article_category_index\": \"" + query.getQueryStr() + "\" } }");
//			}
//			if (!"".equals(query.getCate_id())) {
//
//				sb.append("{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
//			}
//		}

		
		//关键字
		if (!"".equals(query.getQueryStr())) {
//			sb.append("{ \"match\": { \"article_category_index\": \"" + query.getQueryStr() + "\" } }");
			sb.append("{ \"match\": { \"article_category_index\": {\"query\":\"" + query.getQueryStr() + "\",\"operator\": \"and\"}} }");
		}
		
		//分类ID
		if (!"".equals(query.getCate_id())) {
			sb.append(",{ \"match\": { \"class_list\": \"" + query.getCate_id() + "\" } }");
		}
		
		
		if (query.getShip_flag() != 0) {

			sb.append(",{ \"match\": { \"is_free\": \"" + query.getShip_flag() + "\" } }");
		}
		if(query.getSale_flag() !=0){
			
			sb.append(",{ \"match\": { \"article_activity_type\": \"" + "1 2 3 4 5 6 7 8" + "\" } }");
		}
		
		if(!"".equals(query.getArea_code())){
			
			sb.append(",{ \"match\": { \"article_distribution_area\": \"" + "1 " + query.getArea_code() + "\" } }");
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

		// 排序方式
		sb.append(",\"sort\": {");

		switch (query.getSort_flag()) {

		case 1:
			sb.append("\"article_order_times\": \"desc\"");
			break;
		case 2:
			sb.append("\"article_sell_price\":\"asc\"");
			break;
		case 3:
			sb.append("\"article_sell_price\":\"desc\"");
			break;
		default:
			sb.append("\"article_review_score\": \"desc\"");
			break;
		}

		sb.append("}");

		// 分页
		sb.append(",\"size\": " + query.getSize() + ",\"from\": " + from + "}}");

		String esReturn = loader(sb);

		JSONObject obj = JSONObject.fromObject(esReturn);
		JSONObject returnObj = obj.getJSONObject("hits");

		return JSONObject.fromObject(returnObj).toString();
	}

}
