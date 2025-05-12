package ntu.fit.dinhnhatbao_thicuoiky_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests()
        .requestMatchers("/login", "/register", "/home", "/index", "/logout", "/").permitAll()
        .anyRequest().permitAll()
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .rememberMe()
          .key("uniqueAndSecret")
          .tokenValiditySeconds(86400);
    return http.build();
  }
}