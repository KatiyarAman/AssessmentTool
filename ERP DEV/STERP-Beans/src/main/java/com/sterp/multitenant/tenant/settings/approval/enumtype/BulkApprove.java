package com.sterp.multitenant.tenant.settings.approval.enumtype;

public enum BulkApprove {
	Yes(1), No(0);

	int numVal;

	BulkApprove(int val) {
		this.numVal = val;
	}

	public int getVal() {
		return this.numVal;
	}
}
