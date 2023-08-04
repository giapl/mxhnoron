package com.example.mxhnoron.contronller;

import com.example.mxhnoron.data.request.UserRequest;
import com.example.mxhnoron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterContronller {

  private  final UserService userService;

  @Autowired
  public RegisterContronller(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> createUser( @RequestBody UserRequest user) {
    userService.createUser(user);
    return ResponseEntity.ok("dang ki Thanh cong");
  }
}
