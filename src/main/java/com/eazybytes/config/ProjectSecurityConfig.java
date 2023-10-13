package com.eazybytes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
;


@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//@Autowired
	//EazySchoolUnamePwdAuthProvider authProvider;
	
	
	//authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		demo 1
//		http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();
//		http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();
		
		//If you are using h2 database:-
		//http.csrf().ignoringAntMatchers("/h2-console/**")
		
//		demo 2
//		http.csrf().disable(). //Spring security (by using CSRF) by default throws Error 403 when you try to do POST and PUT or modify data in app, hence for now we shall disable that security by doing .csrf().disable()
		//demo 3
//		http.headers().frameOptions().disable(); // H2 by default comes with frame options in header and our csrf security disables any request having frame options in headers. Hence 
		
		http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/register").ignoringAntMatchers("/api/**").and().
		authorizeRequests().
		mvcMatchers("/dashboard").authenticated().
		mvcMatchers("/updateProfile").authenticated().
		mvcMatchers("/displayProfile").authenticated().
		mvcMatchers("/student/**").hasRole("STUDENT").
		mvcMatchers("/public/**").permitAll().
		mvcMatchers("/admin/**").hasRole("ADMIN").
		mvcMatchers("/api/**").authenticated(). //Anyone who has credentials can access service 
		mvcMatchers("/courses").permitAll().
		mvcMatchers("/about").permitAll().
		mvcMatchers("/login").authenticated().//shows login form to authenticate first
		mvcMatchers("/home").permitAll().
		mvcMatchers("/contact").permitAll().
		mvcMatchers("/holiday/*").permitAll().
		mvcMatchers("/saveMsg").permitAll().
		mvcMatchers("/login").permitAll().
		mvcMatchers("/h2-console/**").permitAll().
		and().formLogin().
		loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll().and(). 
		logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll(). 
		and().httpBasic();	// httpBasic() -> Rest aPI, formLogin() -> MVCx
		

		
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.inMemoryAuthentication().withUser("anu").password("anu").roles("user").and().
//		withUser("shreekanth").password("shreekanth").roles("admin").and(). 
//		passwordEncoder(NoOpPasswordEncoder.getInstance()); //In tables you must not store plain text passswords, so you have to encrypt before storing them
//		//There are many implementations passwordEncoder, but over here since this app is not going for any production, m using NoOpPasswordEncoder
//	}
	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.authenticationProvider(authProvider); 
//	}
	
	@Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}
