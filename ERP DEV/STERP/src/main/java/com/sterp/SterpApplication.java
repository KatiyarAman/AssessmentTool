package com.sterp;

import java.util.concurrent.Executor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sterp.multitenant.constant.AppProperties;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAsync
@EnableConfigurationProperties(AppProperties.class)
@EnableScheduling
//@ImportResource("sterpapplication.xml")
public class SterpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SterpApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

//	@Bean
//	public FileStorageProperties file() {
//		return new FileStorageProperties();
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(3);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("AsynchThread-");
		executor.initialize();
		return executor;
	}

	// Fix the CORS errors
//    @Bean
//    public FilterRegistrationBean simpleCorsFilter() {  
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
//        CorsConfiguration config = new CorsConfiguration();  
//        config.setAllowCredentials(true); 
//        // *** URL below needs to match the Vue client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("http://hiretool.shivit.co.in/")); 
//        config.setAllowedMethods(Collections.singletonList("*"));  
//        config.setAllowedHeaders(Collections.singletonList("*"));  
//        source.registerCorsConfiguration("/**", config);  
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
//        return bean;  
//    }

}
