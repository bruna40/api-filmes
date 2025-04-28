package br.com.fillms.projeto_java_ia.security;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.User;

import br.com.fillms.projeto_java_ia.providers.JWTUserProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityUserFilter extends OncePerRequestFilter{
    private JWTUserProvider jwtUserProvider;
    @Autowired
    public SecurityUserFilter securityUserFilter(JWTUserProvider jwtUserProvider) {
        this.jwtUserProvider = jwtUserProvider;
                return null;
       
       
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            var decodeToken = jwtUserProvider.validateToken(token);

            if (decodeToken != null) {
                var emailUser = decodeToken.getClaim("email").asString();
                var idUser = UUID.fromString(decodeToken.getClaim("id").asString());
                var roleUser = decodeToken.getClaim("role").asString();
                
                if (roleUser == null) {
                    roleUser = "USER"; // Default role if not present in token
                }
               
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleUser.toUpperCase()));
            
                CustomUserDetails userDetails = new CustomUserDetails(idUser, emailUser, authorities);
            
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            
                SecurityContextHolder.getContext().setAuthentication(authentication);
            
                request.setAttribute("email", emailUser);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            
            
        }
        filterChain.doFilter(request, response);
    }

    
}
