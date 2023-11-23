package br.com.pedro.AuthenticationAPI.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	
  @Autowired
  SecurityFilter securityFilter;
	
	@Bean 
	//objetivo desse metodo é dizer: quem é bloqueado, quem é liberado e qual filtro vai fazer o tratamento
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		return httpSecurity
		   .csrf(csrf -> csrf.disable()) //desabilito o csrf pq eu que vou tratar a autenticação dos usuarios
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // agora as requisições htttp sao passiveis de autorização
			.authorizeHttpRequests(authorize -> authorize
					// especificando quem é liberado
			.requestMatchers(HttpMethod.POST,"/users/login").permitAll()
			.requestMatchers(HttpMethod.POST,"/users/create").hasRole("ADMIN")
			
			// todas as urls precisaram de autenticação
			.anyRequest().authenticated()
			
					) 
			.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
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
