package com.example.backend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity htp) throws Exception {

        htp.csrf(Customizer->Customizer.disable());
//        htp.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        htp.authorizeHttpRequests(request->request
                .requestMatchers("/login")
                .permitAll()
                .anyRequest().authenticated());


//        htp.formLogin(Customizer.withDefaults());
        htp.httpBasic(Customizer.withDefaults());
        htp.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        htp.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return htp.build();
    }

//    @Bean
//    public UserDetailsService userDetails() {
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("Maran")
//                .password("Maran#107")
//                .roles("User")
//                .build();
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("Elann")
//                .password("Maran#107")
//                .roles("Admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//
//    }

    @Autowired
    private  UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}