package com.manji.sheet.model.reqParam;

public class SheetInfoParam extends SheetParam{
    private long id;
    private String descr;/*描述*/
    private String pics;/*图片*/
    private String add_time;/*添加时间*/
    private String sheetFrom;/*信息来源*/
    private String reportType;/*二级举报类型code*/
    private String codeReport;/*一级举报类型code*/
    private String reportTitle;/*二级举报类型名称code*/
    private String reportUser;/*发送人姓名*/
    private String reportUserId;/*发送人编号*/
    private String reportUserType;/*发送人类型*/
    private int shopId;/*商家编号*/
    private int shopInfo;/*店铺编号*/
    private int articleId;/*商品编号*/
    private int orderId;/*订单编号*/
    private int backOrderId;/*退单号*/

    public String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getReportUserType() {
		return reportUserType;
	}

	public void setReportUserType(String reportUserType) {
		this.reportUserType = reportUserType;
	}

	@Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getCodeReport() {
        return codeReport;
    }

    public void setCodeReport(String codeReport) {
        this.codeReport = codeReport;
    }

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    public String getSheetFrom() {
        return sheetFrom;
    }

    public void setSheetFrom(String sheetFrom) {
        this.sheetFrom = sheetFrom;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(int shopInfo) {
        this.shopInfo = shopInfo;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public int getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(int backOrderId) {
        this.backOrderId = backOrderId;
    }
}
