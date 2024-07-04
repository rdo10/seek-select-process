package com.seek.selection_api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @Column(nullable = false, length = 20)
  private String username;

  @Column(nullable = false, length = 200)
  private String password;

  @Column(length = 50)
  private String email;

  @Column(nullable = false, columnDefinition = "TINYINT")
  private Boolean locked;

  @Column(nullable = false, columnDefinition = "TINYINT")
  private Boolean disabled;
}