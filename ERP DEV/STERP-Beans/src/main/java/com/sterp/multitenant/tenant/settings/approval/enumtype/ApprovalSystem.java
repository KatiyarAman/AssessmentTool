package com.sterp.multitenant.tenant.settings.approval.enumtype;

public enum ApprovalSystem {
	sequential(0), parallel(1);

	int numVal;

	ApprovalSystem(int val) {
		this.numVal = val;
	}

	public int getVal() {
		return this.numVal;
	}
}
