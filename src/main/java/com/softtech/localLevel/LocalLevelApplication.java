package com.softtech.localLevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.softech.localLevel.filter.LocalLevelFilter;

@SpringBootApplication
public class LocalLevelApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LocalLevelApplication.class);
	}
	

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean bean = new FilterRegistrationBean(new LocalLevelFilter());
//		// Mapping filter to a Servlet
////		bean.addServletRegistrationBeans(new ServletRegistrationBean[] { servletRegistrationBean() });
//		return bean;
//	}

	public static void main(String[] args) {
		SpringApplication.run(LocalLevelApplication.class, args);
	}

}
