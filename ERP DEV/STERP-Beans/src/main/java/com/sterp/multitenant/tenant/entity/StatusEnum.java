package com.sterp.multitenant.tenant.entity;

public enum StatusEnum {
	Inactive(0), Active(1);

	int numVal;

	StatusEnum(int val) {
		this.numVal = val;
	}

	public int getVal() {
		return this.numVal;
	}
}