package com.sterp.multitenant.tenant.enumtype;

public enum YesNoEnum {
	No(0), Yes(1);

	int numVal;

	YesNoEnum(int val) {
		this.numVal = val;
	}

	public int getVal() {
		return this.numVal;
	}
}
