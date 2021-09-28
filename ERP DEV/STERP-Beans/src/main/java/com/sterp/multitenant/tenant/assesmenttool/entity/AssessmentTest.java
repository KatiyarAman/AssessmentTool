package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.TimeDurationPolicy;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "assessment_tests")
public class AssessmentTest extends SuperBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Enumerated
	@Column(name = "time_duration_policy")
	private TimeDurationPolicy timeDurationPolicy;
	
	@Column(name = "time_out_duration")
	private Integer timeOutDuration;
	
	@Column(name = "enable_negetive_marking")
	private boolean enableNegetiveMarking;
	
	@Column(name = "test_pass_percentage")
	private double testPassPercentage;
	
	@Column(name = "total_question")
	private Integer totalQuestions;
	
	@Column(name = "enable_web_cam")
	private boolean enableWebCam;
	
	@Column(name = "enable_anti_cheating")
	private boolean enableAntiCheating;
	
	@Column(name = "take_introduction_video")
	private boolean takeIntroductionVideo;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "assessment_test_id")
	private List<AssessementQuetsionTestMapping> mapping = new ArrayList<AssessementQuetsionTestMapping>();
	
	@Transient
	private List<AssessmentQuestionnaire> questions = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "job_profile_id", nullable = false)
	private JobProfile jobProfile;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TimeDurationPolicy getTimeDurationPolicy() {
		return timeDurationPolicy;
	}
	public void setTimeDurationPolicy(TimeDurationPolicy timeDurationPolicy) {
		this.timeDurationPolicy = timeDurationPolicy;
	}
	public Integer getTimeOutDuration() {
		return timeOutDuration;
	}
	public void setTimeOutDuration(Integer timeOutDuration) {
		this.timeOutDuration = timeOutDuration;
	}
	public boolean isEnableNegetiveMarking() {
		return enableNegetiveMarking;
	}
	public void setEnableNegetiveMarking(boolean enableNegetiveMarking) {
		this.enableNegetiveMarking = enableNegetiveMarking;
	}
	public double getTestPassPercentage() {
		return testPassPercentage;
	}
	public void setTestPassPercentage(double testPassPercentage) {
		this.testPassPercentage = testPassPercentage;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public List<AssessmentQuestionnaire> getQuestions() {
		return questions;
	}
	public void setQuestions(List<AssessmentQuestionnaire> questions) {
		this.questions = questions;
	}
	public JobProfile getJobProfile() {
		return jobProfile;
	}
	public void setJobProfile(JobProfile jobProfile) {
		this.jobProfile = jobProfile;
	}
	public boolean isEnableWebCam() {
		return enableWebCam;
	}
	public void setEnableWebCam(boolean enableWebCam) {
		this.enableWebCam = enableWebCam;
	}
	public boolean isEnableAntiCheating() {
		return enableAntiCheating;
	}
	public void setEnableAntiCheating(boolean enableAntiCheating) {
		this.enableAntiCheating = enableAntiCheating;
	}
	public boolean isTakeIntroductionVideo() {
		return takeIntroductionVideo;
	}
	public void setTakeIntroductionVideo(boolean takeIntroductionVideo) {
		this.takeIntroductionVideo = takeIntroductionVideo;
	}
	public List<AssessementQuetsionTestMapping> getMapping() {
		return mapping;
	}
	public void setMapping(List<AssessementQuetsionTestMapping> mapping) {
		this.mapping = mapping;
	}
	
	
}
