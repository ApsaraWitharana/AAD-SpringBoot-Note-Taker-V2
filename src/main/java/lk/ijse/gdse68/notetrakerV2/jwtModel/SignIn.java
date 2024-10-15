package lk.ijse.gdse68.notetrakerV2.jwtModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder //working to builder design pattern
public class SignIn {
    private String email;
    private String password;

}
