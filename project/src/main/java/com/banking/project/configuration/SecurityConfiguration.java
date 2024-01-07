package com.banking.project.configuration;

import com.banking.project.jwt.JwtTokenFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.banking.project.constant.SecurityAuthList.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_LIST).permitAll()
                        .requestMatchers(HttpMethod.GET, ADMIN_LIST).hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/user/create-admin").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(USER_LIST).hasAnyAuthority("USER")
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutSuccessUrl(LOGOUT_URL)
                        .logoutSuccessHandler(((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))));

        return httpSecurity.build();
    }
}
