package com.apitester.test.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.apitester.test.dbservice.repository")
@SpringBootApplication()
@EnableResourceServer
public class DbServiceApplication extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/rest/db/getQuotes/**")
				.permitAll()
				.anyRequest()
				.authenticated();
	}


	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}
}
