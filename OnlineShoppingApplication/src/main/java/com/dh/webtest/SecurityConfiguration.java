package com.dh.webtest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource datasource;
	
//	@Autowired
//	Customer customer;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers("/public/**").permitAll().antMatchers("/admin/**")
				.access("hasRole('ADMIN')").and().logout().logoutSuccessUrl("/login")
				.and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		

	auth.jdbcAuthentication()
				.dataSource(datasource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username, password, true as enabled from customers where username=?")
				.authoritiesByUsernameQuery("select username, role from customers where username=?")
				.rolePrefix("");

		/*
		auth.inMemoryAuthentication().withUser("admin").password("admin")
		.roles("ADMIN", "USER");*/
	

/*public String BcryptDecoder(String password){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(email.equalsIgnoreCase(customer.getEmail()) && encoder.matches(password, customer.getPassword())){
        	if( encoder.matches(password, customer.getPassword())){
        	String status = "true";	
        	//userService.deactivateUserByID(customer.getCustomerId());
        //   RequestAttributes.addAttribute("successmsg", "Your account has been deactivated successfully.");
            model.setViewName("redirect:/logout");
        }else{
            //redirectAttributes.addFlashAttribute("errormsg", "Email or Password is incorrect");
            model.setViewName("redirect:/app/profile/deactivate");
        }return password;
	}*/
}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}