package com.example.myins.configuration;

import com.example.myins.configuration.jwt.JwtAuthenticationEntryPoint;
import com.example.myins.configuration.jwt.JwtRequestFilter;
import com.example.myins.model.enums.UserRole;
import com.example.myins.repository.UserRepo;
import com.example.myins.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfiguration {
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    public SecurityConfiguration(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtRequestFilter jwtRequestFilter) {
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtRequestFilter = jwtRequestFilter;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // defines which pages will be authorized
                .authorizeHttpRequests()
                // allow access to all static files (images, CSS, js)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // the URL-s below are available for all users - logged in and anonymous
                .requestMatchers("/", "/users/login", "/users/login/register", "/users/login-error", "/myCar", "/myHome", "/myHome/offer", "/lifeAndHealth", "/travel").permitAll()
                // only for moderators
                .requestMatchers("/client-page").hasRole(UserRole.CLIENT.name())
                // only for admins
                .requestMatchers("/client-page/admin-page").hasRole(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                // configure login with HTML form
                .formLogin().loginPage("/users/login")
                // the names of the username, password input fields in the custom login form
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // where do we go after login
                .defaultSuccessUrl("/client-page")
                //use true argument if you always want to go there, otherwise go to previous page
                .failureForwardUrl("/users/login-error").and().logout()
                //configure logout
                .logoutUrl("/client-page/logout").logoutSuccessUrl("/");
                //go to homepage after logout
//                .invalidateHttpSession(true).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo) {
        return new ApplicationUserDetailsService(userRepo);
    }

}
