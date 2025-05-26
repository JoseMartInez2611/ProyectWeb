package co.edu.udes.backend.config;

import co.edu.udes.backend.services.ProfileUDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authenticationProvider, JwtRequestFilter jwtRequestFilter) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
                    configurePrivateEndpoints(auth);
                    auth.anyRequest().authenticated();
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(ProfileUDetailServiceImpl profileUDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(profileUDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void configurePrivateEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry http)  {
        //Endpoint Privados

        //GET
        http.requestMatchers(HttpMethod.GET, "/api/v1/absence-justifications/**").hasAnyRole("STUDENT", "ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/academic-records/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.GET, "/api/v1/academic-registrations/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.GET, "/api/v1/academic-resource/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "EMPLOYEE");
        http.requestMatchers(HttpMethod.GET, "/api/v1/activity/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT");
        http.requestMatchers(HttpMethod.GET, "/api/v1/answer-document/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/attendance/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT");
        http.requestMatchers(HttpMethod.GET, "/api/v1/borrow/**").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/career/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "EMPLOYEE");
        http.requestMatchers(HttpMethod.GET, "/api/v1/courses/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/employee/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.GET, "/api/v1/exam/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/groups/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/lessons/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/message/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "EMPLOYEE");
        http.requestMatchers(HttpMethod.GET, "/api/v1/notification/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "EMPLOYEE");
        http.requestMatchers(HttpMethod.GET, "/api/v1/qualifications/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/question/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/report/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "EMPLOYEE");
        http.requestMatchers(HttpMethod.GET, "/api/v1/room/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "EMPLOYEE");
        http.requestMatchers(HttpMethod.GET, "/api/v1/schedules/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/student/**").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/student/{id}/schedule").hasAnyRole("ADMIN", "STUDENT");
        http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/**").hasAnyRole("ADMIN", "EMPLOYEE", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/{teacherId}/evaluations").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/{teacherId}/schedule").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/{teacherId}/groups").hasAnyRole("ADMIN", "TEACHER");


        //POST
        http.requestMatchers(HttpMethod.POST, "/api/v1/absence-justifications/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/academic-registrations/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/academic-resource/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/activity/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/answer-document/**").hasAnyRole("ADMIN", "STUDENT");
        http.requestMatchers(HttpMethod.POST, "/api/v1/attendance/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/borrow/**").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/career/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/courses/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/employee/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/exam/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/groups/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/lessons/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/message/**").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/notification/**").hasAnyRole("ADMIN", "EMPLOYEE", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/qualification/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/question/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/report/**").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.POST, "/api/v1/room/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/schedules/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/student/**").hasRole("ADMIN");
        http.requestMatchers(HttpMethod.POST, "/api/v1/teachers/**").hasRole("ADMIN");

        //PUT
        http.requestMatchers(HttpMethod.PUT, "/api/v1/absence-justifications/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/academic-registrations/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/academic-resource/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/activity/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/answer-document/**").hasAnyRole("ADMIN", "STUDENT");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/attendance/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/borrow/**").hasAnyRole("ADMIN", "EMPLOYEE", "TEACHER");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/career/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/courses/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/employee/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/exam/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/groups/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/lessons/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/qualification/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/question/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/room/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/schedules/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/student/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.PUT, "/api/v1/teachers/**").hasAnyRole("ADMIN");

        //DELETE
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/absence-justifications/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/academic-registrations/**").hasAnyRole("STUDENT", "ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/academic-resource/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/activity/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/answer-document/**").hasAnyRole("ADMIN", "STUDENT");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/attendance/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/borrow/**").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/career/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/courses/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/employee/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/exam/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/groups/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/lessons/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/question/**").hasAnyRole("ADMIN", "TEACHER");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/room/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/schedules/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/student/**").hasAnyRole("ADMIN");
        http.requestMatchers(HttpMethod.DELETE, "/api/v1/teachers/**").hasAnyRole("ADMIN");

    }
}
