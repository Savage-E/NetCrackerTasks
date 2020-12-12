package com.netcracker;

import com.netcracker.contracts.CellularContract;
import com.netcracker.contracts.Contract;
import com.netcracker.contracts.DigitalTvContract;
import com.netcracker.contracts.InternetContract;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;



/**
 * Class provides reading contract from csv file.
 *
 * @author Vlad Kotov
 */
public class LoadFromCsvFile {


  /**
   * Reads contracts from specified file.
   *
   * @param filePath   the file path of contracts
   * @param repository the repository where to add contracts
   */
  public static void readFrom(String filePath, IRepository<Contract> repository) {
    FileReader filereader = null;
    ArrayList<String[]> allData;
    try {
      filereader = new FileReader(filePath);


    } catch (FileNotFoundException e) {
      System.out.println("Error to open the file");
      e.printStackTrace();


    }

    try {
      CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
      assert filereader != null;
      CSVReader csvReader = new CSVReaderBuilder(filereader)
              .withCSVParser(parser)
              .build();
      allData = (ArrayList<String[]>) csvReader.readAll();
      addContracts(allData, repository);

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error to read the date from file");

    }

  }

  private static void addContracts(List<String[]> contracts, IRepository<Contract> repository) {

    int clientId;
    int contractId;
    LocalDate startDate;
    LocalDate endDate;
    int contractNum;
    String fio;
    LocalDate birthday;
    String gender;
    int passportNum;
    int maxInternetSpeed;
    int mb;
    int sms;
    int minutes;
    String channels;
    String contractType;
    Contract newContract = null;
    ArrayList<Person> persons = new ArrayList<>();
    Person newPerson;

    for (String[] row : contracts) {

      newPerson = null;
      clientId = Integer.parseInt(row[4]);

      for (Person p : persons) {
        if (p.getId() == clientId) {
          newPerson = p;
        }
      }
      fio = row[5];
      birthday = new LocalDate(row[6]);
      gender = row[7];
      passportNum = Integer.parseInt(row[8]);

      if (newPerson == null) {
        newPerson = new Person(clientId, fio, birthday, gender, passportNum);
        persons.add(newPerson);
      }


      contractId = Integer.parseInt(row[0]);
      startDate = new LocalDate(row[1]);
      endDate = new LocalDate(row[2]);
      contractNum = Integer.parseInt(row[3]);

      contractType = row[9];
      switch (contractType) {
        case "Internet":
          maxInternetSpeed = Integer.parseInt(row[10]);

          newContract = new InternetContract(contractId, startDate, endDate,
                  contractNum, newPerson, maxInternetSpeed);
          break;
        case "Cellular":

          String[] contractPack = row[10].split(" ");
          sms = Integer.parseInt(contractPack[0]);
          mb = Integer.parseInt(contractPack[1]);
          minutes = Integer.parseInt(contractPack[2]);

          newContract = new CellularContract(contractId, startDate, endDate,
                  contractNum, newPerson, sms, mb, minutes);
          break;
        case "DigitalTV":
          channels = row[10];
          newContract = new DigitalTvContract(contractId, startDate,
                  endDate, contractNum, newPerson, channels);

          break;
        default:
          break;
      }

      repository.add(newContract);
    }
  }

}



