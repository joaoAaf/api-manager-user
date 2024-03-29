package apiManagerUser.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import apiManagerUser.repository.UserRepository;
import apiManagerUser.services.TokenService;
import apiManagerUser.services.exception.ObjectNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository repo;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token;
		var authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			token = authorizationHeader.replace("Bearer ", "");
			
			var subject = this.tokenService.getSubject(token);
			
			var userOpt = this.repo.findByEmail(subject);

			var user = userOpt.orElseThrow(() -> new ObjectNotFoundException("Usuário não existe"));
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
