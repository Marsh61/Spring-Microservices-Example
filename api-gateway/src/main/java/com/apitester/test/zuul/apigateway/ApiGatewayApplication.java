package com.apitester.test.zuul.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Sso
@EnableZuulProxy
@RestController
public class ApiGatewayApplication extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/index.html","/webjars/**","/")
					.permitAll()
				.anyRequest()
				.authenticated()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/index.html").permitAll()
				.and().csrf().disable();//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	@RequestMapping("/user")
	public Principal user (Principal principal) {
	    System.out.println(principal.getName());
		return principal;
	}


	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}