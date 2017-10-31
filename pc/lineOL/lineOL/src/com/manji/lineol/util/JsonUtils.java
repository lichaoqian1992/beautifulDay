package com.manji.lineol.util;

import java.util.ArrayList;
import java.util.List;

import com.manji.lineol.test.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {

	public static void main(String[] args) {
		Student student = new Student();
		student.setId("1001");
		student.setAge(12);
		student.setName("张三");
		
		Student student1 = new Student();
		student.setId("1002");
		student.setAge(13);
		student.setName("李四");
		
		List<Student> list=new ArrayList<Student>();
		list.add(student);
		list.add(student1);
		
		String array = getArray(list);
		System.out.println(array);
		
		List<Student> jsonArrayStringtoBean = jsonArrayStringtoBean(array,Student.class);
		
		System.out.println(jsonArrayStringtoBean);
		

//		String json = getJson(student);
//
//		System.out.println(json);
//
//		Student jsonStringToBean = jsonStringToBean(json, Student.class);
//
//		System.out.println(jsonStringToBean);

	}

	public static String getJson(Object obj) {

		return JSONObject.fromObject(obj).toString();

	}

	public static String getArray(Object obj) {

		return JSONArray.fromObject(obj).toString();

	}

	public static JSONObject tranTObject(String jsonStr) {

		return JSONObject.fromObject(jsonStr);

	}

	public static JSONArray tranToArrayObject(String arrayStr) {

		return JSONArray.fromObject(arrayStr);
	}

	public static <T> T jsonStringToBean(String json, Class<T> t) {
		JSONObject tranTObject = tranTObject(json);
		Object bean = JSONObject.toBean(tranTObject, t);
		return (T) bean;
	}
	
	public static <T> List<T> jsonArrayStringtoBean(String arrayJson,Class<T> t){
		JSONArray tranToArrayObject = tranToArrayObject(arrayJson);
		List<T> list = JSONArray.toList(tranToArrayObject, t);
		return list;
	}
	
	

}
