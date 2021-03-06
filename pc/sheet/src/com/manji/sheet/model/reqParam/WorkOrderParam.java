package com.manji.sheet.model.reqParam;

public class WorkOrderParam extends SheetParam{
    private String sourceCode="";//信息来源
    private String typeCode="";//工单编码
    private String startTime="";//初始时间
    private String endTime="";//结束时间
    private String search="";//搜索
    private String userDeptId="";//登录人部门id


    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

	public String getUserDeptId() {
		return userDeptId;
	}

	public void setUserDeptId(String userDeptId) {
		this.userDeptId = userDeptId;
	}
    
    

}
