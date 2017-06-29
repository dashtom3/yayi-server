package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

/**
 * 
 * @author xiaojiang 时间表
 */
public class BaseTime {
	private String baseTimeId;// 时间ID
	private Date created;// 创建时间
	private Date updated;// 修改时间

	public String getBaseTimeId() {
		return baseTimeId;
	}

	public void setBaseTimeId(String baseTimeId) {
		this.baseTimeId = baseTimeId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public BaseTime() {
		super();
	}

	public BaseTime(String baseTimeId, Date created, Date updated) {
		super();
		this.baseTimeId = baseTimeId;
		this.created = created;
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "basetime [baseTimeId=" + baseTimeId + ", created=" + created
				+ ", updated=" + updated + "]";
	}

}
