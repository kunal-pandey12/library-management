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

                        //  Auth — sabko allow(Register ya singup public koi bhi kr sakta hai )
                        .requestMatchers("/auth/**").permitAll()

                        // Public endpoints (No authentication required)
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/login"
                        ).permitAll()

                        //  Author Controller — /Author/**
                        .requestMatchers(HttpMethod.GET, "/Author/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/Author/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Author/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Author/**")
                        .hasRole("ADMIN")

                        //  Book Controller — /books/**
                        .requestMatchers(HttpMethod.GET, "/books/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/books/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**")
                        .hasRole("ADMIN")

                        //  Issue Controller — /issue/**
                        .requestMatchers(HttpMethod.GET, "/issue/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/issue/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/issue/**")
                        .authenticated()

                        //  User Controller — /User/**
                        // Sirf ADMIN dekh sakta hai users
                        .requestMatchers(HttpMethod.GET, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/User/**")
                        .hasRole("ADMIN")

                        // Baaki sab authenticated
                        .anyRequest().authenticated()
                )
                //(httpBasic)>>“Basic username-password login system enable kar raha hai”
               // .httpBasic(basic ->
                 //       basic.realmName("Library Management System"));

                //this is for fronted
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll());
            return http.build(); //“http.build() configured security rules ka final SecurityFilterChain object return karta hai.
    }                              // jo bhi sab upper bana hai  “Theek hai, ab jo security settings banayi hain unko apply/finalize kar do”

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}