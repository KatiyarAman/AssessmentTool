package com.sterp.multitenant.tenant.entity;

public enum Status {
	Draft(1), Submitted(2), Verified(3), Trial(4), Paid(5), PartiallyApproved(6), Approved(7), Canceled(8), Deleted(9),
	Terminated(10), Expired(11), Returned(12), Revised(13), Rejected(14), Hold(15);

	int numVal;

	Status(int val) {
		this.numVal = val;
	}

	public int getVal() {
		return this.numVal;
	}
}