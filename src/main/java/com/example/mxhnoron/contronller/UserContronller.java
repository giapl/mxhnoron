package com.example.mxhnoron.contronller;

import com.example.mxhnoron.data.request.UserRequest;
import com.example.mxhnoron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserContronller {

  private  final UserService userService;

  @Autowired
  public UserContronller(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/createUserAdmin")
  public ResponseEntity<?> createUserAdmin(@RequestBody UserRequest user){
    return ResponseEntity.ok(userService.createUserAdmin(user));
  }

  @GetMapping("/all")
  public ResponseEntity<?> finAll() {
    return ResponseEntity.ok(userService.finAll());
  }

  @GetMapping("/id")
  public ResponseEntity<?> getId(@RequestParam Long id) {
    try {
      return ResponseEntity.ok(userService.getId(id));
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no id");
    }
  }

  @GetMapping("/username")
  public ResponseEntity<?> getUsername(@RequestParam String username) {
    try {
      return ResponseEntity.ok(userService.getUsername(username));
    }catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no username");
    }
  }

  @GetMapping("/usernameAndId")
  public ResponseEntity<?> getIdAndUsername(@RequestParam String username , Long id) {
    return ResponseEntity.ok(userService.getIdAndUsername(username , id));
  }
  @DeleteMapping("/deleteId")
  public ResponseEntity<?> deleteId(@RequestParam Long id){
    userService.deleteId(id);
    return ResponseEntity.ok("BAN DA XOA THANH CONG ACCOUNT CO ID :"+ id);
  }
  @DeleteMapping("/deleteUsername")
  public ResponseEntity<?> deleteUsername(@RequestParam String username) {
    userService.deleteUsername(username);
    return ResponseEntity.ok("BAN DA XOA ACCOUNT THANH CONG CO TEN :"+username);
  }
}
