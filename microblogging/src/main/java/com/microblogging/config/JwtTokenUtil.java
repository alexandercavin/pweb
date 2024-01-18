package com.microblogging.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

	//@Value("${jwt.secret}")
	private String secret = "0100011010100110011110100010101011000110000111110110011110111010100011111001101111101001011010100101111000010010101001111011111001110000000011101111110010001000011100010000001011101111011010101101011010100010110100001001011000001010110000100010110011110000";

	public String getUsernameFromToken(String token) {
		System.out.println("123123");
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
/*
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		System.out.println("2323r2r");
		System.out.println(claims);
		return claimsResolver.apply(claims);
	}*/
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		System.out.println("2323r2r");
		//System.out.println(claims);
		final Claims claims = getAllClaimsFromToken(token);
		
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		System.out.println("asoy");
		System.out.println(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
		
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token) {
		final String username = getUsernameFromToken(token);
		return (!isTokenExpired(token));
	}
}
