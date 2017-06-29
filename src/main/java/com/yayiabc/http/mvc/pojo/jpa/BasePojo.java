package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class BasePojo {
	private Date created;

	private Date updated;

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

	public BasePojo() {
		super();
	}

	public BasePojo(Date created, Date updated) {
		super();
		this.created = created;
		this.updated = updated;
	}

}
