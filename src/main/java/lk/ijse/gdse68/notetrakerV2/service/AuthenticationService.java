package lk.ijse.gdse68.notetrakerV2.service;

import lk.ijse.gdse68.notetrakerV2.dto.iml.UserDTO;
import lk.ijse.gdse68.notetrakerV2.jwtModel.JwtAuthResponse;
import lk.ijse.gdse68.notetrakerV2.jwtModel.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
