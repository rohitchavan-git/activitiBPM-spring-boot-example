package com.rohit.activiti.util;

public class EumSet {
	public enum InsuranceStatus{
		SEND_FOR_DOCVARIFICATION,
		HOLD_BY_SCRUT,
		APPROVED_BY_SCRUT,
		SEND_FOR_CLEARIFICATION,
		APPROVED,
		NOT_APPROVED;
	};
	public enum EmployeeType{
			SCRUTING,
			RM,
			HA,
			UNDERWRITER;
	};
}
