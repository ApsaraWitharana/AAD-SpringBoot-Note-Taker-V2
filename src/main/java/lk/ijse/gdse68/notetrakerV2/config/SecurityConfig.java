package lk.ijse.gdse68.notetrakerV2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http.csrf(AbstractHttpConfigurer::disable)
             .authorizeRequests(req->
             req.requestMatchers("api/v1/auth/**").permitAll() //token ekk blnne ntuw security eken gnnw//while card mapping
                     .anyRequest().authenticated() // uda tiyen ewa ar en one ekk permite krnn ona
             )
             .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .authenticationProvider()
             .addFilterBefore();
     //restfull -section ex
        return http.build();
}
}
