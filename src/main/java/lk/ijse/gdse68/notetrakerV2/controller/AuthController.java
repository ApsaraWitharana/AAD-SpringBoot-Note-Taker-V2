package lk.ijse.gdse68.notetrakerV2.controller;

import lk.ijse.gdse68.notetrakerV2.dto.iml.UserDTO;
import lk.ijse.gdse68.notetrakerV2.exception.DataPersistFailedException;
import lk.ijse.gdse68.notetrakerV2.jwtModel.JwtAuthResponse;
import lk.ijse.gdse68.notetrakerV2.jwtModel.SignIn;
import lk.ijse.gdse68.notetrakerV2.service.AuthenticationService;
import lk.ijse.gdse68.notetrakerV2.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
//@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }


    //resolving
    @PostMapping(value = "signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JwtAuthResponse>signUn(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart ("profilePic") MultipartFile profilePic,
            @RequestPart ("Role") String role) {
        {
            try {

                String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic); // base64 widiyt convete krnwa eke string ek return krnwa
                UserDTO buildUserDTO = new UserDTO();
                buildUserDTO.setUserId(AppUtil.createUserId());
                buildUserDTO.setFirstName(firstName);
                buildUserDTO.setLastName(lastName);
                buildUserDTO.setEmail(email);
                buildUserDTO.setPassword(passwordEncoder.encode(password));
                buildUserDTO.setProfilePic(base64ProfilePic);
                buildUserDTO.setRole(role);
                authenticationService.signUp(buildUserDTO);
                return new ResponseEntity<>(HttpStatus.CONTINUE);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            } catch (Exception e) {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PostMapping(value = "signIn")
        public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn) {
            return ResponseEntity.ok(authenticationService.signIn(signIn));
        }

    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

}
