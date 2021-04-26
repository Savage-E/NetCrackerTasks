package com.netcracker.jdbc;

import com.netcracker.Repository;
import com.netcracker.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 */
public class RepositoryDatabase {

  private static final Logger LOGGER = LogManager.getLogger(RepositoryDatabase.class);

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
  }

  public Repository restoreRepo() {
    Repository repository = new Repository();

    try (Connection connection = DriverManager.getConnection(url, user, password)) {

      PreparedStatement statement = connection.prepareStatement("SELECT * FROM agreement");
      ResultSet resultSet = statement.executeQuery();

      Person person;
      Contract contract = new Contract();
      while (resultSet.next()) {
        person = new Person(resultSet.getInt(5), resultSet.getString(6),
                LocalDate.parse(resultSet.getString(7)), resultSet.getString(8)
                , resultSet.getInt(9));

        String[] info = resultSet.getString(11).split(" ");


        switch (resultSet.getString(10)) {
          case "Cellular Contract": {
            contract = new CellularContract(resultSet.getInt(1),
                    LocalDate.parse(resultSet.getString(2)),
                    LocalDate.parse(resultSet.getString(3)),
                    resultSet.getInt(4),
                    person, Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2])
            );
            break;
          }
          case "Digital Tv Contract": {
            contract = new DigitalTvContract(resultSet.getInt(1),
                    LocalDate.parse(resultSet.getString(2)),
                    LocalDate.parse(resultSet.getString(3)),
                    resultSet.getInt(4),
                    person, info[0]);
            break;
          }
          case "Internet Contract": {
            contract = new InternetContract(resultSet.getInt(1),
                    LocalDate.parse(resultSet.getString(2)),
                    LocalDate.parse(resultSet.getString(3)),
                    resultSet.getInt(4),
                    person, Integer.parseInt(info[0]));
          }
          break;
        }
        repository.add(contract);
      }
    } catch (SQLException throwable) {
      LOGGER.error(throwable);
    }
    return repository;
  }

  public void saveRepo(Repository repository) {

    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      PreparedStatement preparedStatement = connection.
              prepareStatement("INSERT INTO agreement(id,start_date,end_date,contract_num, " +
                      "person_id,name, birthday, gender,pass_num_series,type,addition_info)" +
                      "VALUES(?,?,?,?,?,?,?,?,?,?,?);");

      for (Contract contract : repository.toArrayList()) {
        preparedStatement.setInt(1, contract.getId());
        preparedStatement.setDate(2, Date.valueOf(contract.getStartDate().toString()));
        preparedStatement.setDate(3, Date.valueOf(contract.getEndDate().toString()));
        preparedStatement.setInt(4, contract.getContractNumber());
        Person person = contract.getPerson();
        preparedStatement.setInt(5, person.getId());
        preparedStatement.setString(6, person.getFio());
        preparedStatement.setDate(7, Date.valueOf(person.getBirthday().toString()));
        preparedStatement.setString(8, person.getGender());
        preparedStatement.setInt(9, person.getPassport_num_series());

        if (contract instanceof CellularContract) {
          preparedStatement.setString(10, "Cellular Contract");
          preparedStatement.setString(11, ((CellularContract) contract).getSms()
                  + " " + ((CellularContract) contract).getMb() + " " +
                  ((CellularContract) contract).getMinutes());
        } else if (contract instanceof DigitalTvContract) {
          preparedStatement.setString(10, "Digital Tv Contract");
          preparedStatement.setString(11, ((DigitalTvContract) contract).getChannelPack());
        } else if (contract instanceof InternetContract) {
          preparedStatement.setString(10, "Internet Contract");
          preparedStatement.setString(11, String.valueOf(((InternetContract) contract).getMaxSpeed()));
        }
        preparedStatement.executeUpdate();
      }
    } catch (SQLException throwable) {
      LOGGER.error("Cannot connect to database", throwable);
    }
  }
}
