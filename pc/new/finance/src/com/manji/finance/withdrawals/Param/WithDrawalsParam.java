package com.manji.finance.withdrawals.Param;


import com.jfinal.plugin.activerecord.Model;

/**
 * 查询提现记录的时候参数实体类
 * Created by Administrator on 2017/2/17.
 */
public class WithDrawalsParam {

    /**
     * 提现订单号
     */
    private String withDrawalsId;

    /**
     * 账户名称
     */
    private String username;

    /**
     * 账户类型
     */
    private String accountType;
    /**
     * 提现状态
     */
    private String status;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 账户ID
     */
    private String accountId;

    /**
     * 是否异常
     */
    private Integer exceptions;

    /**
     * 是否审核
     */
    private Integer detailed;

    /**
     * 审核人id
     */
    private String role;

    /**
     * 提现单id
     */
    private String idlist;

    /**
     * 确认状态(0.人工处理中 1.提现成功 2.提现失败)
     */
    private Integer confirmwhether;

    public String getIdlist() {
        return idlist;
    }

    public void setIdlist(String idlist) {
        this.idlist = idlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWithDrawalsId() {
        return withDrawalsId;
    }

    public void setWithDrawalsId(String withDrawalsId) {
        this.withDrawalsId = withDrawalsId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getExceptions() {
        return exceptions;
    }

    public void setExceptions(Integer exceptions) {
        this.exceptions = exceptions;
    }

    public Integer getDetailed() {
        return detailed;
    }

    public void setDetailed(Integer detailed) {
        this.detailed = detailed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getConfirmwhether() {
        return confirmwhether;
    }

    public void setConfirmwhether(Integer confirmwhether) {
        this.confirmwhether = confirmwhether;
    }
}
