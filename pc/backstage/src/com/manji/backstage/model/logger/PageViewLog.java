package com.manji.backstage.model.logger;


/**
 * 页面访问日志表 dt_page_view_log
 * @author Administrator
 *
 */
public class PageViewLog {

	long id;
	int user_id;
	String title;
	String url;
	String from_url;
	String ip;
	String view_time;
	int view_type;
	String view_browser;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url.trim();
	}
	public String getFrom_url() {
		return from_url;
	}
	public void setFrom_url(String from_url) {
		this.from_url = from_url.trim();
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip.trim();
	}
	public String getView_time() {
		return view_time;
	}
	public void setView_time(String view_time) {
		this.view_time = view_time.trim();
	}
	public int getView_type() {
		return view_type;
	}
	public void setView_type(int view_type) {
		this.view_type = view_type;
	}
	public String getView_browser() {
		return view_browser;
	}
	public void setView_browser(String view_browser) {
		this.view_browser = view_browser.trim();
	}
	
	
	
	
	
}
