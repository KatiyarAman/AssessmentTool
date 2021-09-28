package com.sterp.multitenant.tenant.settings.questionnaire.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "questionnaire_settings")
public class QuestionnaireSettings extends SuperBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2634538368464250957L;

	@Id
	@Column(name = "Id", length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "custom_field_id")
	private CustomField field;
	
	@Column(name = "value")
	private String value;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomField getField() {
		return field;
	}

	public void setField(CustomField field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "QuestionnaireSettings [id=" + id + ", field=" + field + ", value=" + value + "]";
	}

	

	
	
	

}
