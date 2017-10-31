package com.manji.sheet.model.bean;

public class SheetPunish {
	
	/**
	 * 处罚表
	 */
	
	private long sheet_id;
	private int deduct_value;
	private double deduct_amount;
	private double damage_amount;
	private int is_close;
	private int is_freeze;
	private int is_soldout;
	
	public long getSheet_id() {
		return sheet_id;
	}
	public void setSheet_id(long sheet_id) {
		this.sheet_id = sheet_id;
	}
	public int getDeduct_value() {
		return deduct_value;
	}
	public void setDeduct_value(int deduct_value) {
		this.deduct_value = deduct_value;
	}
	public double getDeduct_amount() {
		return deduct_amount;
	}
	public void setDeduct_amount(double deduct_amount) {
		this.deduct_amount = deduct_amount;
	}
	public double getDamage_amount() {
		return damage_amount;
	}
	public void setDamage_amount(double damage_amount) {
		this.damage_amount = damage_amount;
	}
	public int getIs_close() {
		return is_close;
	}
	public void setIs_close(int is_close) {
		this.is_close = is_close;
	}
	public int getIs_freeze() {
		return is_freeze;
	}
	public void setIs_freeze(int is_freeze) {
		this.is_freeze = is_freeze;
	}
	public int getIs_soldout() {
		return is_soldout;
	}
	public void setIs_soldout(int is_soldout) {
		this.is_soldout = is_soldout;
	}
	
}
