package com.ripley.authlogin.testing;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class DatabaseCleaner {

  @Autowired
  private Flyway flyway;

  @AfterEach
  @BeforeAll
  public void clean() {
    this.flyway.clean();
    this.flyway.migrate();
  }

}

