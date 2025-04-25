package br.com.fillms.projeto_java_ia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityUserFilter securityUserFilter;

     
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests( auth ->{
                    auth.requestMatchers(HttpMethod.POST, "/user/create").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/user/update").authenticated()
                    .requestMatchers(HttpMethod.GET, "/user/profile").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/user/delete").authenticated()
                    .anyRequest().authenticated();

            })
            .addFilterBefore(securityUserFilter, BasicAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
