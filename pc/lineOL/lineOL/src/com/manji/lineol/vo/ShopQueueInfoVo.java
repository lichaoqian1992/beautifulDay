package com.manji.lineol.vo;

import java.io.Serializable;

/**
 * 商家排队信息VO
 * 
 * @author pudding
 *
 */
public class ShopQueueInfoVo implements Serializable {
	private static final long serialVersionUID = -4041570604290777332L;

	// 队列类型名称
	private String typeName;

	// 类型别名
	private String typeAs;

	// 队列总个数
	private String totalCount;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeAs() {
		return typeAs;
	}

	public void setTypeAs(String typeAs) {
		this.typeAs = typeAs;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "ShopQueueInfoVo [typeName=" + typeName + ", typeAs=" + typeAs + ", totalCount=" + totalCount + "]";
	}

}
