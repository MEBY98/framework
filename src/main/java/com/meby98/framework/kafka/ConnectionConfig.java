package com.meby98.framework.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Component
public class ConnectionConfig {

  @Value("${spring.datasource.url}")
  private String databaseUrl;

  @Value("${spring.datasource.username}")
  private String databaseUsername;

  @Value("${spring.datasource.password}")
  private String databasePassword;

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
  }

}
