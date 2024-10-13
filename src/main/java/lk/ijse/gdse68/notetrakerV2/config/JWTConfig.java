package lk.ijse.gdse68.notetrakerV2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.notetrakerV2.service.JWTService;
import lk.ijse.gdse68.notetrakerV2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@RequiredArgsConstructor
public class JWTConfig extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String initToken = request.getHeader("Authorization");
        String userEmail;
        String jwtToken;

     //Initialized Token
        if (StringUtils.isEmpty(initToken)|| !initToken.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        //Token received
        jwtToken= initToken.substring(7); //Bearer+space break
        userEmail  =jwtService.extractUsername(jwtToken);

        //Validation
        if (StringUtils.isNotEmpty(userEmail) &&
        SecurityContextHolder.getContext().getAuthentication() == null){ //check user email is empty
            //Lording user details base on the email
          var lordUser =   userService.userDetailsService().loadUserByUsername(userEmail); // user data set
          if (jwtService.isTokenValid(jwtToken,lordUser)){
           SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
              UsernamePasswordAuthenticationToken newContext = new UsernamePasswordAuthenticationToken(lordUser, null, lordUser.getAuthorities());
          }
        }
    }

}

//filer - awasan ekt eha tiyenne servlet ekk