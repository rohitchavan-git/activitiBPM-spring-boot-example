package com.rohit.activiti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rohit.activiti.util.EumSet.InsuranceStatus;

@Entity
@Table(name = "insurance")
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_no")
	private Long applicationNo;
	@Column(name = "insurance_name")
	private String insuranceName;
	@Column(name = "product")
	private String product;
	@Column(name = "premiun_amount")
	private Double premiumAmount;
	@Column(name = "sum_assured_amount")
	private Double sumAssuredAmount;
	@Column(name = "process_insrance_id")
	private String processInstanceId;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private InsuranceStatus status;

	public Insurance() {
	}

	public Insurance(String insuranceName, String product, Double premiumAmount, Double sumAssuredAmount,
			String processInstanceId, InsuranceStatus status) {

		this.insuranceName = insuranceName;
		this.product = product;
		this.premiumAmount = premiumAmount;
		this.sumAssuredAmount = sumAssuredAmount;
		this.processInstanceId = processInstanceId;
		this.status = status;
	}

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Double getSumAssuredAmount() {
		return sumAssuredAmount;
	}

	public void setSumAssuredAmount(Double sumAssuredAmount) {
		this.sumAssuredAmount = sumAssuredAmount;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public InsuranceStatus getStatus() {
		return status;
	}

	public void setStatus(InsuranceStatus status) {
		this.status = status;
	}
}
