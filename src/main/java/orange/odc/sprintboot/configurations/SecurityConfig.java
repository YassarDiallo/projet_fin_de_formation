package orange.odc.sprintboot.configurations;

import orange.odc.sprintboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    private UserService userService;

    private AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain configure(HttpSecurity  http) throws Exception{
        return http.
                authorizeHttpRequests(
                        r -> r.requestMatchers("/connexion","/css/**", "/js/**","/images/**").permitAll()
                                .requestMatchers("/Admin/").hasRole("Admin")
                                .anyRequest().
                                authenticated()

                ).formLogin(f-> {
                    f.loginPage("/connexion")
                            .successForwardUrl("/accueil");

                }).logout(f->{
                    f.logoutSuccessUrl("/connexion")
                            .permitAll();
                })
                .authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Lazy
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Lazy
    @Autowired
    public void setAuthenticationManger(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
