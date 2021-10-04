package com.poly;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.entity.Account;
import com.poly.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(Username ->{
			try {
				Account user =accountService.findById(Username);
				String password =pe.encode(user.getPassword());
				String[] roles= user.getAuthorities().stream()
						.map(er -> er.getRole().getRole_id())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(Username).password(password).roles(roles).build();
			} catch (Exception e) {
				throw new UsernameNotFoundException(Username +"not found!");
			}
		});
		
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		 http.csrf().disable();
		 http.authorizeRequests()
		 .antMatchers("/order/**").authenticated()
		 .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
		 .antMatchers("/rest/authorities").hasRole("DIRE")
		 .anyRequest().permitAll();
		 http.formLogin()
		 .loginPage("/security/login/form")
		 .loginProcessingUrl("/security/login")
		 .defaultSuccessUrl("/security/login/success",false)
		 .failureUrl("/security/login/erorr");
		 http.rememberMe()
		 .tokenValiditySeconds(86400);
		 http.exceptionHandling()
		 .accessDeniedPage("/security/unauthoried");
		 
		 http.logout()
		 .logoutUrl("/security/logoff")
		 .logoutSuccessUrl("/security/logoff/success");
		}
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
	
	
		
}
