package com.sterp.multitenant.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.entity.DeviceMetadata;

@Repository
public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {
	List<DeviceMetadata> findByUserId(Long userId);
}
