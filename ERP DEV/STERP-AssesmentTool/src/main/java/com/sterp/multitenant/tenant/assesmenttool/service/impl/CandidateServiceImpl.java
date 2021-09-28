package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.sterp.multitenant.tenant.assesmenttool.entity.ApplyMandatorySkills;
import com.sterp.multitenant.tenant.assesmenttool.entity.Candidate;
import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateApplication;
import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateSkillExperience;
import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateSkillSummary;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.UploadedFileResource;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateRegisterationDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.CandidateStatus;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateAplicationRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateProfileRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateSkillSummaryRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateVerificationTokenRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.CandidateService;
import com.sterp.multitenant.tenant.assesmenttool.service.JobPostingService;
import com.sterp.multitenant.tenant.assesmenttool.service.JobProfileService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.repository.StatusDetailsRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;
import com.sterp.multitenant.tenant.uploadutility.service.impl.FileStorageUploadServiceImpl;
import com.sterp.multitenant.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;

@Service
public class CandidateServiceImpl implements CandidateService, ServletContextAware {

	@Autowired
	private CandidateRepository candidateRepository;

	private ServletContext servletContext;

	@Autowired
	private CandidateVerificationTokenRepository candidateVerificationTokenRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	JwtTokenUtil jwtTokenutil;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private JobProfileService jobProfileService;

	@Autowired
	private JobPostingService jobPostingService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AppService appService;

	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private FileStorageUploadServiceImpl fileStorage;
	
