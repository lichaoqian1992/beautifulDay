package com.manji.finance.withdrawals.ExceptionSetting;


/**
 * Created by pudding on 2017-4-17. 提现异常规则DTO
 */
public class ExceptionSettingDto {

    private int id;

    private double exception_one;

    private double exception_one_tow;

    private double exception_tow;

    private double exception_three;

    private double exception_four;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getException_one() {
        return exception_one;
    }

    public void setException_one(double exception_one) {
        this.exception_one = exception_one;
    }

    public double getException_one_tow() {
        return exception_one_tow;
    }

    public void setException_one_tow(double exception_one_tow) {
        this.exception_one_tow = exception_one_tow;
    }

    public double getException_tow() {
        return exception_tow;
    }

    public void setException_tow(double exception_tow) {
        this.exception_tow = exception_tow;
    }

    public double getException_three() {
        return exception_three;
    }

    public void setException_three(double exception_three) {
        this.exception_three = exception_three;
    }

    public double getException_four() {
        return exception_four;
    }

    public void setException_four(double exception_four) {
        this.exception_four = exception_four;
    }
}
