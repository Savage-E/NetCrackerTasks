import com.netcracker.Repository;
import com.netcracker.entities.CellularContract;
import com.netcracker.entities.Contract;
import com.netcracker.entities.DigitalTvContract;
import com.netcracker.entities.Person;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static com.netcracker.reflection.Injector.inject;
import static org.junit.Assert.*;

public class RepositoryTest {
  Repository repo = new Repository();

  @Before
  public void initRepo() {
    repo = new Repository();
  }

  @Test
  public void whenSetNewContractThenContractsUpdated() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            33, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    repo.add(new CellularContract(8,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            43223, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );

    repo.set(1, new CellularContract(3,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            52, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );

    int expected = 3;
    int actual = repo.get(3).getId();
    assertEquals(expected, actual);
  }

  @Test
  public void whenSetNewContractThenPreviousContractReturned() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    repo.add(new CellularContract(8,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2345, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    int expected = 1;

    int actual = repo.set(1,
            new CellularContract(3,
                    new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
                    new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
                    45, new Person(1, "fewe",
                    new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
                    "male", 21312311), 423, 3432, 123412)
    ).getId();
    assertEquals(expected, actual);
  }

  @Test
  public void whenSetNonExistContractThenNullReturned() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            343, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(8,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            243, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );

    Contract contract = repo.set(3,
            new CellularContract(3,
                    new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
                    new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
                    44, new Person(1, "fewe",
                    new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
                    "male", 21312311), 423, 3432, 123412)
    );
    assertNull(contract);
  }


  @Test
  public void whenDeleteContractThenTrueReturned() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            342, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(8,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2432, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    assertTrue(repo.delete(1));
  }

  @Test
  public void whenDeleteNonExistContractThenFalseReturned() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            42321, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            24, new Person(3, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            54, new Person(6, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(13,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            654, new Person(5, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3534, new Person(2, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(3,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4534, new Person(4, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    assertFalse(repo.delete(7));

  }

  @Test
  public void whenAddNewContractThenTrueReturned() {
    boolean actual = repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            534, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    assertTrue(actual);
  }

  @Test
  public void whenAddContactThenContractAdded() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            433, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            43, new Person(3, "Fydor Voytov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "fdw,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            23, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2432, new Person(1, "Leo Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    Contract expected = new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Contract actual = repo.get(1);
    assertEquals(expected.getId(), actual.getId());
  }


  @Test
  public void whenGetContactWithId4ThenContractReturned() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            242, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            23, new Person(12, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            23423, new Person(54, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            23432, new Person(24, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    Contract expected = new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            432, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );

    Contract actual = repo.get(4);
    assertEquals(expected.getId(), actual.getId());
  }

  @Test
  public void whenGetNonExistContractThenNullReturned() {
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Sergey Mokhno",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "fewe342",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    Contract actual = repo.get(3);
    assertNull(actual);
  }

  @Test
  public void whenSearchWithMalePersonsThenContractsWithFemalePersonsNotExist() {
    repo.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Sergey Mokhno",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "fewe342",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    Repository repo1 = (Repository) repo.searchBy(u -> u.getPerson().getGender().equals("male"));
    assertNull(repo1.get(3));
  }

  @Test
  public void whenSearchWithMalePersonsThenContractsWithMalePersonsExist() {
    repo.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Sergey Mokhno",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "fewe342",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    Repository repo1 = (Repository) repo.searchBy(u -> u.getPerson().getGender().equals("male"));
    String actual1 = repo1.get(1).getPerson().getGender();
    String actual2 = repo1.get(31).getPerson().getGender();
    String actual3 = repo1.get(2).getPerson().getGender();
    String actual4 = repo1.get(4).getPerson().getGender();

    String expected = "male";
    assertEquals(expected, actual1);
    assertEquals(expected, actual2);
    assertEquals(expected, actual3);
    assertEquals(expected, actual4);
  }

  @Test
  public void whenSearchContractWithNameVladKotovThenEmptyRepositoryReturned() {

    repo.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Sergey Mokhno",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,

            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "fewe342",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Nikita Petrov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    Repository repo1 = (Repository) repo.searchBy(u -> u.getPerson().getFio().equals("Vlad Kotov"));

    assertNull(repo1.get(31));
    assertNull(repo1.get(1));
    assertNull(repo1.get(5));
    assertNull(repo1.get(2));
    assertNull(repo1.get(4));
  }

  @Test(expected = RuntimeException.class)
 // public void whenSortByFioBubbleSortThenRepositorySorted() throws IllegalAccessException {
 public void whenSortByFioBubbleSortThenThrowRuntimeException() throws IllegalAccessException {
          repo.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Andrew Betman",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita Vorobyova",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "Andrew Bolton",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    inject(repo);
    /*repo.sortBy(Comparator.comparing(o -> o.getPerson().getFio()));

    ArrayList<Contract> list = repo.toArrayList();
    ArrayList<String> expected = new ArrayList<>();
    expected.add("Andrew Betman");
    expected.add("Andrew Bolton");
    expected.add("Fydor Potapov");
    expected.add("Lolita Vorobyova");
    expected.add("Vlad Kotov");

    ArrayList<String> actual = new ArrayList<>();
    actual.add(list.get(0).getPerson().getFio());
    actual.add(list.get(1).getPerson().getFio());
    actual.add(list.get(2).getPerson().getFio());
    actual.add(list.get(3).getPerson().getFio());
    actual.add(list.get(4).getPerson().getFio());

    assertEquals(expected, actual);*/
  }

  @Test
  public void whenInvokeToArrayListThenRepositoryConverted() {
    repo.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Andrew Betman",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita Vorobyova",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    ArrayList<Contract> expected = new ArrayList<>();

    expected.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    expected.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Andrew Betman",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    expected.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita Vorobyova",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );

    ArrayList<Contract> actual = repo.toArrayList();

    assertEquals(expected.get(0).getContractNumber(), actual.get(0).getContractNumber());
    assertEquals(expected.get(1).getContractNumber(), actual.get(1).getContractNumber());
    assertEquals(expected.get(2).getContractNumber(), actual.get(2).getContractNumber());

  }

 /* @Test
  public void whenSortByFioMergeSortThenRepositorySorted() throws IllegalAccessException {
    repo.add(new DigitalTvContract(31,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    repo.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            3423, new Person(3, "Andrew Betman",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    repo.add(new DigitalTvContract(5,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            2423, new Person(4, "Lolita Vorobyova",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "female", 21312311), "CNN,1,")
    );
    repo.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            4323, new Person(1, "Andrew Bolton",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repo.add(new DigitalTvContract(4,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            234, new Person(4, "Vlad Kotov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );
    inject(repo);
    repo.sortBy(Comparator.comparing(o -> o.getPerson().getFio()));

    ArrayList<Contract> list = repo.toArrayList();
    ArrayList<String> expected = new ArrayList<>();
    expected.add("Andrew Betman");
    expected.add("Andrew Bolton");
    expected.add("Fydor Potapov");
    expected.add("Lolita Vorobyova");
    expected.add("Vlad Kotov");

    ArrayList<String> actual = new ArrayList<>();
    actual.add(list.get(0).getPerson().getFio());
    actual.add(list.get(1).getPerson().getFio());
    actual.add(list.get(2).getPerson().getFio());
    actual.add(list.get(3).getPerson().getFio());
    actual.add(list.get(4).getPerson().getFio());

    assertEquals(expected, actual);
  }*/

}





