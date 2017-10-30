package com.manji.finance.order.requestParams;
/**
 * 
 * @author Administrator
 *
 */
public class OrderParam {
	
	private long id;//自动编号
	private String name;//商家名称
	private String order_title;//订单标题
	private String order_no;//订单号
	private String order_type;//订单类型
	private int shop_user_id;//商家所属用户
	private String shop_user_role_type;//商家用户角色
	private int shop_user_role_value;//商家用户角色值
	private int user_id;//购买用户ID
	private String user_role_type;//购买用户角色
	private int user_role_value;//购买用户角色值
	private int express_type;//物流方式
	private double express_fee;//物流费用
	private String message;//订单留言
	private String remark;//订单备注
	private long is_invoice;//是否索要发票
	private String invoice_title;//发票抬头
	private double payable_amount;//应付总金额
	private double pre_payable_amount;//预付金额
	private double real_amount;//实付总金额
	private double order_amount;//订单总金额
	private double voucher;//订单代金券
	private double point;//订单总积分
	private Integer status;//订单状态
	private String add_time;//下单时间
	private String confirm_time;//确认时间
	private String invalid_time;//失效时间
	private String complete_time;//完成时间
	private int payment_id;//支付方式
	private double payment_fee;//支付手续费
	private int payment_status;//支付状态
	private int payment_time;//支付时间
	private Integer settlement_status;//结算状态
	private String settlement_time;//结算时间
	private int is_callback;//是否需要回调
	private String back_url;//回调地址
	private String notice_url;//通知地址
	private int back_status;//回调通知状态
	private String back_time;//成功失败时间
	private int is_del;//是否删除
	private int book_back_status;//退订状态
	private int activity_id;//商家活动id
	private int exec_num;//处理次数
	private int is_norma;//是否异常
	private String reject_remark;//商家拒绝接单原因
	private String cancelremark;//取消退单原因
	private double old_real_amount;//实际付款修改之前价格
	private String cancel_amount_remark;//修改之前价格原因
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String type;//类型
	
	
	public Integer getSettlement_status() {
		return settlement_status;
	}
	public void setSettlement_status(Integer settlement_status) {
		this.settlement_status = settlement_status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_title() {
		return order_title;
	}
	public void setOrder_title(String order_title) {
		this.order_title = order_title;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public int getShop_user_id() {
		return shop_user_id;
	}
	public void setShop_user_id(int shop_user_id) {
		this.shop_user_id = shop_user_id;
	}
	public String getShop_user_role_type() {
		return shop_user_role_type;
	}
	public void setShop_user_role_type(String shop_user_role_type) {
		this.shop_user_role_type = shop_user_role_type;
	}
	public int getShop_user_role_value() {
		return shop_user_role_value;
	}
	public void setShop_user_role_value(int shop_user_role_value) {
		this.shop_user_role_value = shop_user_role_value;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_role_type() {
		return user_role_type;
	}
	public void setUser_role_type(String user_role_type) {
		this.user_role_type = user_role_type;
	}
	public int getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(int user_role_value) {
		this.user_role_value = user_role_value;
	}
	public int getExpress_type() {
		return express_type;
	}
	public void setExpress_type(int express_type) {
		this.express_type = express_type;
	}
	public double getExpress_fee() {
		return express_fee;
	}
	public void setExpress_fee(double express_fee) {
		this.express_fee = express_fee;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(long is_invoice) {
		this.is_invoice = is_invoice;
	}
	public String getInvoice_title() {
		return invoice_title;
	}
	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}
	public double getPayable_amount() {
		return payable_amount;
	}
	public void setPayable_amount(double payable_amount) {
		this.payable_amount = payable_amount;
	}
	public double getPre_payable_amount() {
		return pre_payable_amount;
	}
	public void setPre_payable_amount(double pre_payable_amount) {
		this.pre_payable_amount = pre_payable_amount;
	}
	public double getReal_amount() {
		return real_amount;
	}
	public void setReal_amount(double real_amount) {
		this.real_amount = real_amount;
	}
	public double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(double order_amount) {
		this.order_amount = order_amount;
	}
	public double getVoucher() {
		return voucher;
	}
	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getConfirm_time() {
		return confirm_time;
	}
	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}
	public String getInvalid_time() {
		return invalid_time;
	}
	public void setInvalid_time(String invalid_time) {
		this.invalid_time = invalid_time;
	}
	public String getComplete_time() {
		return complete_time;
	}
	public void setComplete_time(String complete_time) {
		this.complete_time = complete_time;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public double getPayment_fee() {
		return payment_fee;
	}
	public void setPayment_fee(double payment_fee) {
		this.payment_fee = payment_fee;
	}
	public int getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}
	public int getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(int payment_time) {
		this.payment_time = payment_time;
	}
	
	public String getSettlement_time() {
		return settlement_time;
	}
	public void setSettlement_time(String settlement_time) {
		this.settlement_time = settlement_time;
	}
	public int getIs_callback() {
		return is_callback;
	}
	public void setIs_callback(int is_callback) {
		this.is_callback = is_callback;
	}
	public String getBack_url() {
		return back_url;
	}
	public void setBack_url(String back_url) {
		this.back_url = back_url;
	}
	public String getNotice_url() {
		return notice_url;
	}
	public void setNotice_url(String notice_url) {
		this.notice_url = notice_url;
	}
	public int getBack_status() {
		return back_status;
	}
	public void setBack_status(int back_status) {
		this.back_status = back_status;
	}
	public String getBack_time() {
		return back_time;
	}
	public void setBack_time(String back_time) {
		this.back_time = back_time;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public int getBook_back_status() {
		return book_back_status;
	}
	public void setBook_back_status(int book_back_status) {
		this.book_back_status = book_back_status;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public int getExec_num() {
		return exec_num;
	}
	public void setExec_num(int exec_num) {
		this.exec_num = exec_num;
	}
	public int getIs_norma() {
		return is_norma;
	}
	public void setIs_norma(int is_norma) {
		this.is_norma = is_norma;
	}
	public String getReject_remark() {
		return reject_remark;
	}
	public void setReject_remark(String reject_remark) {
		this.reject_remark = reject_remark;
	}
	public String getCancelremark() {
		return cancelremark;
	}
	public void setCancelremark(String cancelremark) {
		this.cancelremark = cancelremark;
	}
	public double getOld_real_amount() {
		return old_real_amount;
	}
	public void setOld_real_amount(double old_real_amount) {
		this.old_real_amount = old_real_amount;
	}
	public String getCancel_amount_remark() {
		return cancel_amount_remark;
	}
	public void setCancel_amount_remark(String cancel_amount_remark) {
		this.cancel_amount_remark = cancel_amount_remark;
	}
	
	
	
}
