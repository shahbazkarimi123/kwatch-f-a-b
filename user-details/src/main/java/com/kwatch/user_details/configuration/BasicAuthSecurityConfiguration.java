package com.kwatch.user_details.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.cors(withDefaults())
        .authorizeHttpRequests(
            auth -> {
                // auth.anyRequest().authenticated();
                auth.requestMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/users").hasAnyRole("USER","ADMIN")
                    .requestMatchers(HttpMethod.POST,"/users/login").permitAll()
                    .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                    .anyRequest().authenticated();
                    

            }
        );
        
        http.sessionManagement(
            session-> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        );
        http.httpBasic(withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(
            "SELECT email AS username, password, enabled FROM user WHERE email = ?"
        );
        userDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT email AS username, roles FROM user WHERE email = ?"
        );
        return userDetailsManager;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                    .allowedOrigins("http://192.168.1.13:4200")
                    .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
    
}
