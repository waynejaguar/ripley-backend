package com.ripley.authlogin.core.pg;


import com.zaxxer.hikari.HikariDataSource;
import java.nio.file.Paths;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "ripley", name = "postgres.enable", havingValue = "true")
@EnableConfigurationProperties(PostgresSQLContainerProperties.class)
public class PostgresSQLContainerConfig {

  public static final String PG_VERSION = "postgres:11-alpine";

  private final PostgresSQLContainerProperties properties;

  private final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(PG_VERSION)
      .withDatabaseName("ripley-development-db")
      .withUsername("sa")
      .withPassword("sa");

  public PostgresSQLContainerConfig(final PostgresSQLContainerProperties properties) {
    this.properties = properties;
  }

  @Bean
  public DataSource getPostgresDataSource() {
    if (this.properties.isPersistent()) {
      final String path = Paths.get(".", "target", "pg", "data")
          .toAbsolutePath().normalize().toUri().getPath();
      this.postgreSQLContainer
          .withFileSystemBind(path, "/var/lib/postgresql/data")
          .withCommand("postgres");
    }

    final long waitTimeout = this.properties.getWaitTimeout();
    this.postgreSQLContainer.setWaitStrategy(new JdbcConnectionWaitStrategy(waitTimeout));
    this.postgreSQLContainer.start();

    return new HikariDataSource() {{
      this.setJdbcUrl(PostgresSQLContainerConfig.this.postgreSQLContainer.getJdbcUrl());
      this.setUsername(PostgresSQLContainerConfig.this.postgreSQLContainer.getUsername());
      this.setPassword(PostgresSQLContainerConfig.this.postgreSQLContainer.getPassword());
    }};
  }

}
