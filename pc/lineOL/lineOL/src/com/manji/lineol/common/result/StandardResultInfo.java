package com.manji.lineol.common.result;

import java.io.Serializable;

import com.manji.lineol.common.enums.StatusEnum;

public class StandardResultInfo implements Serializable {

	private static final long serialVersionUID = -4937537791726684426L;

	protected StatusEnum statusEnum;
	protected String code;
	protected String description;
	protected String errorMessage = "";

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "StandardResultInfo [statusEnum=" + statusEnum + ", code=" + code + ", description=" + description
				+ ", errorMessage=" + errorMessage + "]";
	}
	
	

}
