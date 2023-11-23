package br.com.pedro.AuthenticationAPI.security;

import java.io.IOException;
import java.security.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pedro.AuthenticationAPI.repositories.UserDetailsRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
	
	@Autowired
	TokenService tokenService;
	@Autowired
	UserDetailsRepository userDetailsRepository ;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException  {
	
            var token = recoverToken(request);
            if (token != null) {
                
                var username = tokenService.validateToken(token);                    
                UserDetails user = userDetailsRepository.findByUsername(username);
                
                    

                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                 
                
            }
         

        filterChain.doFilter(request, response);
	}
	
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null) return null;
		
		return authHeader.replace("Bearer ", "");
			
	}

}
