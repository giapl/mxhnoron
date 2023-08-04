package com.example.mxhnoron.service;

import com.example.mxhnoron.data.enity.UserSecurity;
import com.example.mxhnoron.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail implements UserDetailsService {

  private final IUserRepository iUserRepository;

  @Autowired
  public UserServiceDetail(IUserRepository iUserRepository) {
    this.iUserRepository = iUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return iUserRepository
        .findByUsername(username)
        .map(UserSecurity::new)
        .orElseThrow(()-> new RuntimeException("no username database"));
  }
}
