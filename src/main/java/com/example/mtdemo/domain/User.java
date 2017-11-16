package com.example.mtdemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String userName;

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(final String userName) {
    this.userName = userName;
  }


}
