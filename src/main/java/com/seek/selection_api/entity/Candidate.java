package com.seek.selection_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "candidates")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String gender;
  private BigDecimal salaryExpected;

}