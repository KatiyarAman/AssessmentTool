package com.sterp.multitenant.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.entity.DeviceMetadata;
import com.sterp.multitenant.tenant.entity.DeviceVerificationOTP;

@Repository
public interface DeviceVerificationOTPRepository extends JpaRepository<DeviceVerificationOTP, Long>{
	
	DeviceVerificationOTP findByDeviceMetadata(DeviceMetadata deviceMetadata);
	
}
