package EPIC_ENERGY_SERVICES_BackEnd.security;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.Utente;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.UtenteService;
import EPIC_ENERGY_SERVICES_BackEnd.exceptions.UnauthorizedException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	JWTTools jwttools;
	@Autowired
	UtenteService utenteService;

	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		 AntPathMatcher matcher = new AntPathMatcher();
		    return matcher.match("/auth/**", request.getServletPath());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Per favore passa il token nell'authorization header");
		String token = authHeader.substring(7);

		jwttools.verifyToken(token);
		String id = jwttools.extractSubject(token);
		Utente currentUser = utenteService.findById(UUID.fromString(id));

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(currentUser, null,
				currentUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		filterChain.doFilter(request, response);

	}

	
}
