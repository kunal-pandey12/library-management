package library.management.books.securityconfig;
import library.management.books.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Auth — sabko allow (Register/Signup public hai)
                        .requestMatchers("/auth/**").permitAll()

                        // Public endpoints (Login page, CSS, JS)
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/login"
                        ).permitAll()

                        // Author REST Controller — /Author/**
                        // REST API ke liye (AuthorController.java)
                        .requestMatchers(HttpMethod.GET, "/Author/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/Author/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Author/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Author/**")
                        .hasRole("ADMIN")

                        // Author View Controller — /authors/**
                        // Thymeleaf pages ke liye (AuthorViewController.java)
                        .requestMatchers(HttpMethod.GET, "/authors/search")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/authors/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/authors/**")
                        .hasRole("ADMIN")

                        // Book search by author — user and admin
                        .requestMatchers(HttpMethod.GET, "/books/search/author/page").authenticated()
                        .requestMatchers(HttpMethod.GET, "/books/search/page").authenticated()

                        // Book search — Sirf ADMIN
                        .requestMatchers(HttpMethod.GET, "/books/search/admin/**").hasRole("ADMIN")

                        // Book Controller — /books/**
                        .requestMatchers(HttpMethod.GET, "/books/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/books/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**")
                        .hasRole("ADMIN")

                        // Issue Controller — /issue/**
                        .requestMatchers(HttpMethod.GET, "/issue/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/issue/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/issue/**")
                        .authenticated()

                        // User Controller — /User/**
                        // Sirf ADMIN dekh sakta hai users
                        .requestMatchers(HttpMethod.GET, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/User/**")
                        .hasRole("ADMIN")


                        // User View Controller — /users/**
                        // Thymeleaf pages ke liye (UserViewController.java)
                        .requestMatchers(HttpMethod.GET, "/users/search").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")


                        // Baaki sab authenticated
                        .anyRequest().authenticated()
                )
                // Frontend ke liye form login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}