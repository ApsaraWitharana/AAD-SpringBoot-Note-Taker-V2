package lk.ijse.gdse68.notetrakerV2.controller;

import lk.ijse.gdse68.notetrakerV2.jwtModel.JWTResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    //resolving
    @PostMapping(value = "signUp")
    public ResponseEntity<JWTResponse>signUn(){
        return null;
    }
    @PostMapping(value = "signIn")
    public ResponseEntity<JWTResponse> signIn(){
        return null;
    }

}
