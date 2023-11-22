package br.com.pedro.AuthenticationAPI.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	
	
	
	
   
	
	@Bean 
	//objetivo desse metodo é dizer: quem é bloqueado, quem é liberado e qual filtro vai fazer o tratamento
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecutiry) throws Exception{
		
		return httpSecutiry
		   .csrf(csrf -> csrf.disable()) //desabilito o csrf pq eu que vou tratar a autenticação dos usuarios
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // agora as requisições htttp sao passiveis de autorização
			.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.POST,"/users/login").permitAll()
			.requestMatchers(HttpMethod.POST,"/users").permitAll()
			// especificando quem é liberado
			.anyRequest().authenticated()
			
					) // todas as urls precisaram de autenticação
			.build();
	}
	
	  @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  

	  
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	 

}
