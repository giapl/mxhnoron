package com.example.mxhnoron.service;

import com.example.mxhnoron.data.enity.User;
import com.example.mxhnoron.data.request.UserRequest;
import com.example.mxhnoron.repository.IUserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private IUserRepository iUserRepository;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(IUserRepository iUserRepository, PasswordEncoder passwordEncoder) {
    this.iUserRepository = iUserRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @SneakyThrows
  @Override
  public User createUser(UserRequest user) {
    User user1 = new User();
    if (iUserRepository.existsByEmailAndUsername(user.getUsername(), user.getEmail())) {
      throw new RuntimeException("user and email da ton tai");
    }
    user1.setEmail(user.getEmail());
    user1.setUsername(user.getUsername());
    user1.setPassword(passwordEncoder.encode(user.getPassword()));
    user1.setRole("USER");
    user1.setDate(new Date());
    return iUserRepository.save(user1);
  }

  @Override
  public List<User> finAll() {
    return iUserRepository.findAll();
  }

  @Override
  public Optional<User> getId(Long id) {
    return iUserRepository.findById(id);
  }

  @Override
  public Optional<User> getUsername(String username) {
    return iUserRepository.findByUsername(username);
  }

  @Override
  public User getIdAndUsername(String username, Long id) {
    return iUserRepository.findByIdAndUsername(id, username);
  }

  @Override
  public void deleteId(Long id) {
    iUserRepository.deleteById(id);
  }

  @Override
  public void deleteUsername(String username) {
    iUserRepository.deleteByUsername(username);
  }

  @SneakyThrows
  @Override
  public User createUserAdmin(UserRequest user) {
    User user1 = new User();
    if (iUserRepository.existsByEmailAndUsername(user.getUsername(), user.getEmail())) {
      throw new RuntimeException("email and username da ton tai");
    }
    user1.setEmail(user.getEmail());
    user1.setUsername(user.getUsername());
    user1.setPassword(passwordEncoder.encode(user.getPassword()));
    user1.setRole(user.getRole());
    user1.setDate(new Date());
    return iUserRepository.save(user1);
  }
}
