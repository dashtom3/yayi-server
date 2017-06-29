package com.yayiabc.common.enums;
/**
 * 资质审核状态的枚举
 * @author yehu
 *
 */
public enum CertificatEnum {
	CERTIFICAT_RUNNING("审核中",1),
	CERTIFICAT_SUCCESS("审核通过",2),
	CERTIFICAT_FAIL("审核未通过",3);
	private String label;
	private Integer code;
	
	private CertificatEnum(String label, Integer code) {
		this.label = label;
		this.code = code;
	}
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
	public String toString(){
		return label;
	}
	
}
