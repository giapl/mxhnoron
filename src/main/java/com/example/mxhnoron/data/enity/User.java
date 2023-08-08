package com.example.mxhnoron.data.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "email")
  private String email;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "role")
  private String role;
  @Column(name = "date")
  @CreationTimestamp
  private Date date;

}
