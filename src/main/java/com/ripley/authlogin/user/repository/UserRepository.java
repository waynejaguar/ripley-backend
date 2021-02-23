package com.ripley.authlogin.user.repository;


import com.ripley.authlogin.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u where u.username = :username and u.password =:password")
  Optional<User> findByUserName(@Param("username") String username, @Param("password") String password);


  @Query("select u from User u where u.username = :username")
  Optional<User> findOneByUserName(@Param("username") String username);


}
