package com.sterp.multitenant.tenant.branch.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.branch.dto.BranchRequestDto;
import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface BranchService {

	Map<String, Object> addOrUpdateBranch(HttpServletRequest request, Branch branch, MultipartFile[] files);

	Branch getBranchById(Long branchId, boolean iscacheable);

	Map<String, Object> getSelectionList(boolean iscacheable);

	public Map<String, Object> getBranchList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);

}
