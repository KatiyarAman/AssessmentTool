package com.sterp.multitenant.tenant.settings.approval.enumtype;

public enum ApprovalBased {
	Value(0), Time(1), Volume(2);

	int numVal;

	ApprovalBased(int val) {
		this.numVal = val;
	}

	public int getVal() {
		return this.numVal;
	}
}
