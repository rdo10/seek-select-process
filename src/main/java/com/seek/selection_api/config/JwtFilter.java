package com.seek.selection_api.config;

import com.seek.selection_api.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  private final UserService userService;

  public JwtFilter(JwtUtil jwtUtil, UserService userService) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    System.out.println("here "+authHeader);
    if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
      System.out.println("Invalid Auth Header");
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.split(" ")[1].trim();

    if (!this.jwtUtil.isValid(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }

    String username = this.jwtUtil.getUsername(jwt);
    System.out.println("Username: " + username);
    User user = (User) this.userService.FindUser(username);

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword(), user.getAuthorities());

    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    System.out.println(authenticationToken);
    filterChain.doFilter(request,response);

  }
}
