package com.manji.backstage.utils;

import java.util.ArrayList;
import java.util.List;

import com.manji.backstage.vo.operation.AttriVo;

import net.sf.json.JSONArray;

public class ManjiText {

	
	
	public static String attriJson(String content){
		
		
		String[] array =content.split(";");
		List<AttriVo> aList =new ArrayList<AttriVo>();
		
		for(int i=0;i<array.length;i++){
			
			String tempStr =array[i];
			String[] tempArray =tempStr.split(":");
			
			AttriVo av =new AttriVo();
			av.setTitle(tempArray[0]);
			
			if(tempArray.length>1){
				av.setValues(tempArray[1]);
			}else{
				av.setValues("");
			}
			aList.add(av);
			
			}
		
		String  resultJson =JSONArray.fromObject(aList).toString();
		System.out.println(resultJson);
		return resultJson;
	}
	
	
	public static String attriTitle(String content){
		
		if(null ==content||"".equals(content)){
			return "";
		}
		String[] array =content.split(";");
		
		String titleString ="";
		for(int i=0;i<array.length;i++){
			if(i!=0){
				titleString+=",";
			}
			String tempStr =array[i];
			String[] tempArray =tempStr.split(":");
			
			titleString+=tempArray[0];
			
		}
		
		return titleString;
	}
	
	
	public static String replaceText(String content){
		
		content =content.replace("，", ",");
		content =content.replace("：", ":");
		
		
		return content;
		
	}
}
