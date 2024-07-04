package com.seek.selection_api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "candidates", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(unique = true)
  private String email;
  private String gender;
  private BigDecimal salaryExpected;

}