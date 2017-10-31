package com.manji.finance.system;
/**
 * 调用充值、提现接口后返回的数据
 * @author Administrator
 *
 */
public class HttplogDO {

	private int id;

    private String Action;

    private String QuerySN;

    private String TranSN;

    private String ResultData;

    private String Createdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getQuerySN() {
		return QuerySN;
	}

	public void setQuerySN(String querySN) {
		QuerySN = querySN;
	}

	public String getTranSN() {
		return TranSN;
	}

	public void setTranSN(String tranSN) {
		TranSN = tranSN;
	}

	public String getResultData() {
		return ResultData;
	}

	public void setResultData(String resultData) {
		ResultData = resultData;
	}

	public String getCreatedate() {
		return Createdate;
	}

	public void setCreatedate(String createdate) {
		Createdate = createdate;
	}
    
}
