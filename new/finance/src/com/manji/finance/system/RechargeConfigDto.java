package com.manji.finance.system;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/8.  金额配置表  YYR
 */
public class RechargeConfigDto extends Model{

    private int id;
    /**
     * 审批人角色
     */
    private String userName;
    private String realName;
    /**
     * 单笔最小金额
     */
    private double singleMinMoney;
    /**
     * 单笔最大金额
     */
    private double singleMaxMoney;
    /**
     * 总金额
     */
    private double totalMoney;

    /**
     * 提現单笔最小金额
     */
    private double t_singleMinMoney;
    /**
     * 提現单笔最大金额
     */
    private double t_singleMaxMoney;
    /**
     * 提現每日总金额
     */
    private double t_totalMoney;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;











    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public double getSingleMinMoney() {
        return singleMinMoney;
    }

    public void setSingleMinMoney(double singleMinMoney) {
        this.singleMinMoney = singleMinMoney;
    }

    public double getSingleMaxMoney() {
        return singleMaxMoney;
    }

    public void setSingleMaxMoney(double singleMaxMoney) {
        this.singleMaxMoney = singleMaxMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getT_singleMinMoney() {
        return t_singleMinMoney;
    }

    public void setT_singleMinMoney(double t_singleMinMoney) {
        this.t_singleMinMoney = t_singleMinMoney;
    }

    public double getT_singleMaxMoney() {
        return t_singleMaxMoney;
    }

    public void setT_singleMaxMoney(double t_singleMaxMoney) {
        this.t_singleMaxMoney = t_singleMaxMoney;
    }

    public double getT_totalMoney() {
        return t_totalMoney;
    }

    public void setT_totalMoney(double t_totalMoney) {
        this.t_totalMoney = t_totalMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
