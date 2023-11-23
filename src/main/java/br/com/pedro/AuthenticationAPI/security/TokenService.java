package br.com.pedro.AuthenticationAPI.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.pedro.AuthenticationAPI.dto.UserDTO;
import br.com.pedro.AuthenticationAPI.entities.User;

@Service
public class TokenService {
	
	@Value("$api.security.token.secret")
	private String secret;

public String generateToken(User user) {
		
		
		
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("authenticationAPI")
					.withSubject(user.getUsername())
					.withExpiresAt(generateExpirantionDate())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("ERRO WHILE GENERATION TOKEN");
		}
	}

public String validateToken(String token) {
	try {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.require(algorithm)
				.withIssuer("authenticationAPI")
				.build()
				.verify(token)//descriptografou o otken
				.getSubject();
	} catch (JWTVerificationException e) {
		// TODO: handle exception
		return "";
	}
}

	private Instant generateExpirantionDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
