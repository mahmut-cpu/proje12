package com.example.iste.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin-dashboard").hasRole("ADMIN")
                        .requestMatchers("/user-dashboard").hasRole("USER")
                        .requestMatchers("/login-user","/admin-dashboard","/user-dashboard", "/login-admin", "/signup", "/public").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login-user")
                        .defaultSuccessUrl("/user-dashboard", true)
                        .usernameParameter("email") // Kullanıcı adı olarak email kullanılıyor
                        .passwordParameter("password") // Şifre alanı
                        .failureUrl("/login-user?error=true")
                        .permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login-admin")
                        .usernameParameter("email") // Kullanıcı adı olarak email kullanılıyor
                        .passwordParameter("password") // Şifre alanı
                        .defaultSuccessUrl("/admin-dashboard", true)
                        .failureUrl("/login-admin?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );
        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
