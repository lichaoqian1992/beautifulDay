package com.manji.financesystem.common;


/**
 * Created by luobairan on 2016/7/25.
 */
public class BaseResult extends StandardResultInfo {

    public void setFailResult(String code, String description,String errorMessage) {
        this.statusEnum = StatusEnum.FAIL;
        this.code = code;
        this.description = description;
        this.errorMessage= errorMessage;
    }

    public void setFailResult(String code, String description) {
        this.statusEnum = StatusEnum.FAIL;
        this.code = code;
        this.description = description;
    }

    public void setSuccessResult(String description){

        this.statusEnum = StatusEnum.SUCCESS;
        this.code =StatusEnum.SUCCESS.getCode();
        this.description = description;
    }

    public static BaseResult getSuccessResult(String message) {
        BaseResult result = new BaseResult();
        result.setSuccessResult(message);
        return result;
    }

    public static BaseResult getFailResult(String errorCode,String description , String errorMessage) {
        BaseResult result = new BaseResult();
        result.setFailResult(errorCode, description,errorMessage);
        return result;
    }

    public static BaseResult getFailResult(String errorCode,String description ) {
        BaseResult result = new BaseResult();
        result.setFailResult(errorCode, description);
        return result;
    }


}
