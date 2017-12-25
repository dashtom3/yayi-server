package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 
 * @author xiaojiang 资质认证表
 */
public class Certification extends BasePojo {
	private String userId;

	private Integer type;

	private String companyName;

	private String part;

	private String doctorPic;

	private String s;

	private Integer state;

	private String failReason;

	private Integer judge;

	private String workAddress;
	
	private String medicalLicense; // 医疗机构执业许可证
	private String businessLicense; // 营业执照
	private String taxRegistration; // 税务登记证
	private String openingPermit; // 开户许可证
	private String radiologicalPermit; // 放射诊疗许可证
	private String idCardPositive; // 法人身份证正面
	private String idCardOtherside; // 法人身份证反面

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part == null ? null : part.trim();
	}

	public String getDoctorPic() {
		return doctorPic;
	}

	public void setDoctorPic(String doctorPic) {
		this.doctorPic = doctorPic == null ? null : doctorPic.trim();
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress == null ? null : workAddress.trim();
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
		this.failReason = failReason == null ? null : failReason.trim();
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

	public Certification() {
		super();
	}

	public Certification(String userId, Integer type, String companyName,
			String part, String doctorPic, String workAddress, Integer state,
			String failReason, Integer judge) {
		super();
		this.userId = userId;
		this.type = type;
		this.companyName = companyName;
		this.part = part;
		this.doctorPic = doctorPic;
		this.workAddress = workAddress;
		this.state = state;
		this.failReason = failReason;
		this.judge = judge;
	}

	public Certification(Integer type, String companyName, String part,
			String doctorPic, String workAddress) {
		super();
		this.type = type;
		this.companyName = companyName;
		this.part = part;
		this.doctorPic = doctorPic;
		this.workAddress = workAddress;
	}

	public Certification(String userId, Integer type, String companyName,
			String part, String doctorPic, String workAddress, Integer state,
			String failReason, Integer judge, String medicalLicense,
			String businessLicense, String taxRegistration,
			String openingPermit, String radiologicalPermit,
			String idCardPositive, String idCardOtherside) {
		super();
		this.userId = userId;
		this.type = type;
		this.companyName = companyName;
		this.part = part;
		this.doctorPic = doctorPic;
		this.workAddress = workAddress;
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
		return "Certification [userId=" + userId + ", type=" + type
				+ ", companyName=" + companyName + ", part=" + part
				+ ", doctorPic=" + doctorPic + ", workAddress=" + workAddress
				+ ", state=" + state + ", failReason=" + failReason
				+ ", judge=" + judge + ", medicalLicense=" + medicalLicense
				+ ", businessLicense=" + businessLicense + ", taxRegistration="
				+ taxRegistration + ", openingPermit=" + openingPermit
				+ ", radiologicalPermit=" + radiologicalPermit
				+ ", idCardPositive=" + idCardPositive + ", idCardOtherside="
				+ idCardOtherside + "]";
	}

}