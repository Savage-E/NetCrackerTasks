import com.netcracker.Repository;
import com.netcracker.entities.CellularContract;
import com.netcracker.entities.DigitalTvContract;
import com.netcracker.entities.InternetContract;
import com.netcracker.entities.Person;
import com.netcracker.util.xml.XMLSerializer;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vlad Kotov
 * Date: 02.май2021
 * Time:  10:41
 * Project: Contracts
 * Description:
 */
public class XMLTest {
  Repository repository;
  XMLSerializer xmlSerializer = new XMLSerializer();

  @Before
  public void init() {
    repository = new Repository();
  }

  @Test
  public void toXml() {

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
    repository.add(new InternetContract(3,
            new LocalDate(2010, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2010, 12, 21, CopticChronology.getInstance()),
            43223, new Person(1, "fewe",
            new LocalDate(1988, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), 423)
    );
    xmlSerializer.toXml(repository);
  }

  @Test
  public void fromXml() {
    repository = xmlSerializer.fromXml("contracts.xml");
    System.out.println(repository.get(1).getPerson().getFio());
    assertEquals(repository.get(1).getPerson().getFio(), "Fydor Potapov");
    assertEquals(repository.get(2).getPerson().getGender(), "male");
  }
}
