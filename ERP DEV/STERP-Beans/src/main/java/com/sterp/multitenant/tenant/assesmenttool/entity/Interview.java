package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "interviews")
public class Interview extends SuperBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "interview_name")
	private String interviewName;
	
	@Column(name = "webcam_permission")
	private Boolean webcamPermission;
	
	@Column(name = "total_questions")
	private Integer totalQuestions;
	
	@ManyToOne
	@JoinColumn(name = "job_profile_id", nullable = false)
	private JobProfile jobProfile;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "interview_id")
	private List<InterviewGeneralQuestionMappingBean> mappedQuestions = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public Boolean getWebcamPermission() {
		return webcamPermission;
	}

	public void setWebcamPermission(Boolean webcamPermission) {
		this.webcamPermission = webcamPermission;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public JobProfile getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(JobProfile jobProfile) {
		this.jobProfile = jobProfile;
	}

	public List<InterviewGeneralQuestionMappingBean> getMappedQuestions() {
		return mappedQuestions;
	}

	public void setMappedQuestions(List<InterviewGeneralQuestionMappingBean> mappedQuestions) {
		this.mappedQuestions = mappedQuestions;
	}
}
