package com.sterp.multitenant.mastertenant.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.mastertenant.entity.Clientage;
import com.sterp.multitenant.mastertenant.entity.MasterTenant;
import com.sterp.multitenant.mastertenant.repository.MasterTenantRepository;
import com.sterp.multitenant.mastertenant.service.MasterTenantService;

/**
 * @author Navinder Singh
 */

@Service
public class MasterTenantServiceImpl implements MasterTenantService {
	private static final Logger log = LoggerFactory.getLogger(MasterTenantServiceImpl.class);

	@Autowired
	MasterTenantRepository masterTenantRepository;

	@Override
	public MasterTenant findByClientId(Integer clientId) {
		log.info("findByClientId() method call");
		return masterTenantRepository.findByTenantClientId(clientId);
	}

	@Override
	public MasterTenant loadMasterTenantByClientage(Clientage clientage) {
		return this.masterTenantRepository.findByClientage(clientage);
	}

	@Override
	public MasterTenant getById(int id) {
		Optional<MasterTenant> optional = this.masterTenantRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
