package com.microblogging.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microblogging.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtTokenChecker {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	
	public String doTokenChecker(String JwtToken) {
		
		String checker1 = "";

		//final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
		if (JwtToken != null && JwtToken.startsWith("Bearer ")) {
			System.out.println(JwtToken);
			System.out.println("aloha");
			jwtToken = JwtToken.substring(7);
			try {
				
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				System.out.println(username);
			} catch (IllegalArgumentException e) {
				System.out.println("error token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			throw new IllegalArgumentException();
		}

		//Once we get the token validate it.
		if (username != null) {

			//UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken)) {

				checker1 = "true";
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
				//SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				checker1 = "false";
			}
		}
		System.out.println(checker1);
		return checker1;
	
		
	}

}
