package com.manji.financesystem.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class HttpUtils {

	/**
	 * @Description :后台进行post请求(请写在代码执行结尾)
	 * @param response
	 * @param postUrl
	 * @param paramMap
	 * @throws IOException
	 */
	public static void doBgPostReq(HttpServletResponse response, String postUrl, Map<String, ?> paramMap)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<form name='postSubmit' method='post' action='" + postUrl + "' >");
		for (String key : paramMap.keySet()) {
			out.println("<input type='hidden' name='" + key + "' value='" + paramMap.get(key) + "' >");
		}
		out.println("</form>");
		out.println("<script>");
		out.println("  document.postSubmit.submit()");
		out.println("</script>");
	}

}
