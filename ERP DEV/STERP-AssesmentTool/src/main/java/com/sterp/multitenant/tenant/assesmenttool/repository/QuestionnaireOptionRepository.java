package com.sterp.multitenant.tenant.assesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.assesmenttool.entity.QuestionnaireOption;


@Repository
public interface QuestionnaireOptionRepository extends JpaRepository<QuestionnaireOption, Long> {

}
