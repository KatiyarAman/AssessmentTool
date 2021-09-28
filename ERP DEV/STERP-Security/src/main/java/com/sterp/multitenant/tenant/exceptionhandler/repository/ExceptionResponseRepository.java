package com.sterp.multitenant.tenant.exceptionhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.exceptionhandler.ExceptionResponse;

@Repository
public interface ExceptionResponseRepository extends JpaRepository<ExceptionResponse, Integer> {

}
