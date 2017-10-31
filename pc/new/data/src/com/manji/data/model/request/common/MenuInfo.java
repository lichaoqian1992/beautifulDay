package com.manji.data.model.request.common;

import java.util.List;

public class MenuInfo extends Menu{

	List<Menu> child;

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	
	
}
