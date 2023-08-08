package com.example.mxhnoron.security;


import com.example.mxhnoron.service.UserServiceDetail;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity {

  private final UserServiceDetail userServiceDetail;

  public ConfigSecurity(UserServiceDetail userServiceDetail) {
    this.userServiceDetail = userServiceDetail;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(new AntPathRequestMatcher("/api/register", HttpMethod.POST.name()))
            .permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/all", HttpMethod.GET.name()))
            .hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/api/id", HttpMethod.GET.name()))
            .hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/api/username", HttpMethod.GET.name()))
            .hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/api/usernameAndId", HttpMethod.GET.name()))
            .hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/api/delete", HttpMethod.DELETE.name()))
            .hasRole("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/deleteUsername", HttpMethod.DELETE.name()))
            .hasRole("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/createUserAdmin", HttpMethod.GET.name()))
            .hasRole("ADMIN")
            .anyRequest()
            .denyAll())
        .userDetailsService(userServiceDetail)
        .headers(headers -> headers.frameOptions().sameOrigin())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

}
