package com.manji.messageserver.common.exception;

import com.manji.messageserver.common.errorcode.ErrorCodeEnum;

/**
 * Created by pudding on 2016-12-12.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4202151263464870408L;

    private String resultCode;
    private String resultMessage;

    public ServiceException() {
        super();
    }

    public ServiceException(ErrorCodeEnum errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public ServiceException(String resultCode, String resultMessage) {
        super(resultMessage);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{resultCode:").append(resultCode).append(", resultMessage:")
                .append(resultMessage).append("}");
        return builder.toString();
    }
}
