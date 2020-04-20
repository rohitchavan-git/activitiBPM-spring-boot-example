package com.rohit.activiti.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {
	
	@Column(name="is_able_insurance")
	private boolean isAbleInsurance;

	@OneToMany
	private List<Insurance> insurance;

	public Customer() {
	}

	public Customer(String userName, boolean isAbleInsurance,List<Insurance> insurance) {
		super(userName);
		this.setAbleInsurance(isAbleInsurance);
		this.insurance = insurance;
	}

	public List<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(List<Insurance> list) {
		this.insurance = list;
	}

	public boolean isAbleInsurance() {
		return isAbleInsurance;
	}

	public void setAbleInsurance(boolean isAbleInsurance) {
		this.isAbleInsurance = isAbleInsurance;
	}

}
