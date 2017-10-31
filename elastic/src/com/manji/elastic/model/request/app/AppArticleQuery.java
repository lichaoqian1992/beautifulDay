package com.manji.elastic.model.request.app;

public class AppArticleQuery {

	//基础参数
	private String queryStr=""; //查询字段
	private int size =20; //每页条数
	private int pageNum =1;//当前页数
	
	//筛选条件参数
	private String cate_id="";//分类ID
	private String shop_cate_id ="";//商家分类ID
	private String brand_code ="";//品牌ID
	private String goods_id ="";//适配  商品ID
	private String area_code ="";//地区码。
	private int sale_flag =0;///折扣类型，01
	private int price_start =0; //筛选初始金额
	private int price_end  ;//筛选结束金额 ，默认不传。
	private int shop_id =0; //商家ID
	//包邮特殊逻辑
	private int ship_flag =0;//是否包邮，默认为0，1为包邮
	private String dis_area_code ="";//配送地区

	//排序方式
	private int sort_flag =0;//0默认综合排序，1，销量排序，2，按照价格降序排序，3，按照价格升序排序,4按照商家综合排序，5按照商家销量排序
	
	
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPrice_start() {
		return price_start;
	}
	public void setPrice_start(int price_start) {
		this.price_start = price_start;
	}
	public int getPrice_end() {
		return price_end;
	}
	public void setPrice_end(int price_end) {
		this.price_end = price_end;
	}
	public int getShip_flag() {
		return ship_flag;
	}
	public void setShip_flag(int ship_flag) {
		this.ship_flag = ship_flag;
	}
	public int getSale_flag() {
		return sale_flag;
	}
	public void setSale_flag(int sale_flag) {
		this.sale_flag = sale_flag;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public int getSort_flag() {
		return sort_flag;
	}
	public void setSort_flag(int sort_flag) {
		this.sort_flag = sort_flag;
	}
	public String getDis_area_code() {
		return dis_area_code;
	}
	public void setDis_area_code(String dis_area_code) {
		this.dis_area_code = dis_area_code;
	}
	public String getBrand_code() {
		return brand_code;
	}
	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getShop_cate_id() {
		return shop_cate_id;
	}
	public void setShop_cate_id(String shop_cate_id) {
		this.shop_cate_id = shop_cate_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	
	
	
}
