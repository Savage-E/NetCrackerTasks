import com.netcracker.Repository;
import com.netcracker.entities.CellularContract;
import com.netcracker.entities.Contract;
import com.netcracker.entities.DigitalTvContract;
import com.netcracker.entities.Person;
import com.netcracker.exceptions.InjectionException;
import com.netcracker.parser.CSVReader;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.netcracker.reflection.Injector.inject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CSVReaderTest {

  Repository repository;
  CSVReader csvReader;

  @Before
  public void initRepo() throws  InjectionException {
    repository = new Repository();
    csvReader = new CSVReader();
    inject(csvReader);
  }


  @Test
  public void whenReadToEmptyRepoThenRepositoryFilled() {

    csvReader.readFrom("src\\test\\resources\\Testcontracts", repository);
    ArrayList<Contract> arrayList = repository.toArrayList();
    String expectedFio = "Kotov Vladislav Olegovich";
    int expectedId = 32;
    String actualFio = arrayList.get(0).getPerson().getFio();
    int actualId = arrayList.get(0).getId();
    assertEquals(expectedFio, actualFio);
    assertEquals(expectedId, actualId);
  }

  @Test
  public void whenReadContractsThenAdded() {
    repository.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repository.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Andrew Betman",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    repository.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita Vorobyova",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );

    repository.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "Andrew Bolton",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );

    repository.add(new DigitalTvContract(32,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    csvReader.readFrom("src\\test\\resources\\Testcontracts", repository);
    ArrayList<Contract> arrayList = repository.toArrayList();

    String expectedFio1 = "Kotov Vladislav Olegovich";
    int expectedId1 = 32;
    String actualFio1 = arrayList.get(5).getPerson().getFio();
    int actualId1 = arrayList.get(5).getId();

    String expectedFio2 = "Lolita Vorobyova";
    int expectedId2 = 5;
    String actualFio2 = arrayList.get(2).getPerson().getFio();
    int actualId2 = arrayList.get(2).getId();

    assertEquals(expectedFio1, actualFio1);
    assertEquals(expectedId1, actualId1);
    assertEquals(expectedFio2, actualFio2);
    assertEquals(expectedId2, actualId2);
  }

  @Test
  public void whenReadWithContractAge210ThenContractRejected() {
    repository.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita Vorobyova",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );

    repository.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "Andrew Bolton",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );

    repository.add(new DigitalTvContract(34,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    csvReader.readFrom("src\\test\\resources\\Contracts2", repository);
    Contract expected = repository.get(32);
    assertNull(expected);
  }


  @Test
  public void whenReadWithAgeContract14ThenContractRejected() {

    csvReader.readFrom("src\\test\\resources\\Contracts3", repository);
    Contract expected = repository.get(32);
    assertNull(expected);
  }

  @Test
  public void whenReadCorrectAgeDataFioContractThenContractAdded() {

    csvReader.readFrom("src\\test\\resources\\TestContracts", repository);
    String expected = "Kotov Vladislav Olegovich";
    String actual = repository.get(32).getPerson().getFio();
    assertEquals(expected, actual);
  }

  @Test
  public void whenReadWrongFioContractThenContractRejected() {


    csvReader.readFrom("src\\test\\resources\\Contracts2", repository);
    Contract expected = repository.get(31);
    assertNull(expected);
  }

  @Test
  public void whenReadWrongContractsDatesThenContractsRejected() {

    csvReader.readFrom("src\\test\\resources\\WrongContractDates", repository);
    Contract expected1 = repository.get(31);
    Contract expected2 = repository.get(32);
    assertNull(expected1);
    assertNull(expected2);

  }

}




