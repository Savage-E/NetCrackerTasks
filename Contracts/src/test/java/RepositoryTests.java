import com.netcracker.*;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryTests {
    Repository repo = new Repository();

    @Test
    public void whenSetNewContractThenContractsUpdated() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repo.set(1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        int expected = 3;
        int actual = repo.get(3).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void whenSetNewContractThenPreviousContractReturned() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        int expected = 1;
        int actual = repo.set(1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412)).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void whenSetNonExistContractThenNullReturned() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));

        Contract contract = repo.set(3, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        assertEquals(null, contract);
    }


    @Test
    public void whenDeleteContractThenTrueReturned() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        assertTrue(repo.delete(1));
    }

    @Test
    public void whenDeleteNonExistContractThenFalseReturned() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repo.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(13, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        assertFalse(repo.delete(7));
    }

    @Test
    public void whenAddNewContractThenTrueReturned() {
        boolean actual = repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        assertTrue(actual);
    }

    @Test
    public void whenAddContactThenContractAdded() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repo.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));

        Contract expected = new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,");
        Contract actual = repo.get(1);
        assertEquals(expected.getId(), actual.getId());
    }


    @Test
    public void whenGetContactWithId4ThenContractReturned() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repo.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        Contract expected = new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,");

        Contract actual = repo.get(4);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void whenGetNonExistContractThenNullReturned() {
        repo.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repo.add(new CellularContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repo.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        Contract actual = repo.get(3);
        assertEquals(null, actual);
    }


}





