package com.dm.ecommerce.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuario/cadastro", "/usuario/login", "/pedido/view/{id}", "/pedido/cadastro", "/produto/view", "/produto/view/{id}", "/pagamento/cadastro").permitAll()
                .requestMatchers("/usuario/view", "/usuario/delete", "/pedido/view/", "/pedido/delete", "/produto/cadastro", "/produto/{id}", "/produto/delete", "/pagamento/view").hasRole("ADMIN").anyRequest().authenticated()).httpBasic(withDefaults()


        );


        return httpSecurity.build();
    }
}
