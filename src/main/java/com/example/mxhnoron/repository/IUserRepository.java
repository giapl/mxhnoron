package com.example.mxhnoron.repository;

import com.example.mxhnoron.data.enity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  @Query(value = "select *from account.user", nativeQuery = true)
  List<User> findAll();
  @Query(value = "select * from account.user where id =: id",nativeQuery = true)
  Optional<User> findById(@Param("id") Long id);
  @Query(value = "select * from account.user where username = :username",nativeQuery = true)
  Optional<User> findByUsername(@Param("username") String username);
  @Query(value = "select * from account.user where id and username = :id and :username",nativeQuery = true)
  User findByIdAndUsername(@Param("id") Long id , @Param("username") String username);
  @Query(value = "DELETE from account.user where username =: username",nativeQuery = true)
  User deleteByUsername(@Param("username") String username);
  boolean existsByEmailAndUsername(@Param("email") String email, @Param("username") String username);

}
