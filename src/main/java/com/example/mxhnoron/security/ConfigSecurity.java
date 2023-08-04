package com.example.mxhnoron.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity {


  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    PasswordEncoder passwordEncoder = passwordEncoder();
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password(passwordEncoder.encode("123"))
        .roles("USER")
        .build();
    UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")
        .password(passwordEncoder.encode("123"))
        .roles("ADMIN")
        .build();
    manager.createUser(user);
    manager.createUser(admin);
    return manager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(HttpMethod.POST, "/register").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/all").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/id").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/username").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/usernameAndId").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/delete").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/deleteUsername").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/createUserAdmin").hasRole("ADMIN")
            .anyRequest()
            .denyAll())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

}
