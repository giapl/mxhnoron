package com.example.mxhnoron.service;

import com.example.mxhnoron.data.enity.User;
import com.example.mxhnoron.data.request.UserRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {

  User createUser(UserRequest user);

  List<User> finAll();

  Optional<User> getId(Long id);

  Optional<User> getUsername (String username);

  User getIdAndUsername(String username, Long id);

  void deleteId(Long id);

  void deleteUsername(String username);

  User createUserAdmin(UserRequest user);

}
