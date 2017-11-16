package com.example.mtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class MtApplication {

  public static void main(final String[] args) {
    SpringApplication.run(MtApplication.class, args);
  }
}
