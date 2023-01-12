package org.durga.user_service.util;

import org.durga.user_service.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
        @Autowired
        private JWTUtil jwtUtil;
        @Autowired
        private CustomUserDetailsService customUserDetailsService;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {

            String tokenHeader = request.getHeader("Authorization");
            String username=null;
            String jwttoken = null;
            if(tokenHeader !=null && tokenHeader.startsWith("Bearer ")) {
                jwttoken = tokenHeader.substring(7);

                username = jwtUtil.getUsernameFromToken(jwttoken);

                UserDetails loadUserByUsername = customUserDetailsService.loadUserByUsername(username);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loadUserByUsername ,  				null, loadUserByUsername.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication( usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }
}


