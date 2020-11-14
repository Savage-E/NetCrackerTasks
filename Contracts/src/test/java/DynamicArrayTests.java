import com.netcracker.DynamicArray;
import com.netcracker.Person;
import com.netcracker.contracts.CellularContract;
import com.netcracker.contracts.Contract;
import com.netcracker.contracts.DigitalTVContract;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DynamicArrayTests {
    DynamicArray<Contract> contracts = new DynamicArray<>();


    @Test
    public void whenInsertNewElementThenTrueReturned() {
        boolean actual = contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 34, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        assertTrue(actual);

    }

    @Test
    public void whenAddElementThenElementAdded() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 21, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        Contract expected = new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 23, new Person(3, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,");
        Contract actual = contracts.get(0);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void whenGetElementWithId8ThenElementReturned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 24, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 53245, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        Contract expected = new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 2352, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412);
        Contract actual = contracts.get(1);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetIndex3ThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 223, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 145, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.get(3);

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetIndexLessThan0ThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 211, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 422, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.get(-1);
    }

    @Test
    public void whenSetNewElementThenElementsUpdated() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 2313, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 2124, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.set(1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 85, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        int expected = 3;
        int actual = contracts.get(1).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void whenSetNewElementThenPreviousElementReturned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 564, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 453, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        int expected = 8;
        int actual = contracts.set(1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 423, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412)).getId();
        assertEquals(expected, actual);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetNewElementToNegativeIndexThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 452, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 68, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.set(-1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 21, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetNewElementToIndexMoreThanSizeThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 22, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 67, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.set(3, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 7876, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
    }

    @Test
    public void whenRemoveElementThenTrueReturned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 87, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 765, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        assertTrue(contracts.remove(0));
    }


    @Test
    public void whenInvokeIsEmptyMethodThenTrueReturned() {
        assertTrue(contracts.isEmpty());
    }

    @Test
    public void whenInvokeClearMethodThenArrayBecameEmpty() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 654, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 577, new Person(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 9876, new Person(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 584, new Person(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));

        contracts.clear();
        assertTrue(contracts.isEmpty());
    }

    @Test
    public void whenInvokeSizeMethodThenSize4Returned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 567, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 47, new Person(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 74, new Person(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 785, new Person(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));
        int expected = 4;
        int actual = contracts.size();

        assertEquals(expected, actual);
    }

    @Test
    public void whenInvokeGetCapacityThen20Returned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 333, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 211, new Person(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 1, new Person(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 54, new Person(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));
        contracts.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 5433, new Person(5, "lqqw", new LocalDate(1999, 1, 2, CopticChronology.getInstance()), "female", 435), "rwerwew"));
        contracts.add(new DigitalTVContract(6, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 4335, new Person(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), "rwerwew"));
        contracts.add(new DigitalTVContract(7, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 543, new Person(7, "rhjg", new LocalDate(1999, 2, 13, CopticChronology.getInstance()), "female", 2139876), "rwerwew"));
        contracts.add(new CellularContract(13, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 654, new Person(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), 4567, 1245, 123));

        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 43, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.add(new CellularContract(9, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 76, new Person(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), 231, 3123, 131));
        contracts.add(new CellularContract(10, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 362, new Person(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), 231, 4124, 1231));
        int expected = 20;
        int actual = contracts.getCapacity();
        assertEquals(expected, actual);


    }

    @Test
    public void whenInvokeTrimToSizeThenArrayCapacityReducedToSize() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 52, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 88, new Person(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 255, new Person(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 65, new Person(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));
        contracts.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 6785, new Person(5, "lqqw", new LocalDate(1999, 1, 2, CopticChronology.getInstance()), "female", 435), "rwerwew"));
        contracts.add(new DigitalTVContract(6, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 675, new Person(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), "rwerwew"));
        contracts.add(new DigitalTVContract(7, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 5465, new Person(7, "rhjg", new LocalDate(1999, 2, 13, CopticChronology.getInstance()), "female", 2139876), "rwerwew"));
        contracts.add(new CellularContract(13, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 65454, new Person(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), 4567, 1245, 123));

        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 653, new Person(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.add(new CellularContract(9, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 363, new Person(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), 231, 3123, 131));
        contracts.add(new CellularContract(10, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 654, new Person(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), 231, 4124, 1231));
        contracts.trimToSize();
        int expected = 11;
        int actual = contracts.getCapacity();
        assertEquals(expected, actual);
    }

}