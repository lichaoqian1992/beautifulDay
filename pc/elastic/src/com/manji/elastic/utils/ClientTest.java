package com.manji.elastic.utils;

public class ClientTest {

//	private static final String index = "shop";
//	private static final String index ="shop_hot";
//	private static final String index ="shop_extra";
	 private static final String index ="article";

	public static void main(String[] args) {

//		 String str ="{\"size\":100,\"query\": {\"bool\": {\"must\": [{ \"match\": {\"article_title\": \"*钢琴*\"}}]}}}";
		 
//		 String str="{\"query\": {\"bool\": {\"must\": [{ \"match\":{\"article_activity_type\": \"1 2 3\" }},{ \"match\": { \"shop_id\": \"2144937\" } }]}},\"sort\": {\"article_review_score\": \"desc\"},\"size\": 20,\"from\": 0}";
//		String str ="{\"query\":{\"wildcard\":{\"article_category_index\":\"*钢琴*\"}}}";
		// String str ="{\"query\":{\"bool\":
		// {\"must\":[\"filter\":{\"geo_distance\":{\"distance\":\"1km\",
		// \"distance_type\":
		// \"sloppy_arc\",\"location\":{\"lat\":\"29.63\",\"lon\":\"106.54\"}}}}}}";
		// String str ="{\"query\": {\"bool\": {\"must\": [],\"filter\":
		// [{\"geo_distance\":{\"distance\":\"1km\",\"distance_type\":\"sloppy_arc\",\"latlog\":{\"lat\":29.63,\"lon\":106.54}}}]}},\"sort\":
		// {\"article_review_score\": \"desc\"},\"size\": 20,\"from\": 0}}";

//		 String str
//		 ="{\"query\":{\"match\":{\"article_category_id\":\"800\"}},\"collapse\":{\"field\":\"shop_id\"},\"size\":10,\"from\":0}";

		String str = "{\"query\": {\"bool\": {\"must\": [{ \"match\": { \"article_category_index\": \"食友香菇豆干\" } }]}}}";
//商家商品
//		 String str ="{\"query\":{\"match_all\":{}},\"collapse\":{\"field\":\"shop_id\",\"inner_hits\":{\"name\":\"top_rated\",\"size\":2,\"sort\":[{\"article_review_score\":\"desc\"}]}},\"sort\":[{\"article_review_score\":{\"order\":\"desc\"}}],\"size\":10,\"from\":0}";
		 
		
		
//		String str="{\"query\":{\"match_all\":{}},\"sort\":{\"_geo_distance\":{\"latLog\":{\"lat\":\"29.63\",\"lon\":\"106.54\"},\"order\": \"asc\",\"unit\":\"m\"}}}";
		 
//		\"filter\": {\"geo_distance\": {\"distance\":\"1000m\",\"distance_type\": \sloppy_arc\",\"latLog\": {\"lat\":  29.63,\lon\": 106.54}}}
		
//		String str ="{\"aggs\":{\"rings\":{\"geo_distance\":{\"field\":\"latLog\",\"distance_type\" : \"plane\",\"origin\":\"29.63,106.54\",\"unit\":\"m\",\"ranges\":[{\"to\":1000}]}}}}";
		
//		String str ="{\"query\": {\"bool\": {\"must\": [],\"filter\":{\"geo_distance\":{\"distance\":\"500m\",\"latlng\":{\"lat\":29.579734,\"lon\":106.538809}}} }},\"sort\":{\"_geo_distance\":{\"latlng\":{\"lat\":\"29.579734\",\"lon\":\"106.538809\"},\"order\": \"asc\",\"unit\":\"m\"}},\"size\": 3,\"from\": 0}";
	
//		String str ="{\"query\": {\"bool\": {\"must\": [],\"filter\":{\"geo_distance\":{\"distance\":\"500m\",\"latlng\":{\"lat\":29.579734,\"lon\":106.538809}}} }} }";
		
//		String str ="{\"query\": {\"bool\": {\"must\": [{\"match\":{\"shop_id\":\"2144402\"}}]}}}";
		
		
//		String str ="{\"text\":\"最好的夹克\"}";
//		System.out.println(str);
		
		
//		String str ="{\"query\": {\"bool\": {\"must\": [],\"filter\":{\"geo_distance\":{\"distance\":\"1000m\",\"latlng\":{\"lat\":29.578736,\"lon\":106.540318}}}}},\"sort\":{\"_geo_distance\":{\"latlng\":{\"lat\":\"29.578736\",\"lon\":\"106.540318\"},\"order\": \"asc\",\"unit\":\"m\"}},\"size\": 20,\"from\": 0}";
//		String str="{\"query\": {\"bool\": {\"must\": [{\"match\":{\"main_business\":\"1027 1028 1080\"}}],\"filter\":{\"geo_distance\":{\"distance\":\"10km\",\"latlng\":{\"lat\":29.578736,\"lon\":106.540318}}}}}}";
		
		String returnJson = ElasticClient.postMethodWithQeuryBody(index + "/_search?pretty", str);
//		String returnJson = ElasticClient.postMethodWithQeuryBody(index + "/_analyze?analyzer=ik_smart&pretty=true'", str);


		System.out.println(returnJson);

	}

}
