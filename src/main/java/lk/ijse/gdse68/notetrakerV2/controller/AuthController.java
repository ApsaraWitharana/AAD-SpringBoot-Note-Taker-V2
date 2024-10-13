package lk.ijse.gdse68.notetrakerV2.controller;

import lk.ijse.gdse68.notetrakerV2.dto.iml.UserDTO;
import lk.ijse.gdse68.notetrakerV2.exception.DataPersistFailedException;
import lk.ijse.gdse68.notetrakerV2.jwtModel.JWTResponse;
import lk.ijse.gdse68.notetrakerV2.jwtModel.SignIn;
import lk.ijse.gdse68.notetrakerV2.service.UserService;
import lk.ijse.gdse68.notetrakerV2.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    //resolving
    @PostMapping(value = "signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTResponse>signUn(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic) {
        try {

            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic); // base64 widiyt convete krnwa eke string ek return krnwa
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setProfilePic(base64ProfilePic);
            userService.saveUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.CONTINUE);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signIn")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn){ //ob ekt serialize krnw
        return null;
    }

}
