package com.ripley.authlogin.core.pg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ripley.postgres")
public class PostgresSQLContainerProperties {

  private boolean enabled;
  private boolean persistent;
  private long waitTimeout;

}
