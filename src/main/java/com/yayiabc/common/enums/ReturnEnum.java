package com.yayiabc.common.enums;
/**
 * 退货状态的枚举
 * @author yehu
 *
 */
public enum ReturnEnum{
	RUNNING("审核中", 1),
	BACK_SUCCEED("退款成功", 2),
	BACK_FAIL("退款失败", 3),
	ABOLISH("已取消", 4);
	private String label;
	private Integer code;
	private ReturnEnum(String label, Integer code) {
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