	@Autowired
	private StatusDetailsRepository statusRepo;
	@Autowired
	private CandidateAplicationRepository candidateApplicationRepository;
	@Autowired
	private CandidateProfileRepository candidateProfileRepository;
	@Autowired
	private CandidateSkillSummaryRepository candidateSkillSummaryRepository;
	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> applyOnJob(CandidateRegisterationDto candidateDto, MultipartFile[] resumeDoc,
			MultipartFile introVideo,String hostName) throws IllegalStateException, IOException, MessagingException {
		Map<String, Object> response = new HashMap<String, Object>();
		CandidateApplication candidateApplication=new CandidateApplication(); 
		CandidateProfile candidateProfile=new CandidateProfile();
		Candidate candidate=this.mapper.map(candidateDto,Candidate.class);
		JobPosting jobPosting = new JobPosting();
		if(candidateDto.getJobPostingId() != null) {
			jobPosting=this.jobPostingService.getJobPostingById(candidateDto.getJobPostingId());
		}
		
		
		//if this request is for candidate registration
		if (candidate.getId() == null) {
			List<CandidateDto> candidateExist =candidateRepository.findCustomWithJobPosting(candidateDto.getEmail(),candidateDto.getJobPostingId());
			if(candidateExist.size()>0 )
			{
				response.put("data",candidateExist);
				response.put("message","Email Already in My Database");
				return response;
			}
			  candidate.setCreatedDate(LocalDate.now());
			  candidate.setCandidateStatus(CandidateStatus.REGISTERED);
			  candidate.setStatus(23L); 
			  String emailVerificationToken = UUID.randomUUID().toString();
			  candidate.setVerificationToken(emailVerificationToken);
			  if(candidateDto.getJobPostingId() != null) {
				  candidate.setExpiryDate(jobPosting.getPostingEndDate());
			  }
			  
			  candidate = this.candidateRepository.save(candidate);
			   if( candidateDto.getPermanentAddress() !=null) {
				   candidateProfile.setCandidateId(candidate.getId());
					  candidateProfile.setIsSameTOPermanent(candidateDto.getSameAsPermanentAddress()); 
					  candidateProfile.setPermanentAddress(candidateDto.getPermanentAddress());
					  candidateProfile.setCurrentAddress(candidateDto.getCurrentAddress());
					  candidateProfile.setCurrentCityId(candidateDto.getCurrentCityId());
					  candidateProfile.setCityId(candidateDto.getPermanentCityId());
					  candidateProfile.setRelavantExperienceUnit(candidateDto.getTotalExperienceUnit());
					  candidateProfile.setTotalExperienceUnit(candidateDto.getRelavantExperienceUnit());
					  candidateProfile.setRelavantExperience(candidateDto.getRelavantExperience());
					  candidateProfile.setTotalExperience(candidateDto.getTotalExperience());
					  candidateProfile.setPermanentCountryId(candidateDto.getPermanentCountryId());
					  candidateProfile.setPermanentStateId(candidateDto.getPermanentStateId());
					  candidateProfile.setPermanentPincode(candidateDto.getPermanentPincode());
					  candidateProfile.setCurrentCountryId(candidateDto.getCurrentCountryId());
					  candidateProfile.setCurrentStateId(candidateDto.getCurrentStateId());
					  candidateProfile.setCurrentPincode(candidateDto.getCurrentPincode());
					  this.candidateProfileRepository.save(candidateProfile);
			   }
			 
			  candidateApplication.setCandidateId(candidate.getId());
			  if(candidateDto.getJobPostingId() != null) {
				  candidate.setExpiryDate(jobPosting.getPostingEndDate());
				  candidateApplication.setJobPosting(jobPosting);
			  }
			  
			  candidateApplication.setAppliedDate(candidate.getCreatedDate());
			  candidateApplication.setStatus(candidate.getStatus());
			  if(candidateDto.getSkillExperience() != null && candidateDto.getSkillExperience().size() > 0) {
				  List<CandidateSkillExperience> skills = candidateDto.getSkillExperience();
				  for (CandidateSkillExperience candidateSkillExperience : skills) {
					  candidateSkillExperience.setCandidateRegId(candidate.getId());
				  }
				  candidateApplication.setSkillExperience(skills);
			  }
			  this.candidateApplicationRepository.save(candidateApplication);
			  
			  if(candidateDto.getSkillSummary() !=null && candidateDto.getSkillSummary().size()>0){
				  List<CandidateSkillSummary> skills = candidateDto.getSkillSummary();
				  for (CandidateSkillSummary candidateSkillExperience : skills) {
					  candidateSkillExperience.setCandidateRegId(candidate.getId());
					  candidateSkillExperience.setHaveSkill(true);
					  System.out.println("CandidateSkillSummary");
				  }
				  this.candidateSkillSummaryRepository.saveAll(skills);
			  }
			  
			  
			 
			  if(!candidateDto.isThroughSocialLogin()) {		 
				 	if(candidateDto.getJobPostingId() != null) {
						// Generate verification token for candidate String token =
						  String token = this.jobPostingService.getShareableToken(candidateDto.getJobPostingId());
						  JobProfile jobProfile = jobPosting.getJobProfile(); 
						 // this.sendVerificationLinkMail(emailVerificationToken, candidate.getCandidateEmail(),token);
						  Map<String, Object> props = new HashMap<>();
						  
						  props.put("accessToken", token); 
						  props.put("emailVerificationToken",emailVerificationToken);
						  props.put("candidateEmail", candidate.getEmail());
						  props.put("candidate", candidate);
						  props.put("jobTitle",jobProfile.getJobTitle());
						  props.put("postingId",candidateDto.getJobPostingId());
						  props.put("port", "81737");
						  props.put("hostName",hostName);
						  
						  this.sendVerificationLinkMail(props);
						  
						  response.put("message", "We've sent an email to " + candidate.getEmail() +" with a verification link to verify your email.");
					 	}
				  } 
				  else {
				       response.put("emailVerificationToken", emailVerificationToken);
				       response.put("message", "Verified through social login"); 
				  }
	               candidateDto.setId(candidate.getId());
	              response.put("data", candidateDto);
	              //candidate Profile updating 
	            } else {
			              CandidateProfile profile=candidateProfileRepository.findByCandidateId(candidate.getId());
			            		  if(profile !=null) {
			            			  candidateProfile.setId(profile.getId());
			            		  }
	            		 
			            Candidate CandidateToken=candidateRepository.getOne(candidateDto.getId());
			       
			          candidateProfile.setCandidateId(candidate.getId());
					  candidateProfile.setIsSameTOPermanent(candidateDto.getSameAsPermanentAddress()); 
					  candidateProfile.setPermanentAddress(candidateDto.getPermanentAddress());
					  candidateProfile.setCurrentAddress(candidateDto.getCurrentAddress());
					  candidateProfile.setCurrentCityId(candidateDto.getCurrentCityId());
					  candidateProfile.setCityId(candidateDto.getPermanentCityId());
					  candidateProfile.setRelavantExperienceUnit(candidateDto.getTotalExperienceUnit());
					  candidateProfile.setTotalExperienceUnit(candidateDto.getRelavantExperienceUnit());
					  candidateProfile.setRelavantExperience(candidateDto.getRelavantExperience());
					  candidateProfile.setTotalExperience(candidateDto.getTotalExperience());
					  candidateProfile.setPermanentCountryId(candidateDto.getPermanentCountryId());
					  candidateProfile.setPermanentStateId(candidateDto.getPermanentStateId());
					  candidateProfile.setPermanentPincode(candidateDto.getPermanentPincode());
					  candidateProfile.setCurrentCountryId(candidateDto.getCurrentCountryId());
					  candidateProfile.setCurrentStateId(candidateDto.getCurrentStateId());
					  candidateProfile.setCurrentPincode(candidateDto.getCurrentPincode());
					  if(jobPosting.getJobProfile() !=null) {
					  candidateProfile.setProfileTitle(jobPosting.getJobProfile().getJobTitle());
					  }
					 candidate.setCandidateProfile(candidateProfile);
					 candidate.setVerificationToken(CandidateToken.getVerificationToken());
					 candidate.setCandidateStatus(CandidateStatus.VERIFIED);
					 if(jobPosting.getPostingEndDate() !=null) {
					 candidate.setExpiryDate(jobPosting.getPostingEndDate());
					 }
					 candidate.setCreatedDate(CandidateToken.getCreatedDate());
				    
					 
					 CandidateApplication updateCandidateApplication=candidateApplicationRepository.findCustomCandidateIdAndJobPostingId(candidate.getId(),jobPosting.getId());
					 if(updateCandidateApplication!=null) {
				     updateCandidateApplication.setSkillExperience(candidateDto.getSkillExperience());
				     // update candidate status
					candidate.setStatus(24L);

					// Save Candidate Resume on File System
					if (resumeDoc != null && resumeDoc.length > 0) {
						MultipartFile resumeFile = resumeDoc[0];
						final String filename = candidate.getId() + candidate.getName() + resumeFile.getOriginalFilename();

						// get content type and other information
						UploadedFileResource resume = new UploadedFileResource();
						resume.setFilename(filename);
						resume.setContentType(resumeFile.getContentType());
						resume.setFilesize(resumeFile.getSize());
						
						candidate.setResume(resume);
						updateCandidateApplication.setResume(resume);
//						String uploadPath = this.fileStorage.getFileStorageLocation("/resumes");
						File file = this.fileStorage.getFileStorageLocation("/resumes", filename);
						// save file on file system
//						final String path = this.servletContext.getRealPath(uploadPath);
						resumeFile.transferTo(file);
					}

					// save candidate video on file system
					if (introVideo != null) {
						final String filename = candidate.getId() + candidate.getName() + introVideo.getOriginalFilename();

						// get content type and other information
						UploadedFileResource introVideoFile = new UploadedFileResource();
						introVideoFile.setFilename(filename);
						introVideoFile.setContentType(introVideo.getContentType());
						introVideoFile.setFilesize(introVideo.getSize());

						// set resume to candidate
						candidate.setIntroVideo(introVideoFile);
						updateCandidateApplication.setIntroVideo(introVideoFile);
//						String uploadPath = this.fileStorage.getFileStorageLocation()+"/introductionVideos";
						File file = this.fileStorage.getFileStorageLocation("/introductionVideos", filename);
						// save file on file system
//						final String path = this.servletContext.getRealPath(uploadPath);
						introVideo.transferTo(file);

					}					
					// check the mandatory skills criteria test
					if (!jobPosting.getApplyMandatorySkills().isEmpty()) {
						this.testCandidateOnMandatorySkillCriteria(jobPosting.getApplyMandatorySkills(), updateCandidateApplication);
					}
					
                    List<CandidateApplication> applicationList=new ArrayList<>();
                    //updateCandidateApplication.setCandidateId(candidate.getId());
                    applicationList.add(updateCandidateApplication);
					candidate.setCandiateApplication(applicationList);
					candidate = this.candidateRepository.save(candidate);
	            }else {
	            	//update candiate Detail Manually start herer
	            	if(candidateDto.getId() !=null) {
	            		Candidate updateCandidate=candidateRepository.getOne(candidateDto.getId());
	            		candidate.setId(updateCandidate.getId());
	            		candidate.setStatus(24L);
	            		 
	            		CandidateProfile getprofile=candidateProfileRepository.findByCandidateId(candidate.getId());
	            		  if(getprofile !=null) {
	            			  candidateProfile.setId(getprofile.getId());
	            		  }
	          candidateProfile.setCandidateId(candidate.getId());
			  candidateProfile.setIsSameTOPermanent(candidateDto.getSameAsPermanentAddress()); 
			  candidateProfile.setPermanentAddress(candidateDto.getPermanentAddress());
			  candidateProfile.setCurrentAddress(candidateDto.getCurrentAddress());
			  candidateProfile.setCurrentCityId(candidateDto.getCurrentCityId());
			  candidateProfile.setCityId(candidateDto.getPermanentCityId());
			  candidateProfile.setRelavantExperienceUnit(candidateDto.getTotalExperienceUnit());
			  candidateProfile.setTotalExperienceUnit(candidateDto.getRelavantExperienceUnit());
			  candidateProfile.setRelavantExperience(candidateDto.getRelavantExperience());
			  candidateProfile.setTotalExperience(candidateDto.getTotalExperience());
			  candidateProfile.setPermanentCountryId(candidateDto.getPermanentCountryId());
			  candidateProfile.setPermanentStateId(candidateDto.getPermanentStateId());
			  candidateProfile.setPermanentPincode(candidateDto.getPermanentPincode());
			  candidateProfile.setCurrentCountryId(candidateDto.getCurrentCountryId());
			  candidateProfile.setCurrentStateId(candidateDto.getCurrentStateId());
			  candidateProfile.setCurrentPincode(candidateDto.getCurrentPincode());
			  if(jobPosting.getJobProfile() !=null) {
			  candidateProfile.setProfileTitle(jobPosting.getJobProfile().getJobTitle());
			  }
			 candidate.setCandidateProfile(candidateProfile);
			 candidate.setVerificationToken(updateCandidate.getVerificationToken());
			 candidate.setCandidateStatus(CandidateStatus.VERIFIED);
			 if(jobPosting.getPostingEndDate() !=null) {
			 candidate.setExpiryDate(jobPosting.getPostingEndDate());
			 }
			 candidate.setCreatedDate(updateCandidate.getCreatedDate());
	            	}
	          candidate =candidateRepository.save(candidate);
	            
	         }
					if(candidateDto.getSkillSummary() !=null && candidateDto.getSkillSummary().size()>0){
						  List<CandidateSkillSummary> skills = candidateDto.getSkillSummary();
						  for (CandidateSkillSummary candidateSkillExperience : skills) {
							  candidateSkillExperience.setCandidateRegId(candidate.getId());
							  candidateSkillExperience.setHaveSkill(true);
							  System.out.println("CandidateSkillSummary");
						  }
						  this.candidateSkillSummaryRepository.saveAll(skills);
					  }
					
					response.put("message", "Hey, " + candidate.getName()
							+ " We've accepted your application and will send you " + " a response mail soon.");
		       
	            }
		//response.put("jobPostingId", jobPosting.getId());
		response.put("throughSocialLogin", candidateDto.isThroughSocialLogin());
		return response;
		
		
	}
	
	

