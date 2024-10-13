package lk.ijse.gdse68.notetrakerV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public DefaultSecurityFilterChain securityFilterChange (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests().anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();

    }
    //is this manage user in memory
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        //password is not get plaintext  is this conversing this default password encode
       UserDetails principleUser = User.withDefaultPasswordEncoder()
                .username("Sachini")
               .password("sachi123")
                .roles("admin") //refund robin
               .build();
       return new InMemoryUserDetailsManager(principleUser);

    }


}
