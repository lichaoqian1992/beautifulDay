package com.manji.mlife.model;

public class Card {
    private String cardno;

    private String cardpassword;

    private String tradeno;

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getCardpassword() {
        return cardpassword;
    }

    public void setCardpassword(String cardpassword) {
        this.cardpassword = cardpassword == null ? null : cardpassword.trim();
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno == null ? null : tradeno.trim();
    }
}