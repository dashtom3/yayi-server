package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BasePojo {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
    /**
     * catid
     * @param created
     * @param updated
     */
	public BasePojo(Date created, Date updated) {
		super();
		this.created = created;
		this.updated = updated;
	}

}
