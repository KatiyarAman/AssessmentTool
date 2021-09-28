package com.sterp.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		ParameterBuilder screenIdParameter = new ParameterBuilder();
		screenIdParameter.name("moduleId").modelRef(new ModelRef("Long")).parameterType("header").required(false)
				.build();
//		ParameterBuilder actionIdParameter = new ParameterBuilder();
//		actionIdParameter.name("actionId").modelRef(new ModelRef("integer")).parameterType("header").required(false)
//				.build();
		List<springfox.documentation.service.Parameter> aParameters = new ArrayList<springfox.documentation.service.Parameter>();
		aParameters.add(screenIdParameter.build());
		// aParameters.add(actionIdParameter.build());
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(getApiInformation())
				.securitySchemes(Arrays.asList(apiKey())).securityContexts(Collections.singletonList(securityContext()))
				.globalOperationParameters(aParameters).directModelSubstitute(Timestamp.class, Long.class);

//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build().apiInfo(getApiInformation())
//				.securitySchemes(Arrays.asList(apiKey())).securityContexts(Collections.singletonList(securityContext()))
//				.directModelSubstitute(Timestamp.class, Long.class);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { authorizationScope };
		return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
	}

	private ApiInfo getApiInformation() {
		return new ApiInfo("Shivit Mini - ERP", "Developed and Designed by Shivit Technologies Pvt.Ltd", "1.0",
				"API Terms of Service URL",
				new Contact("Shivit Technologies Pvt.Ltd", "http://www.shivit.com/", "info@shivit.com"), "API License",
				"API License URL", Collections.emptyList());
	}

	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}
}