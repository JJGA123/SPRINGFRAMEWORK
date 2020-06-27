package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bolsadeideas.springboot.app.auth.filter.JWTAuthenticationFilter;
import com.bolsadeideas.springboot.app.auth.filter.JWTAuthorizationFilter;
import com.bolsadeideas.springboot.app.auth.handler.LoginSuccesHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	/*@Autowired 
	private DataSource dataSource;*/
	
	@Autowired
	private JpaUserDetailsService userDetailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		/*PasswordEncoder encoder = passwordEncoder;
		builder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("select username, password, enable from users where username=?")
			.authoritiesByUsernameQuery("select u.username,a.authority from authorities a inner join users u on (a.user_id = u.id) where u.username=?"); 
		/*UserBuilder users = User.builder().passwordEncoder(password -> {
			return encoder.encode(password);
		}); 
		
		builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
			.withUser(users.username("andres").password("12345").roles("USER"));*/
		
		builder.userDetailsService(userDetailService)
			.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/listar","/locale","/home","/layout/**","/login").permitAll()
			/*.antMatchers("/ver/**").hasAnyRole("ROLE_ADMIN")
			.antMatchers("/uploads/**").hasAnyRole("ROLE_ADMIN")
			.antMatchers("/form/**").hasAnyRole("ROLE_ADMIN")
			.antMatchers("/eliminar/**").hasAnyRole("ROLE_ADMIN")
			.antMatchers("/factura/**").hasAnyRole("ROLE_ADMIN")*/
			.anyRequest().authenticated()
			/*.and()
			.formLogin()
				.successHandler(successHandler)
				.loginPage("/login").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error_403")*/
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}	
	
	
	
	
}
