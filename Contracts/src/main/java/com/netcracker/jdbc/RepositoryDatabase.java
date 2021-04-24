package com.netcracker.jdbc;

import com.netcracker.Repository;
import com.netcracker.entities.Contract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 */
public class RepositoryDatabase {

  private static final Logger LOGGER = LogManager.getLogger(RepositoryDatabase.class);
  Flyway flyway;

  private String url;
  private String user;
  private String password;


  public RepositoryDatabase() {
    try (InputStream is = RepositoryDatabase.class.getClassLoader()
            .getResourceAsStream("database.properties")) {
      Properties props = new Properties();
      props.load(is);
      url = props.getProperty("datasource.url");
      user = props.getProperty("datasource.user");
      password = props.getProperty("datasource.password");
    } catch (IOException e) {
      LOGGER.error("Error to read data from database.properties", e);
    }
    flyway = Flyway.configure().dataSource(url, user, password)
            .locations("db")
            .load();
    flyway.migrate();
  }

  public void restoreRepo() {


  }

  public void saveRepo(Repository repository) {

    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      PreparedStatement preparedStatement = connection.
              prepareStatement("INSERT INTO contract(id,start_date,end_date,contract_num, " +
                      "person_id,name, birthday, gender,pass_num_series,type,addition_info)" +
                      "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");

      for (int i = 0; i < repository.size(); i++) {
        Contract contract = repository.get(0);

      }
    } catch (SQLException throwable) {
      LOGGER.error("Cannot connect to database", throwable);
    }

  }
}
