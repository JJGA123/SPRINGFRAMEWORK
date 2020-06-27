package com.bolsadeideas.springboot.app.auth.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.bolsadeideas.springboot.app.auth.SimpleGrantedAuthoritiesMixin;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String header = request.getHeader("Authorization");
		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		boolean validoToken;
		Claims claims = null;
		SecretKey secretKey = new SecretKeySpec("Alguna.Llave.Secreta.Alguna.Llave.Secreta.Alguna.Llave.Secreta.Alguna.Llave.Secreta".getBytes(), SignatureAlgorithm.HS512.getJcaName());
		try {
			claims = Jwts.parser()
	                .setSigningKey(secretKey)
	                .parseClaimsJws(header.replace("Bearer ", ""))
	                .getBody();
			validoToken=true;
		} catch (JwtException e) {
			// TODO: handle exception
			validoToken=false;
		}
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		if(validoToken) {
			String username = claims.getSubject();
			Object roles = claims.get("authorities");
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthoritiesMixin.class)
					.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
			authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	protected boolean requiresAuthentication(String header) {
		if(header == null || !header.startsWith("Bearer ")) {
			return false;
		}
		return true;
	}

	
	
}
