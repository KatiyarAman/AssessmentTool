package com.sterp.multitenant.tenant.settings.questionnaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.questionnaire.entity.QuestionnaireSettings;

@Repository
public interface QuestionnaireRepository extends JpaRepository<QuestionnaireSettings, Long> {

}