	private void testCandidateOnMandatorySkillCriteria(Collection<ApplyMandatorySkills>
	  jobApplyMandatorySkills, CandidateApplication candidateApplication) {
	  Iterator<CandidateSkillExperience> candidateSkillExperience = candidateApplication.getSkillExperience().iterator();
	  final Map<Long, ApplyMandatorySkills> map = new HashMap<>();
	  jobApplyMandatorySkills.forEach(skill -> map.put(skill.getId(), skill));
	  boolean passedMandatoryCriteriaFiltering = true;
	  while (candidateSkillExperience.hasNext()) { 
		final CandidateSkillExperience candidateSkill = candidateSkillExperience.next(); 
		final Long mandatorySkillId= candidateSkill.getMandatorySkillId();
		
	if (map.containsKey(mandatorySkillId)) { 
		ApplyMandatorySkills ams =  map.get(mandatorySkillId);
	 
	if (ams.getMandatory() && candidateSkill.getHaveSkill()) {
	  final ExperienceCountUnit jobPostingExperienceCountUnit = ams.getExperienceCountUnit(); 
	  final ExperienceCountUnit candidateExperienceCountUnit =candidateSkill.getExperienceUnit();
	  final double jobPostingExperience =map.get(mandatorySkillId).getExperienceRequired();
	   final double candidateExperience = candidateSkill.getExperience();
	   final double jobPostingExperienceInMonth = (jobPostingExperienceCountUnit !=ExperienceCountUnit.YEAR) ? jobPostingExperience : jobPostingExperience * 12;
	  final double candidateExperienceInMonth = (candidateExperienceCountUnit !=ExperienceCountUnit.YEAR) ? candidateExperience : candidateExperience * 12;
	  
	  if (candidateExperienceInMonth < jobPostingExperienceInMonth) {
	         passedMandatoryCriteriaFiltering = false;
	         break; 
	         } 
	       } 
	 
	   // reject candidate if s/he does't have any mandatory skill 
	  else { 
		  passedMandatoryCriteriaFiltering = false; 
		  break; 
		  } 	  
	     } 
	  }
	
	  if (passedMandatoryCriteriaFiltering) 
		  // shortlisted
		  candidateApplication.setStatus(25L);
	  else 
		  // rejected 
		  candidateApplication.setStatus(26L);
	}
	 
