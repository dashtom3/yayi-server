package com.yayiabc.http.mvc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Benefit;
import com.yayiabc.http.mvc.pojo.jpa.BenefitDetail;
import com.yayiabc.http.mvc.pojo.jpa.ExcelEntry;

public interface BenefitChangeDao {

	void addBenefit(Benefit benefit);

	void addBenefitDetail(@Param("benefitId")Integer benefitId,@Param("benefitCode") String benefitCode);

	BenefitDetail getBenefitByBenefitCode(@Param("benefitCode")String benefitCode);

	Benefit getBenefitByBenefitId(@Param("benefitId")Integer benefitId);

	String getUserIdByToken(@Param("token")String token);

	String getPhoneByUserId(@Param("userId")String userId);

	void updateState(@Param("benefitCodeId")Integer benefitCodeId,@Param("phone") String phone);

	void updateBenefitValueNum(@Param("benefitId")Integer benefitId);

	Integer getTotalNum(@Param("benefitName")String benefitName,@Param("enable") Integer enable);

	List<Benefit> getBenefitList(@Param("benefitName")String benefitName,@Param("enable") Integer enable,@Param("currentNumber")Integer currentNumber,@Param("numberPerPage")Integer numberPerPage);

	List<BenefitDetail> getBenefitDetailListByBenefitId(@Param("benefitId")Integer benefitId,
			@Param("currentNumber")Integer currentNumber, @Param("numberPerPage")Integer numberPerPage);

	List<ExcelEntry> getExcelEntryList(@Param("benefitId")Integer benefitId);

	

	

}
