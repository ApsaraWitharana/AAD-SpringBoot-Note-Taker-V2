package lk.ijse.gdse68.notetrakerV2.service;

import lk.ijse.gdse68.notetrakerV2.dao.UserDAO;
import lk.ijse.gdse68.notetrakerV2.dto.iml.UserDTO;
import lk.ijse.gdse68.notetrakerV2.jwtModel.JwtAuthResponse;
import lk.ijse.gdse68.notetrakerV2.jwtModel.SignIn;
import lk.ijse.gdse68.notetrakerV2.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {
    private final UserDAO userDao;
    private final JWTService jwtService;
    private final Mapping mapping;
    //utils
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JwtAuthResponse.builder().token(generatedToken).build() ;    }


    @Override
    public JwtAuthResponse signUp(UserDTO signUpUser) {
        var savedUser = userDao.save(mapping.convertToUserEntity(signUpUser));
        var genToken = jwtService.generateToken(savedUser);
        return JwtAuthResponse.builder().token(genToken).build();
    }

    @Override
    public JwtAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUsername(accessToken);
        var userEntity =
                userDao.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JwtAuthResponse.builder().token(refreshToken).build();
    }
}
