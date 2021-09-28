package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateRegisterationDto;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public interface CandidateService {
	Map<String, Object> applyOnJob(CandidateRegisterationDto dto, MultipartFile resumeDoc[], MultipartFile introVideoFile,String hotsNameForDynamicEmail) throws IllegalStateException, IOException, MessagingException;
	Map<String, Object> verifyCandidate(String receivedToken,HttpServletRequest request);
	CandidateRegisterationDto getById(Long candidateId,HttpServletRequest request);
//	Map<String, Object> getCandidateList(Map<String, Object> params);
	Map<String, Object>  getCandidateList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);
	Map<String, Object> getQuestionnaireList(Map<String, Object> params);
}
