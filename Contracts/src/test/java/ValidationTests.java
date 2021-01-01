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
  public void whenValidateNegativeAgeThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(2020, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = ageValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }

  @Test
  public void whenValidateAge120ThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = ageValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());


  }

  @Test
  public void whenValidateAge87ThenWarningMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1930, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );

    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.WARNING);
    Message actual = ageValidator.validate(contract);
    assertEquals(expected.getStatus(), actual.getStatus());

  }

  @Test
  public void whenValidateAge43ThenOkMessageReturned() {
    Contract contract = new DigitalTvContract(31, new LocalDate(2000, 12, 12,
            CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor Potapov",
            new LocalDate(1987, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );

    Validator<Contract> ageValidator = new AgeValidator();
    Message expected = new Message();
    expected.setStatus(Status.OK);
    Message actual = ageValidator.validate(contract);
    assertEquals(expected.getStatus(), actual.getStatus());

  }

  @Test
  public void whenValidateFioWithNumbersThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fydor1 Potapov",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> fioValidator = new FioValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = fioValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());


  }

  @Test
  public void whenValidateFioWithSpacesThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fyd o r Potapov",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> fioValidator = new FioValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = fioValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());


  }

  @Test
  public void whenValidateFioWithWordThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2000, 12, 12, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fyd",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> fioValidator = new FioValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = fioValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());


  }


  @Test
  public void whenValidateContractWithWrongStartDateThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2021, 12, 20, CopticChronology.getInstance()),
            new LocalDate(2000, 12, 21, CopticChronology.getInstance()),
            34, new Person(1, "Fyd",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> dateValidator = new DateValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = dateValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }

  @Test
  public void whenValidateContractWithAlmostExpiredEndDateThenWarningMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2015, 12, 20, CopticChronology.getInstance()),
            LocalDate.now(),
            34, new Person(1, "Fydor",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> dateValidator = new DateValidator();
    Message expected = new Message();
    expected.setStatus(Status.WARNING);
    Message actual = dateValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }

  @Test
  public void whenValidateContractWithExpiredEndDateThenWarningMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2015, 12, 20, CopticChronology.getInstance()),
            new LocalDate(2017, 12, 20, CopticChronology.getInstance()),
            34, new Person(1, "Fydor",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,");
    Validator<Contract> dateValidator = new DateValidator();
    Message expected = new Message();
    expected.setStatus(Status.WARNING);
    Message actual = dateValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }

  @Test
  public void whenValidateContractWithEndDateGreaterThanStartThenErrorMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2017, 12, 20, CopticChronology.getInstance()),
            new LocalDate(2015, 12, 20, CopticChronology.getInstance()),
            34, new Person(1, "Fydor",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> dateValidator = new DateValidator();
    Message expected = new Message();
    expected.setStatus(Status.ERROR);
    Message actual = dateValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }


  @Test
  public void whenValidateContractWithValidDatesThenOkMessageReturned() {
    Contract contract = new DigitalTvContract(31,
            new LocalDate(2017, 12, 20, CopticChronology.getInstance()),
            new LocalDate(2051, 12, 20, CopticChronology.getInstance()),
            34, new Person(1, "Fydor",
            new LocalDate(1900, 10, 23, CopticChronology.getInstance()),
            "male", 21312311), "CNN,1,"
    );
    Validator<Contract> dateValidator = new DateValidator();
    Message expected = new Message();
    expected.setStatus(Status.OK);
    Message actual = dateValidator.validate(contract);

    assertEquals(expected.getStatus(), actual.getStatus());
  }

}
