package com.yayiabc.http.mvc.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Benefit;
import com.yayiabc.http.mvc.pojo.jpa.BenefitDetail;

public interface BenefitChangeService {

	DataWrapper<Void> add(String benefitName, Integer benefitQb,
			Integer benefitNum, String updated);

	DataWrapper<Void> use(String token, String benefitCode);

	DataWrapper<List<Benefit>> list(String benefitName, Integer enable,Integer currentPage,Integer numberPerPage);

	DataWrapper<List<BenefitDetail>> detail(Integer benefitId,
			Integer currentPage, Integer numberPerPage);

	DataWrapper<Void> downLoad(Integer benefitId, HttpServletResponse response);

	DataWrapper<Void> delete(Integer benefitId);

}
