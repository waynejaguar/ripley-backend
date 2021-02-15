package com.ripley.authlogin.testing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ContextConfiguration(initializers = {PostgresTest.Initializer.class})
public @interface PostgresTest {

  PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11-alpine")
      .withDatabaseName("ripley-test-db")
      .withUsername("sa")
      .withPassword("sa");

  class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(final ConfigurableApplicationContext context) {
      if (System.getenv().containsKey("DISABLE_TEST_CONTAINERS")) {
        return;
      }

      postgreSQLContainer.start();

      TestPropertyValues.of(
          "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
          "spring.datasource.username=" + postgreSQLContainer.getUsername(),
          "spring.datasource.password=" + postgreSQLContainer.getPassword()
      ).applyTo(context.getEnvironment());
    }
  }
}
