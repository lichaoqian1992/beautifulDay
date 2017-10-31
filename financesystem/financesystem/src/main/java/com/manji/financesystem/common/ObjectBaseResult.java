package com.manji.financesystem.common;

public class ObjectBaseResult<T> extends BaseResult{

	private static final long serialVersionUID = -6734196203391158174L;
	
	private T obj;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
	public static <T> ObjectBaseResult<T> failResult(String errorCode, String errorMessage){
		ObjectBaseResult<T> baseResult=new ObjectBaseResult<T>();
		baseResult.setFailResult(errorCode,errorMessage);
		return baseResult; 
	}
	
	public static <T> ObjectBaseResult<T> successResult(T obj){
		ObjectBaseResult<T> baseResult=new ObjectBaseResult<T>();
		baseResult.setObj(obj);
		baseResult.setSuccessResult("查询成功");
		return baseResult;
	}
	
	public static <T> ObjectBaseResult<T> successResult(){
		ObjectBaseResult<T> baseResult=new ObjectBaseResult<T>();
		baseResult.setSuccessResult("查询成功");
		return baseResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	

}
