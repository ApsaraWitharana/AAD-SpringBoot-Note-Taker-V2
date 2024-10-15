package lk.ijse.gdse68.notetrakerV2.config;

import lk.ijse.gdse68.notetrakerV2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JWTConfig jwtConfigFilter;
    //Config filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http.csrf(AbstractHttpConfigurer::disable)
             .authorizeRequests(req->
             req.requestMatchers("api/v1/auth/**").permitAll() //token ekk blnne ntuw security eken gnnw//while card mapping
                     .anyRequest().authenticated() // uda tiyen ewa ar en one ekk permite krnn ona
             )
             .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .authenticationProvider(authenticationProvider())
             .addFilterBefore(jwtConfigFilter, UsernamePasswordAuthenticationFilter.class); // passing the filter type
     //restfull -section ex
        return http.build();
}
    //Password encoder
    @Bean
     public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //decrypt password encode
     }
//  //AuthenticationManager
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider(); // user email base krl db eken eliyt gnnw
        dap.setUserDetailsService(userService.userDetailsService()); //db eken user wa gnna
        dap.setPasswordEncoder(passwordEncoder()); //
        return dap;
    }
//Auth Manager - authenticate object manage krnwa
    @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
   }
}
