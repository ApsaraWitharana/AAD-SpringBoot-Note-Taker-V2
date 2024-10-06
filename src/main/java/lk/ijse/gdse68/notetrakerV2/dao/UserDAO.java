package lk.ijse.gdse68.notetrakerV2.dao;


import lk.ijse.gdse68.notetrakerV2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity,String> {
   UserEntity getUserEntityByUserId(String userId);//data jpa wlin method ek coll krnw


}