		/**
		
		Candidate candidate = this.mapper.map(candidateDto, Candidate.class);
		JobPosting jobPosting = this.jobPostingService.getJobPostingById(candidateDto.getJobPostingId());
		candidate.setJobPosting(jobPosting);
		candidate.setAppliedDate(LocalDate.now());
		candidate.setAssessmentTestResult(AssessmentTestStatus.TestNotTakenYet);
		// if this request is for candidate registration
		if (candidate.getId() == null) {
			candidate.setCandidateStatus(CandidateStatus.REGISTERED);
			candidate.setStatus(23L);
			candidate = this.candidateRepository.save(candidate);
			CandidateVerificationToken verificationToken = new CandidateVerificationToken();
			String emailVerificationToken = UUID.randomUUID().toString();
			verificationToken.setToken(emailVerificationToken);
			verificationToken.setCandidateId(candidate.getId());

			// expire this token after two weeks
			verificationToken.setExpiryDate(jobPosting.getPostingEndDate());
			this.candidateVerificationTokenRepository.save(verificationToken);
			if (!candidateDto.isThroughSocialLogin()) {
				
				// Generate verification token for candidate
				String token = this.jobPostingService.getShareableToken(candidateDto.getJobPostingId());
				JobProfile jobProfile = jobPosting.getJobProfile();
				//	this.sendVerificationLinkMail(emailVerificationToken, candidate.getEmail(), token);
				Map<String, Object> props = new HashMap<>();
				
				props.put("accessToken", token);
				props.put("emailVerificationToken", emailVerificationToken);
				props.put("candidateEmail", candidate.getEmail());
				props.put("candidate", candidate);
				props.put("jobTitle", jobProfile.getJobTitle());
				props.put("port", "81737");
				props.put("hostName",hostName);
		
				this.sendVerificationLinkMail(props);

				response.put("message", "We've sent an email to " + candidate.getEmail()
						+ " with a verification link to verify your email.");
			}
			else {
				response.put("emailVerificationToken", emailVerificationToken);
				response.put("message", "Verified through social login");
			}
			candidateDto.setId(candidate.getId());
			response.put("data", candidateDto);
		} else {

			// update candidate status
			candidate.setStatus(23L);

			// Save Candidate Resume on File System
			if (resumeDoc != null && resumeDoc.length > 0) {
				MultipartFile resumeFile = resumeDoc[0];
				final String filename = candidate.getId() + candidate.getName() + resumeFile.getOriginalFilename();

				// get content type and other information
				UploadedFileResource resume = new UploadedFileResource();
				resume.setFilename(filename);
				resume.setContentType(resumeFile.getContentType());
				resume.setFilesize(resumeFile.getSize());

				// set resume to candidate
				candidate.setResume(resume);
//				String uploadPath = this.fileStorage.getFileStorageLocation("/resumes");
				File file = this.fileStorage.getFileStorageLocation("/resumes", filename);
				// save file on file system
//				final String path = this.servletContext.getRealPath(uploadPath);
				resumeFile.transferTo(file);

			}

			// save candidate video on file system
			if (introVideo != null) {
				final String filename = candidate.getId() + candidate.getName() + introVideo.getOriginalFilename();

				// get content type and other information
				UploadedFileResource introVideoFile = new UploadedFileResource();
				introVideoFile.setFilename(filename);
				introVideoFile.setContentType(introVideo.getContentType());
				introVideoFile.setFilesize(introVideo.getSize());

				// set resume to candidate
				candidate.setIntroVideo(introVideoFile);
//				String uploadPath = this.fileStorage.getFileStorageLocation()+"/introductionVideos";
				File file = this.fileStorage.getFileStorageLocation("/introductionVideos", filename);
				// save file on file system
//				final String path = this.servletContext.getRealPath(uploadPath);
				introVideo.transferTo(file);

			}

			// check the mandatory skills criteria test
			if (!jobPosting.getApplyMandatorySkills().isEmpty()) {
				this.testCandidateOnMandatorySkillCriteria(jobPosting.getApplyMandatorySkills(), candidate);
			}
			candidate = this.candidateRepository.save(candidate);
			response.put("message", "Hey, " + candidate.getName()
					+ " We've accepted your application and will send you " + " a response mail soon.");
		}
		response.put("throughSocialLogin", candidateDto.isThroughSocialLogin());
		return response;
	}
*/
	/*
	 * 
	 * */
	
	

	
	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> verifyCandidate(String receivedToken,HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		//CandidateVerificationToken token = this.candidateVerificationTokenRepository.findByToken(receivedToken);
		Candidate candidate=this.candidateRepository.findByVerificationToken(receivedToken);
		if (candidate == null) {
			throw new RuntimeException("Token Verification Failed. Token not found");
		} else {
			//long id = token.getId();
			candidate.setStatus(24L);
			candidate.setCandidateStatus(CandidateStatus.VERIFIED);
			candidate = this.candidateRepository.save(candidate);
			Claims claims=(Claims) request.getAttribute("claims");
			String jobPostingId=claims.get("jobPostingId").toString();
			JobPostingDto jobPostingDto = this.jobPostingService.getById(Long.parseLong(jobPostingId));
			//CandidateApplication candidateApplication=candidateApplicationRepository.findCustomCandidateIdAndJobPostingId(candidate.getId(),Long.parseLong(jobPostingId));
			response.put("message", "Email verification is successfull");
			CandidateRegisterationDto candidRegister = this.mapper.map(candidate, CandidateRegisterationDto.class);
//			candidRegister.setSkillExperience(skillExperience);
			
			response.put("candidate",candidRegister );
			//JobPostingDto jobPostingDto = this.jobPostingService.getById(candidateDto.getJobPostingId());

			response.put("jobPosting", jobPostingDto);
			// TODO: if candidate is verified check its assessment test and interview and
			// send link for that
		}
		return response;
	}

