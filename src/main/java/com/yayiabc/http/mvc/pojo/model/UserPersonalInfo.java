package com.yayiabc.http.mvc.pojo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class UserPersonalInfo {
	private String userId;
	private String phone;
	private String trueName;
	private Integer sex;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String qq;
	private String userPic;
	private Integer type;
	private String companyName;
	private String part;
	private String workAddress;
	private String doctorPic; // 医师执业资格证
	private Integer state;
	private String failReason;
	private Integer judge;
	private String medicalLicense; // 医疗机构执业许可证
	private String businessLicense; // 营业执照
	private String taxRegistration; // 税务登记证
	private String openingPermit; // 开户许可证
	private String radiologicalPermit; // 放射诊疗许可证
	private String idCardPositive; // 法人身份证正面
	private String idCardOtherside; // 法人身份证反面

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrueName() {
		return trueName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getDoctorPic() {
		return doctorPic;
	}

	public void setDoctorPic(String doctorPic) {
		this.doctorPic = doctorPic;
	}

	public Integer getJudge() {
		return judge;
	}

	public void setJudge(Integer judge) {
		this.judge = judge;
	}

	public String getMedicalLicense() {
		return medicalLicense;
	}

	public void setMedicalLicense(String medicalLicense) {
		this.medicalLicense = medicalLicense;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxRegistration() {
		return taxRegistration;
	}

	public void setTaxRegistration(String taxRegistration) {
		this.taxRegistration = taxRegistration;
	}

	public String getOpeningPermit() {
		return openingPermit;
	}

	public void setOpeningPermit(String openingPermit) {
		this.openingPermit = openingPermit;
	}

	public String getRadiologicalPermit() {
		return radiologicalPermit;
	}

	public void setRadiologicalPermit(String radiologicalPermit) {
		this.radiologicalPermit = radiologicalPermit;
	}

	public String getIdCardPositive() {
		return idCardPositive;
	}

	public void setIdCardPositive(String idCardPositive) {
		this.idCardPositive = idCardPositive;
	}

	public String getIdCardOtherside() {
		return idCardOtherside;
	}

	public void setIdCardOtherside(String idCardOtherside) {
		this.idCardOtherside = idCardOtherside;
	}

	public UserPersonalInfo() {
		super();
	}

	public UserPersonalInfo(String userId, String phone, String trueName,
			Integer sex, Date birthday, String qq, String userPic,
			Integer type, String companyName, String part, String workAddress,
			String doctorPic, Integer state, String failReason, Integer judge,
			String medicalLicense, String businessLicense,
			String taxRegistration, String openingPermit,
			String radiologicalPermit, String idCardPositive,
			String idCardOtherside) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.trueName = trueName;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.userPic = userPic;
		this.type = type;
		this.companyName = companyName;
		this.part = part;
		this.workAddress = workAddress;
		this.doctorPic = doctorPic;
		this.state = state;
		this.failReason = failReason;
		this.judge = judge;
		this.medicalLicense = medicalLicense;
		this.businessLicense = businessLicense;
		this.taxRegistration = taxRegistration;
		this.openingPermit = openingPermit;
		this.radiologicalPermit = radiologicalPermit;
		this.idCardPositive = idCardPositive;
		this.idCardOtherside = idCardOtherside;
	}

	@Override
	public String toString() {
		return "UserPersonalInfo [userId=" + userId + ", phone=" + phone
				+ ", trueName=" + trueName + ", sex=" + sex + ", birthday="
				+ birthday + ", qq=" + qq + ", userPic=" + userPic + ", type="
				+ type + ", companyName=" + companyName + ", part=" + part
				+ ", workAddress=" + workAddress + ", doctorPic=" + doctorPic
				+ ", state=" + state + ", failReason=" + failReason
				+ ", judge=" + judge + ", medicalLicense=" + medicalLicense
				+ ", businessLicense=" + businessLicense + ", taxRegistration="
				+ taxRegistration + ", openingPermit=" + openingPermit
				+ ", radiologicalPermit=" + radiologicalPermit
				+ ", idCardPositive=" + idCardPositive + ", idCardOtherside="
				+ idCardOtherside + "]";
	}

}
