package com.nlu.mainguyen.travelserviceapi.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.nlu.mainguyen.travelserviceapi.jwt.filter.JwtAuthenticationFilter;
import com.nlu.mainguyen.travelserviceapi.jwt.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final UserDetailsServiceImp userDetailsServiceImp;// cấp 1: kiểm tra về thông tin user

        private final JwtAuthenticationFilter jwtAuthenticationFilter;// cấp 2: kiểm tra token

        private final CustomLogoutHandler logoutHandler; // tương đương controler xử lí về API logout

        public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp,
                        JwtAuthenticationFilter jwtAuthenticationFilter,
                        CustomLogoutHandler logoutHandler) {
                this.userDetailsServiceImp = userDetailsServiceImp;
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
                this.logoutHandler = logoutHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                return http.csrf(AbstractHttpConfigurer::disable)

                                .authorizeHttpRequests(request -> request
                                                .requestMatchers(new AntPathRequestMatcher("/private/**"))
                                                .authenticated())

                                .httpBasic(Customizer.withDefaults())

                                .authorizeHttpRequests(request -> request
                                                .requestMatchers("/public/**")
                                                .permitAll())

                                .authorizeHttpRequests(request -> request
                                                .requestMatchers("/auth/login",
                                                                "/auth/register",
                                                                "/auth/refresh_token",
                                                                "/auth/resetPassword",
                                                                "/auth/forgotPassword")
                                                .anonymous())

                                .userDetailsService(userDetailsServiceImp)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .exceptionHandling(
                                                e -> e.accessDeniedHandler(
                                                                (request, response, accessDeniedException) -> response
                                                                                .setStatus(403))
                                                                .authenticationEntryPoint(new HttpStatusEntryPoint(
                                                                                HttpStatus.UNAUTHORIZED)))
                                .logout(l -> l
                                                .logoutUrl("/auth/logout")
                                                .addLogoutHandler(logoutHandler)
                                                .logoutSuccessHandler((request, response,
                                                                authentication) -> SecurityContextHolder
                                                                                .clearContext()))
                                .build();

        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }

}