	private void sendVerificationLinkMail(Map<String, Object> props) throws IOException,MessagingException {
		// create verification link
//		String link = UriComponentsBuilder
//				.fromUriString("http://hiretool.shivit.co.in/portal/candidate-profile-complete-form")
//				.queryParam("token", props).queryParam("verificationToken", emailVerificationToken).build().toString();

//		StringBuilder sb = new StringBuilder();
//		sb.append("<html>").append("<body>").append("<a href=\"" + link + "\">Click Here To  Verify Your Email.</a>")
//				.append("</body>").append("</html>");
	

		Context context = new Context();
		context.setVariables(props);
		String html = templateEngine.process("mail/mail", context);
		
		MimeMessage message = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo((String) props.get("candidateEmail"));
			helper.setSubject("Complete Your Job Application For " + (String) props.get("jobTitle"));
			helper.setText(html, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		// this.mailSender.send(message);
		System.out.println(mailSender);
		// send email asynchronously
		new Thread(() -> mailSender.send(message)).start();
	}

	@Override
	@Transactional(value = "tenantTransactionManager")	
	public CandidateRegisterationDto getById(Long candidateId,HttpServletRequest request) {
		Candidate candidate = this.candidateRepository.findById(candidateId).get();
		CandidateRegisterationDto dto = this.mapper.map(candidate, CandidateRegisterationDto.class);
		
		//CandidateApplication candidates = this.candidateApplicationRepository.findByCandidateId(candidate.getId());
//		JsonParser parser = JsonParserFactory.getJsonParser();
		//Claims claims =  jwtTokenutil.getAllClaimsFromToken(token);
		Claims claims=(Claims) request.getAttribute("claims");
		List<CandidateSkillSummary> skills =  this.candidateSkillSummaryRepository.findCustomByCandidateRegId(candidateId);
		dto.setSkillSummary(skills);
		/*
		 * String jonPostingId = claims.get("jobPostingId").toString();
		 * CandidateRegisterationDto dto = this.mapper.map(candidate,
		 * CandidateRegisterationDto.class);
		 * dto.setJobPostingId(Long.parseLong(jonPostingId));
		 */
		//while editing the candidate or view we need list of candidate appliction id @author aman 16-friday-21
		//dto.setCandidateApplication(candidateapplication);
		
		if(claims.get("jobPostingId")!=null) {
			String jonPostingId = claims.get("jobPostingId").toString();
			
			dto.setJobPostingId(Long.parseLong(jonPostingId));
		}else {
			CandidateProfile candidateProfile=candidateProfileRepository.findByCandidateId(candidate.getId());
			System.out.println("candidateProfile :"+candidateProfile);
			if(candidateProfile != null) {
				dto.setPermanentAddress(candidateProfile.getPermanentAddress());
				dto.setPermanentCountryId(candidateProfile.getPermanentCountryId());
				dto.setPermanentStateId(candidateProfile.getPermanentStateId());
				dto.setPermanentCityId(candidateProfile.getCityId());
				dto.setPermanentPincode(candidateProfile.getPermanentPincode());
				dto.setCurrentAddress(candidateProfile.getCurrentAddress());
				dto.setCurrentCountryId(candidateProfile.getCurrentCountryId());
				dto.setCurrentStateId(candidateProfile.getCurrentStateId());
				dto.setCurrentCityId(candidateProfile.getCurrentCityId());
				dto.setCurrentPincode(candidateProfile.getCurrentPincode());
				System.out.println("candidate Dto :"+dto.getPermanentAddress());

			}
		}
		return dto;
	}

	
	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> getCandidateList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId) {
		UserEntity currentUser = this.appService.getCurrentUser();
		if (currentUser == null)
			throw new CustomException("Session has been timed out!");

		Map<String, Object> response = new HashMap<String, Object>();
		// List<ScreenFilter> screenFilter = new ArrayList<ScreenFilter>();

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
//		int totalCount = Integer.parseInt(Long.valueOf(this.getCount()).toString());
		Page<CandidateDto> branchList = null;
//		if (totalCount == 0) {
//			throw new ResourceNotFoundException("No Record Found");
//		}
		if ((search == null) || (search.isEmpty())) {
			branchList = this.candidateRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			branchList = this.candidateRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		for (CandidateDto candidateDto : branchList) {
			candidateDto.setStatusDetail(this.statusRepo.findById(candidateDto.getStatus()).get());
		}
		response.put("totalCount", this.candidateRepository.count());
		response.put("data", branchList);
		return response;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Override
	public Map<String, Object> getQuestionnaireList(Map<String, Object> params) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Candidate.class, params));
		return response;
	}
}
