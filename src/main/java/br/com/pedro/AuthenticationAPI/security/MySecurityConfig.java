package br.com.pedro.AuthenticationAPI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	
	@Bean 
	//objetivo desse metodo é dizer: quem é bloqueado, quem é liberado e qual filtro vai fazer o tratamento
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable() //desabilito o csrf pq eu que vou tratar a autenticação dos usuarios
			.authorizeHttpRequests()  // agora as requisições htttp sao passiveis de autorização
			.requestMatchers(HttpMethod.GET,"/users").permitAll()// especificando quem é liberado
			.anyRequest().authenticated().and().cors(); // todas as urls precisaram de autenticação
	
		http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
