package com.manji.finance.withdrawals.Withdrawals;

/**
 * Created by pudding on 2017-4-14. 提现dto
 */
public class WithdrawalsDto {

    private Long id;

    /***用户ID*/
    private String userId;

    /***用户角色类型*/
    private String userRoleTyoe;

    /***用户角色值*/
    private Integer userRoleValue;

    /***提现账户ID*/
    private Integer accountId;

    /***提现订单号*/
    private String withdrawalsNo;

    /***银行卡名称*/
    private String bankName;

    /***银行卡卡号*/
    private String bankCard;

    /***银行卡地区*/
    private String bankArea;

    /***详细开户地址*/
    private String bankAddress;

    /***提现金额*/
    private Double amount;

    /***提现状态*/
    private String status;

    /***生成时间*/
    private String add_time;

    /***完成时间*/
    private String complete_time;

    /***三方支付交易号*/
    private String transactionNo;

    /***提现处理说明*/
    private String remark;

    /***是否删除*/
    private String isDel;

    /***提现手续费*/
    private Double commission;

    /***开户姓名*/
    private String bank_user;

    /***提现满意券*/
    private Double voucher;

    /***提现总金额*/
    private Double totalMoney;

    /**是否是异常订单：0不是,1是**/
    private int exceptions;

    /**异常是否审核过0不是,1是**/
    private int examine;

    /**异常提示信息**/
    private String alter;

    /**是否审核**/
    private int detailed;

    /**
     * 确认状态(0.人工处理中 1.提现成功 2.提现失败)
     */
    private int confirmwhether;

    /***账户状态*/
    private Integer state;

    /**
     * 账户名称
     */
    private String accountname;

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRoleTyoe() {
        return userRoleTyoe;
    }

    public void setUserRoleTyoe(String userRoleTyoe) {
        this.userRoleTyoe = userRoleTyoe;
    }

    public Integer getUserRoleValue() {
        return userRoleValue;
    }

    public void setUserRoleValue(Integer userRoleValue) {
        this.userRoleValue = userRoleValue;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getWithdrawalsNo() {
        return withdrawalsNo;
    }

    public void setWithdrawalsNo(String withdrawalsNo) {
        this.withdrawalsNo = withdrawalsNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankArea() {
        return bankArea;
    }

    public void setBankArea(String bankArea) {
        this.bankArea = bankArea;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(String complete_time) {
        this.complete_time = complete_time;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getBank_user() {
        return bank_user;
    }

    public void setBank_user(String bank_user) {
        this.bank_user = bank_user;
    }

    public Double getVoucher() {
        return voucher;
    }

    public void setVoucher(Double voucher) {
        this.voucher = voucher;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getExceptions() {
        return exceptions;
    }

    public void setExceptions(int exceptions) {
        this.exceptions = exceptions;
    }

    public int getExamine() {
        return examine;
    }

    public void setExamine(int examine) {
        this.examine = examine;
    }

    public String getAlter() {
        return alter;
    }

    public void setAlter(String alter) {
        this.alter = alter;
    }

    public int getDetailed() {
        return detailed;
    }

    public void setDetailed(int detailed) {
        this.detailed = detailed;
    }

    public int getConfirmwhether() {
        return confirmwhether;
    }

    public void setConfirmwhether(int confirmwhether) {
        this.confirmwhether = confirmwhether;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
