package com.manji.orService.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/8/30.
 */


public class SelectSheetVo {

    private String orSheetTypeId;

    private String orPriority;

    private String startTime="";

    private String endTime="";

    private String search;

    @NotBlank(message = "参数[orPlate]不能为空")
    private String orPlate;

    private String orFounderId;

    private String orFounderDept;

    @NotBlank(message = "参数[pageNum]不能为空")
    private String pageNum;
    @NotBlank(message = "参数[pageSize]不能为空")
    private String pageSize;


    public String getOrSheetTypeId() {
        return orSheetTypeId;
    }

    public void setOrSheetTypeId(String orSheetTypeId) {

        if(orSheetTypeId==null || orSheetTypeId.equals("")){
            orSheetTypeId="-1";
        }

        this.orSheetTypeId = orSheetTypeId;
    }

    public String getOrPriority() {
        return orPriority;
    }

    public void setOrPriority(String orPriority) {

        if(orPriority==null || orPriority.equals("")){
            orPriority="-1";
        }

        this.orPriority = orPriority;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {

        if(startTime==null){
            startTime="";
        }


        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {

        if(endTime==null){
            endTime="";
        }

        this.endTime = endTime;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrPlate() {
        return orPlate;
    }

    public void setOrPlate(String orPlate) {
        this.orPlate = orPlate;
    }

    public String getOrFounderId() {
        return orFounderId;
    }

    public void setOrFounderId(String orFounderId) {
        this.orFounderId = orFounderId;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrFounderDept() {
        return orFounderDept;
    }

    public void setOrFounderDept(String orFounderDept) {
        this.orFounderDept = orFounderDept;
    }
}
