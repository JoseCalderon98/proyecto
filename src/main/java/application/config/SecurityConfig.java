package application.config;

import application.config.security.CustomUserDetailsService;
import application.config.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("handlerExceptionResolver")
    private org.springframework.web.servlet.HandlerExceptionResolver resolver;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> {
                System.out.println("Configuring Security Rules...");
                auth.requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/users").hasAnyRole("INTERNAL_ANALYST", "ADMIN")
                    .requestMatchers("/api/clients/**").hasAnyRole("COMMERCIAL_EXECUTIVE", "INTERNAL_ANALYST", "ADMIN")
                    .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/accounts").hasAnyRole("COMMERCIAL_EXECUTIVE", "ENTERPRISE_EMPLOYEE", "ENTERPRISE_SUPERVISOR", "INTERNAL_ANALYST", "ADMIN")
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().authenticated();
            })
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        http.exceptionHandling(exceptions -> exceptions
            .authenticationEntryPoint((request, response, authException) -> {
                resolver.resolveException(request, response, null, authException);
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                resolver.resolveException(request, response, null, accessDeniedException);
            })
        );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
}
