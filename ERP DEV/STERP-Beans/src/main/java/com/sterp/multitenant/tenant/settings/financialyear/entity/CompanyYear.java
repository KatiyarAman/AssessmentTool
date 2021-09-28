package com.sterp.multitenant.tenant.settings.financialyear.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name="company_year")
public class CompanyYear extends SuperBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6858654655702908438L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "year_type")
	private String yearType;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "grace_period")
	private int gracePeriod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYearType() {
		return yearType;
	}

	public void setYearType(String yearType) {
		this.yearType = yearType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(int gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	@Override
	public String toString() {
		return "CompanyYear [id=" + id + ", yearType=" + yearType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", gracePeriod=" + gracePeriod + "]";
	}

	
	
	
	
	
}
