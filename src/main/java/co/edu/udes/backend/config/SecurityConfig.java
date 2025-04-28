package co.edu.udes.backend.config;

import co.edu.udes.backend.services.ProfileUDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf-> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http->{





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
                    http.requestMatchers(HttpMethod.GET, "/api/v1/schedule/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/student/**").hasAnyRole("ADMIN", "EMPLOYEE");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/student/{id}/schedule").hasAnyRole("ADMIN", "STUDENT");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/**").hasAnyRole("ADMIN", "EMPLOYEE");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/{teacherId}/evaluations").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/teachers/{teacherId}/lessons-formatted-info").hasAnyRole("ADMIN", "TEACHER");


                    //POST
                    http.requestMatchers(HttpMethod.POST, "/api/v1/absence-justifications").hasAnyRole("STUDENT", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/academic-registrations").hasAnyRole("STUDENT", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/academic-resource").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/activity").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/answer-document").hasAnyRole("ADMIN", "STUDENT");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/attendance").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/borrow").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/career").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/courses").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/employee").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/exam").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/groups").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/lessons").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/message").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/notification").hasAnyRole("ADMIN", "EMPLOYEE", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/qualification").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/question").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/report").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/room").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/schedules").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/student").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/teachers").hasAnyRole("ADMIN");

                    //PUT
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/absence-justifications").hasAnyRole("STUDENT", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/academic-registrations").hasAnyRole("STUDENT", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/academic-resource").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/activity").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/answer-document").hasAnyRole("ADMIN", "STUDENT");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/attendance").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/borrow").hasAnyRole("ADMIN", "EMPLOYEE", "TEACHER");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/career").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/courses").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/employee").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/exam").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/groups").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/lessons").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/qualification").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/question").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/room").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/schedules").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/student").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/teachers").hasAnyRole("ADMIN");

                    //DELETE
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/absence-justifications").hasAnyRole("STUDENT", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/academic-registrations").hasAnyRole("STUDENT", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/academic-resource").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/activity").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/answer-document").hasAnyRole("ADMIN", "STUDENT");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/attendance").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/borrow").hasAnyRole("ADMIN", "EMPLOYEE", "STUDENT", "TEACHER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/career").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/courses").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/employee").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/exam").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/groups").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/lessons").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/question").hasAnyRole("ADMIN", "TEACHER");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/room").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/schedules").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/student").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/teachers").hasAnyRole("ADMIN");

                })

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(ProfileUDetailServiceImpl profileUDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(profileUDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    //Cambiar a BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
