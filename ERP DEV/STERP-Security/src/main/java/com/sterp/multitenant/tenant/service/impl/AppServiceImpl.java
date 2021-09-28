package com.sterp.multitenant.tenant.service.impl;

import java.beans.PropertyDescriptor;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.repository.UserRepository;

@Service("AppService")
public class AppServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	HttpSession session;

	public long hoursDifference(Date date1, Date date2) {
		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		return (long) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	}

	public UserEntity getCurrentUser() {
		UserEntity user = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (userName != null) {
			user = this.userRepository.findByUsername(userName);
		}
		return user;
	}

	public LocalDate validateDate(String search) {
		try {
			return LocalDate.parse(search);
		} catch (Exception e) {
			return null;
		}
	}

	public UserEntity getCurrentUser(String username) {
		UserEntity user = null;
		if (username != null) {
			user = this.userRepository.findByUsername(username);
		}

		return user;
		// return
		// this.userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	public HttpHeaders getXCountHeader(long count) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(count));
		return headers;
	}

	public Pageable createPageRequest(int firstResult, int maxResults, String sortType, String sortField) {
		if (sortType.equalsIgnoreCase("ASC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).ascending());
		}
		if (sortType.equalsIgnoreCase("DESC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).descending());
		}
		return PageRequest.of(firstResult, maxResults);
	}

	public Set<String> getNullPropertyNames(Object source, String... extra) {
		BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		for (String args : extra) {
			emptyNames.add(args);
		}
		return emptyNames;
	}

	public Long getSessionSite() {
		return Long.parseLong(session.getAttribute("siteId").toString());
	}

}
