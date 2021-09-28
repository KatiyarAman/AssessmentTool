package com.sterp.multitenant.mastertenant.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.mastertenant.entity.Clientage;
import com.sterp.multitenant.mastertenant.entity.MasterTenant;
import com.sterp.multitenant.mastertenant.repository.ClientageRepository;
import com.sterp.multitenant.mastertenant.service.ClientageService;
import com.sterp.multitenant.mastertenant.service.MasterTenantService;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.service.SecurityAppService;

@Service
public class ClientageServiceImpl implements ClientageService {

	@Autowired
	ClientageRepository clientageRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	SecurityAppService securityAppService;

	@Autowired
	MasterTenantService masterTenantService;

	@Override
	public Long getCount() {
		return clientageRepository.count();
	}

	@Override
	public Clientage getClientageById(Integer clientId) {
		return this.clientageRepository.findById(clientId).get();
	}

	@Override
	public Clientage getClientageByEmail(String email) {
		return this.clientageRepository.findByEmail(email);
	}

	@Override
	public Clientage getClient(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTenantIdBySubDomain(String subDomain) {
		Optional<Clientage> optionalClient = this.clientageRepository.findBySubDomainName(subDomain);
		if (optionalClient.isPresent()) {
			Clientage clientage = optionalClient.get();
			MasterTenant masterTenant = this.masterTenantService.loadMasterTenantByClientage(clientage);
			return masterTenant != null ? masterTenant.getTenantClientId() : 0;
		}
		throw new ResourceNotFoundException(
				"Domain name not registered with us. If issue persist contact our service provider.");
	}

	@Override
	public Clientage getClientageByTenantId(Integer tenantClientId) {
		return this.masterTenantService.getById(tenantClientId).getClientage();
	}
}
