package com.ripley.authlogin.core.pg;


import java.sql.DriverManager;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;

@Slf4j
@AllArgsConstructor
public class JdbcConnectionWaitStrategy extends AbstractWaitStrategy {

  private final long waitTimeout;

  @Override
  @SneakyThrows
  protected void waitUntilReady() {
    final JdbcDatabaseContainer container = (JdbcDatabaseContainer) this.waitStrategyTarget;
    final long expiry = System.currentTimeMillis() + this.waitTimeout * 1000;

    while (System.currentTimeMillis() < expiry) {
      try {
        DriverManager.getConnection(
            container.getJdbcUrl(), container.getUsername(), container.getPassword()
        ).close();
        return;
      } catch (final Exception e) {
        log.trace("Failed to connect to {}. Retrying...", container.getJdbcUrl());
      }
      Thread.sleep(1000);
    }

    throw new RuntimeException("The container took too long to start. Aborting...");
  }
}
