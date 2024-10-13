package lk.ijse.gdse68.notetrakerV2.service;



import lk.ijse.gdse68.notetrakerV2.customObj.UserResponse;
import lk.ijse.gdse68.notetrakerV2.dto.iml.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(String userId);
    UserResponse getSelectedUser(String usrId);
    List<UserDTO> getAllUsers();
    UserDetailsService userDetailsService();

}
