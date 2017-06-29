package com.yayiabc.common.enums;
/**
 * 用户身份的枚举
 * @author 小月亮
 *
 */
public enum IdentityEnum {
	MANNAGER("管理员",1),
	MARKET("销售/普通用户",2),
	DOCTOR("牙医",3),
	ORGANIZATION("机构",4);
	private String label;
	private Integer code;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	private IdentityEnum(String label, Integer code) {
		this.label = label;
		this.code = code;
	}
	public String toString(){
		return label;
	}
}
