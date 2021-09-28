package com.sterp.multitenant.tenant.core.services.impl;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.repository.ScreenFilterRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.ScreenFilterService;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class ScreenFilterServiceImpl implements ScreenFilterService {

	@Autowired
	AppService appService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ScreenFilterRepository screenFilterRepository;

	/*
	 * @Override public List<ScreenFilter> getScreenFilterByModuleIdAndUserId(long
	 * moduleId, long userId) { // TODO Auto-generated method stub return null; }
	 */

//	@Override
//	public List<ScreenFilterDTO> saveAll(List<ScreenFilterDTO> screenFilterDTO) {
//		if(screenFilterDTO.size() > 0) {
//			List<ScreenFilter> screenFilterList = new ArrayList<ScreenFilter>();
//			screenFilterDTO.forEach((filterDto) -> {
//				ScreenFilter filter = new ScreenFilter();
//				filter.setId(filterDto.getId()); 
//				filter.setHidden(filterDto.isHidden());
//				filter.setUserId(filterDto.getUserId());
//				filter.setModuleId(filterDto.getModuleId());	
//				filter.setFilterValue(filterDto.getFilterValue());  
//				filter.setFilterRange(filterDto.getFilterRange());
//				filter.setFieldIndexingId(filterDto.getFieldIndexingId());
//				screenFilterList.add(filter);
//			});
//			
//			//List<ScreenFilter> screenFilterList = Arrays.asList(modelMapper.map(screenFilterDTO, ScreenFilter[].class));
//			
//			screenFilterRepository.saveAll(screenFilterList);
//			return screenFilterRepository.findAllByUserIdAndModuleId
//					(screenFilterDTO.get(0).getUserId(), screenFilterDTO.get(0).getModuleId());
//		}
//		return new ArrayList<ScreenFilterDTO>();
//		
//	}

	@Override
	public List<ScreenFilterDTO> getScreenFilter(long userId, long moduleId) {
		return this.screenFilterRepository.findAllByUserIdAndModuleId(userId, moduleId);
	}

	@Override
	public List<ScreenFilter> save(List<ScreenFilter> screenFilters) {
		return this.screenFilterRepository.saveAll(screenFilters);
	}

	@Override
	public List<ScreenFilter> deleteAll(List<ScreenFilter> screenFilters) {
		this.screenFilterRepository.deleteAll(screenFilters);
		return Collections.emptyList();
	}

}
