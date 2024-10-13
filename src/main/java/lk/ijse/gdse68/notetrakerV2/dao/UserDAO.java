package lk.ijse.gdse68.notetrakerV2.dao;


import lk.ijse.gdse68.notetrakerV2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserEntity,String> {
   UserEntity getUserEntityByUserId(String userId);//data jpa wlin method ek coll krnw
   Optional<UserEntity> findByEmail(String email);
   //JPQL -RND - nati ekk set krgnn


}
