package com.netcracker.io;

import com.netcracker.IRepository;
import com.netcracker.entities.Contract;
import com.netcracker.entities.CellularContract;
import com.netcracker.entities.DigitalTvContract;
import com.netcracker.entities.InternetContract;
import com.netcracker.entities.Person;
import com.netcracker.validators.AgeValidator;
import com.netcracker.validators.DateValidator;
import com.netcracker.validators.FioValidator;
import com.netcracker.validators.Message;
import com.netcracker.validators.Status;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;



/**
 * Class provides reading contract from csv file.
 *
 * @author Vlad Kotov
 */
public class CSVReader {
  private static final Logger logger = LogManager.getLogger(CSVReader.class);

  /**
   * Reads contracts from specified file.
   *
   * @param filePath   the file path of contracts
   * @param repository the repository where to add contracts
   */
  public static void readFrom(String filePath, IRepository<Contract> repository) {
    logger.debug("Starting method readFrom");
    FileReader filereader;
    ArrayList<String[]> allData;

    try {
      filereader = new FileReader(filePath);
      CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
      com.opencsv.CSVReader csvReader = new CSVReaderBuilder(filereader)
              .withCSVParser(parser)
              .build();
      allData = (ArrayList<String[]>) csvReader.readAll();
      addContracts(allData, repository);


    } catch (FileNotFoundException e) {
      logger.info("Error to open the file");
      logger.error("Exception:",e);

    } catch (IOException e) {
      logger.info("Error to read the data from file");
      logger.error("Exception:",e);
    }

    logger.debug("Exiting method readFrom");
  }

  private static void addContracts(List<String[]> contracts, IRepository<Contract> repository) {
    logger.debug("Starting adding method addContract");
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
      if (validation(newContract)) {
        logger.debug("Adding to repository new contract");
        repository.add(newContract);
      } else {
        logger.info("Cannot add contract with id" + newContract.getId());
      }
    }
    logger.debug("Exiting from method addContract");
  }

  private static boolean validation(Contract newContract) {
    logger.debug("Starting validation method");
    Message[] messages = new Message[3];
    boolean result = false;
    FioValidator fioValidator = new FioValidator();
    AgeValidator ageValidator = new AgeValidator();
    DateValidator dateValidator = new DateValidator();

    messages[0] = fioValidator.validate(newContract);
    messages[1] = dateValidator.validate(newContract);
    messages[2] = ageValidator.validate(newContract);
    for (Message m : messages) {
      if (m.getStatus() == Status.ERROR) {
        logger.info(m.getMessage());
        logger.debug("Exiting from validation method");
        return result;
      }
    }
    result = true;
    logger.debug("Exiting from validation method");
    return result;

  }
}




