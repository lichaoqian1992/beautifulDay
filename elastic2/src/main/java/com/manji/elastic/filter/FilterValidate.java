package com.manji.elastic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
public class FilterValidate implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取项目路径
		String path = req.getContextPath();
		String basePath = "//"+req.getServerName()+path+"/";
		if(isIp(req.getServerName()) || "localhost".equals(req.getServerName())){
			basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		}
		req.setAttribute("basePath", basePath);
		String origin = "*";
		
		String referer = req.getHeader("Referer");
		if(StringUtils.isNotBlank(referer)){
			String[] arr = referer.split("/");
			if(arr.length>2){
				origin = StringUtils.join(arr, "/", 0, 3);
			}
		}
		//处理跨域
		resp.setHeader("Access-Control-Allow-Origin",origin);
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,jsessionid,Content-Type");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		
		chain.doFilter(request, response);
	}
	/**
	 * 判断是否是一个IP  
	 * @param IP
	 * @return
	 */
	public boolean isIp(String IP){
		boolean b = false;
		if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){  
			String s[] = IP.split("\\.");  
			if(Integer.parseInt(s[0])<255)  
				if(Integer.parseInt(s[1])<255)  
					if(Integer.parseInt(s[2])<255)  
						if(Integer.parseInt(s[3])<255)  
							b = true;  
		}  
		return b;  
	}
}
