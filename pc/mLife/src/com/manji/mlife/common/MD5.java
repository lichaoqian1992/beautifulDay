package com.manji.mlife.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.BackVo;

import com.manji.mlife.utils.MD5util;
public class MD5 {

	
//	@Autowired
//	private static DBService service;
	
	/**
	 * 签名字符串
	 * @param map
	 * @return
	 * http://pay.wshop.me/tools.ashx?action=PartnerOrderCheck&partner_channel=mjLife&order_no=aaa555
	 */
	public static String sign(String action,String partner_channel,String order_no,String key){
		
		String text ="";
		String sign="";
		
		text ="action="+action+
					"&order_no="+order_no
						+"&partner_channel="+partner_channel;
		text +=key;
		
		sign =MD5util.getMD5(text);
		
		return sign;
		
	}
	
	/**
	 * 校验字符串
	 * @param map
	 * @param vo 
	 * @return
	 */
    public static boolean verify(Map<String, String> map,String signKey, BackVo vo) {
    	
        List<String> keys = new ArrayList<String>(map.keySet());

        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
        	
            String key = keys.get(i);
            
            String value = map.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        prestr+=signKey;
    	String mysign = MD5util.getMD5(prestr);
    	
    	System.out.println("------------生活平台的签名"+mysign);
    	System.out.println("------------满集网的签名"+vo.getSign());
    	
    	if(mysign.equalsIgnoreCase(vo.getSign())) {
    		
    		return true;
    	}
    	else {
    		return false;
    	}
    }

	public static String toSign(Map<String,String> map) {
		
		String sign = "";
		String num1 =(String) map.get("ismobile");
		String num2 =(String) map.get("notify_url");
		String num3 =(String) map.get("order_cate");
		String num4 =(String) map.get("order_money");
		String num5 =(String) map.get("order_no");
		String num6 =(String) map.get("order_title");
		String num7 =(String) map.get("partner_channel");
		String num8 =(String) map.get("return_url");
		String num9 =(String) map.get("session");
		String num10 =(String) map.get("timestamp");
		String num11 ="manjiwang";
		StringBuffer sb = new StringBuffer();
		sb.append("ismobile=").append(num1)
			.append("&notify_url=").append(num2)
			.append("&order_cate=").append(num3)
			.append("&order_money=").append(num4)
			.append("&order_no=").append(num5)
			.append("&order_title=").append(num6)
			.append("&partner_channel=").append(num7)
			.append("&return_url=").append(num8)
			.append("&session=").append(num9)
			.append("&timestamp=").append(num10)
			.append(num11);
					
		sign = MD5util.getMD5(sb.toString());
		
		return sign;
	}

	
	
	@SuppressWarnings("rawtypes")
	public static String generateSign(RedirectAttributes attr){
		Map map =attr.asMap();
		String sign ="";
		
		String num1 =(String) map.get("ismobile");
		String num2 =(String) map.get("notify_url");
		String num3 =(String) map.get("order_cate");
		String num4 =(String) map.get("order_money");
		String num5 =(String) map.get("order_no");
		String num6 =(String) map.get("order_title");
		String num7 =(String) map.get("partner_channel");
		String num8 =(String) map.get("return_url");
		String num9 =(String) map.get("session");
		String num10 =(String) map.get("timestamp");
		StringBuffer sb = new StringBuffer();
		sb.append("ismobile=").append(num1)
			.append("&notify_url=").append(num2)
			.append("&order_cate=").append(num3)
			.append("&order_money=").append(num4)
			.append("&order_no=").append(num5)
			.append("&order_title=").append(num6)
			.append("&partner_channel=").append(num7)
			.append("&return_url=").append(num8)
			.append("&session=").append(num9)
			.append("&timestamp=").append(num10)
			.append("manjiwang");
					
		sign = MD5util.getMD5(sb.toString());
		System.out.println(sb.toString()+"------"+sign);
//		attr.addAttribute("sign", sign);
		
		return sign;
	}
	
//	public static void main(String[] args ){
//		String str ="&not";
//		System.out.println(toSign(str));
//
//	}
	
}
