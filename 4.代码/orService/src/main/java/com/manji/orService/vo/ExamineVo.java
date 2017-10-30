package com.manji.orService.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ExamineVo {

    //工单id
    @NotBlank(message = "参数[id]不能为空")
    private String id;
    //审核结果
    @NotBlank(message = "参数[examineResult]不能为空")
    private String examineResult;//1：通过  2：不通过
    //服务评价
    @NotBlank(message = "参数[serviceEvaluation]不能为空")
    private String serviceEvaluation;//1：好，2：中 3：差
    //是否解决
    @NotBlank(message = "参数[isSolve]不能为空")
    private String isSolve;//1：已解决 2：未解决
    //描述
    private String content;

    private boolean booSolve=true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {

        if(examineResult.equals("1")){
            examineResult="审核通过";
        }else{
            examineResult="审核不通过";
            this.booSolve=false;
        }

        this.examineResult = examineResult;
    }

    public String getServiceEvaluation() {
        return serviceEvaluation;
    }

    public void setServiceEvaluation(String serviceEvaluation) {

        if(serviceEvaluation.equals("1")){
            serviceEvaluation="好";
        }else if(serviceEvaluation.equals("2")){
            serviceEvaluation="中";
        }else{
            serviceEvaluation="差";
        }

        this.serviceEvaluation = serviceEvaluation;
    }

    public String getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(String isSolve) {

        if(isSolve.equals("1")){
            isSolve="已解决";
        }else{
            isSolve="未解决";
        }

        this.isSolve = isSolve;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getBooSolve() {
        return booSolve;
    }

    public void setBooSolve(boolean booSolve) {
        this.booSolve = booSolve;
    }
}
