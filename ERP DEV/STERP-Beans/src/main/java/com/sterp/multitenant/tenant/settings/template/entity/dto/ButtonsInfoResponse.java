package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.ModuleButtonsMapping;

public class ButtonsInfoResponse {
	private Object buttons;
	private List<ModuleButtonsMapping> buttonList;

	public ButtonsInfoResponse() {
		// TODO Auto-generated constructor stub
	}

	public ButtonsInfoResponse(Object buttons, List<ModuleButtonsMapping> buttonList) {
		this.buttons = buttons;
		this.buttonList = buttonList;
	}

	public Object getButtons() {
		return buttons;
	}

	public void setButtons(Object buttons) {
		this.buttons = buttons;
	}

	public List<ModuleButtonsMapping> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<ModuleButtonsMapping> buttonList) {
		this.buttonList = buttonList;
	}

}
