package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccesHandler;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(password -> {
			return encoder.encode(password);
		}); 
		
		builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
			.withUser(users.username("andres").password("12345").roles("USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/listar","/home","/layout/**").permitAll()
			/*.antMatchers("/ver/**").hasAnyRole("USER")
			.antMatchers("/uploads/**").hasAnyRole("USER")
			.antMatchers("/form/**").hasAnyRole("ADMIN")
			.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
			.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.successHandler(successHandler)
				.loginPage("/login").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error_403");
	}	
	
	
	
	
}
