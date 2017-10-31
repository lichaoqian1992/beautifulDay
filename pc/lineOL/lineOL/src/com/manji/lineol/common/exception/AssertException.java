package com.manji.lineol.common.exception;

import com.manji.lineol.common.errorcode.ErrorCodeEnum;

public class AssertException extends IllegalArgumentException{
	
	 private static final long serialVersionUID = 3611026590046491155L;
	    private String errorCode;
	    private String errorMessage;

	    public AssertException() {
	        super();
	    }

	    public AssertException(ErrorCodeEnum errorCode) {
	        super(errorCode.toString());
	        this.errorCode = errorCode.getCode();
	        this.errorMessage = errorCode.getMessage();
	    }

	    public AssertException(String errorCode, String errorMessage) {
	        super(errorCode + ":" + errorMessage);
	        this.errorCode = errorCode;
	        this.errorMessage = errorMessage;
	    }

	    public String getErrorCode() {
	        return this.errorCode;
	    }

	    public void setErrorCode(String errorCode) {
	        this.errorCode = errorCode;
	    }

	    public String getErrorMessage() {
	        return this.errorMessage;
	    }

	    public void setErrorMessage(String errorMessage) {
	        this.errorMessage = errorMessage;
	    }

	    @Override
	    public String toString() {
	        return "AssertException{" +
	                "errorCode='" + errorCode + '\'' +
	                ", errorMessage='" + errorMessage + '\'' +
	                '}';
	    }

	    /**
	     * 不收集栈信息
	     * @return
	     * @see java.lang.Throwable#fillInStackTrace()
	     */
	    @Override
	    public synchronized Throwable fillInStackTrace() {
	        return this;
	    }

}
