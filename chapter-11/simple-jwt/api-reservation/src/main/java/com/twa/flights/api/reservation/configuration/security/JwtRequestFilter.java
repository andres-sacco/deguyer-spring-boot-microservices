package com.twa.flights.api.reservation.configuration.security;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // The secret key shared by the generating application.
    private String secret =
            "qwertyuiopasdfghjklzxcvbnm123456891012132twaprobandogeneraciondecontrase";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
        }

        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // Parse and validate the token
                Claims claims =
                        Jwts.parser()
                                .setSigningKey(
                                        Base64.getEncoder().encodeToString(secret.getBytes()))
                                .parseClaimsJws(jwt)
                                .getBody();

                // If valid, continue processing
                // Extract username and roles from the token
                String username = claims.getSubject(); // Typically, the subject is the username

                // Convert roles to GrantedAuthority
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority((String) claims.get("email"));
                List<SimpleGrantedAuthority> authorities = List.of(authority);

                // Create UserDetails
                UserDetails userDetails = new CustomUserDetailsDTO(username, null, authorities);

                // Create AuthenticationToken
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, jwt, userDetails.getAuthorities());

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                // Handle invalid JWT token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}