package com.manji.msgservice.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

public class BaseParam {
	@NotBlank(message ="发送时间段类型不能为空")
	private String sendTimeType;
	private String liveTime;
	private String deadTime;

	@ApiModelProperty(value = "发送出去的时间类型：timely:及时。。。temptime:模版定义的时间段。。。customize：自定义时间段")
	public String getSendTimeType() {
		return sendTimeType;
	}
	public void setSendTimeType(String sendTimeType) {
		this.sendTimeType = sendTimeType;
	}
	@ApiModelProperty(value = "自定义时间段的开始时间")
	public String getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(String liveTime) {
		this.liveTime = liveTime;
	}
	@ApiModelProperty(value = "自定义时间段的结束时间")
	public String getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}
}
