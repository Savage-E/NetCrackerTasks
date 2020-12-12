import com.netcracker.entities.Contract;
import com.netcracker.entities.DigitalTvContract;
import com.netcracker.entities.Person;
import com.netcracker.validators.*;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidationTests {

  @Test
  public void whenAddNegativeAgeThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31, new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov", new LocalDate(2020, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,");
    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = ageValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }

  @Test
  public void whenAddAge120ThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31, new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov", new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,");
    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = ageValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());


  }

  @Test
  public void whenAddAge87ThenWarningMessageReturned() {
    Contract contract = new DigitalTvContract(31, new LocalDate(2000, 12, 12,
            CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov", new LocalDate(1930, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,");

    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.WARNING);
    Message actual = ageValidator.validate(contract);
    assertEquals(expected.getStatus(), actual.getStatus());

  }

  @Test
  public  void whenAddFioWithNumbersThenErrorMessageReturned(){
    Contract contract=new DigitalTvContract(31, new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor1 Potapov", new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,")
            ;
    Validator<Contract> fioValidator=new FioValidator();
    Message expected= new Message();
    expected.setStatus(Status.ERROR);
    Message actual= fioValidator.validate(contract);

    assertEquals(expected.getStatus(),actual.getStatus());


  }

}
