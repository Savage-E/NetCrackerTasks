import com.netcracker.Repository;
import com.netcracker.entities.CellularContract;
import com.netcracker.entities.DigitalTvContract;
import com.netcracker.entities.Person;
import com.netcracker.jdbc.RepositoryDatabase;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositoryDatabaseTest {

  Repository repository;
  RepositoryDatabase repositoryDatabase;

  @Before
  public void initDataBase() {
    repositoryDatabase = new RepositoryDatabase();
  }

  @Before
  public void initRepository() {
    repository = new Repository();
  }

  @Test
  public void whenSaveRepositoryThenSuccessSave() {
    repository.add(new DigitalTvContract(1,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            33, new Person(1, "Fydor Potapov",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
    );

    repository.add(new CellularContract(2,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            43223, new Person(1, "fewe",
            new LocalDate(1999, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423, 3432, 123412)
    );
    repositoryDatabase.saveRepo(repository);
  }

  @Test
  public void whenRestoreRepositoryThenSuccessOperation() {
    repository = repositoryDatabase.restoreRepo();
    assertEquals(repository.get(1).getPerson().getFio(), "Fydor Potapov");
    assertEquals(repository.get(2).getPerson().getGender(),"male");
  }
}
