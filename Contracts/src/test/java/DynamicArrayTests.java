import com.netcracker.*;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DynamicArrayTests {
    DynamicArray<Contract> contracts = new DynamicArray<Contract>();


    @Test
    public void whenInsertNewElementThenTrueReturned() {

        assertTrue(contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,")));

    }

    @Test
    public void whenAddContractThenContractAdded() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        Contract expected = new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,");
        Contract actual = contracts.get(0);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void whenGetContractWithId8ThenContractReturned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        Contract expected = new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412);
        Contract actual = contracts.get(1);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetIndex3ThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.get(3);

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetIndexLessThan0ThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.get(-1);
    }

    @Test
    public void whenSetNewContractThenContractsUpdated() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.set(1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        int expected = 3;
        int actual = contracts.get(1).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void whenSetNewContractThenPreviousContractReturned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        int expected = 8;
        int actual = contracts.set(1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412)).getId();
        assertEquals(expected, actual);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetNewContractToNegativeIndexThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.set(-1, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetNewContractToIndexMoreThanSizeThenThrowException() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.set(3, new CellularContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
    }

    @Test
    public void whenRemoveContractThenTrueReturned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.remove(0);
    }


    @Test
    public void whenInvokeIsEmptyMethodThenTrueReturned() {
        assertTrue(contracts.isEmpty());
    }

    @Test
    public void whenInvokeClearMethodThenArrayBecameEmpty() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));

        contracts.clear();
        assertTrue(contracts.isEmpty());
    }

    @Test
    public void whenInvokeSizeMethodThenSize4Returned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));
        int expected = 4;
        int actual = contracts.size();

        assertEquals(expected, actual);
    }

    @Test
    public void whenInvokeGetCapacityThen20Returned() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));
        contracts.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(5, "lqqw", new LocalDate(1999, 1, 2, CopticChronology.getInstance()), "female", 435), "rwerwew"));
        contracts.add(new DigitalTVContract(6, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), "rwerwew"));
        contracts.add(new DigitalTVContract(7, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(7, "rhjg", new LocalDate(1999, 2, 13, CopticChronology.getInstance()), "female", 2139876), "rwerwew"));
        contracts.add(new CellularContract(13, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), 4567, 1245, 123));

        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.add(new CellularContract(9, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), 231, 3123, 131));
        contracts.add(new CellularContract(10, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), 231, 4124, 1231));
        int expected = 20;
        int actual = contracts.getCapacity();
        assertEquals(expected, actual);


    }

    @Test
    public void whenInvokeTrimToSizeThenArrayCapacityReducedToSize() {
        contracts.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "rwerwew"));
        contracts.add(new DigitalTVContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), "rwerwew"));
        contracts.add(new DigitalTVContract(3, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), "rwerwew"));
        contracts.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(4, "llcd", new LocalDate(1999, 10, 12, CopticChronology.getInstance()), "female", 423423), "rwerwew"));
        contracts.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(5, "lqqw", new LocalDate(1999, 1, 2, CopticChronology.getInstance()), "female", 435), "rwerwew"));
        contracts.add(new DigitalTVContract(6, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), "rwerwew"));
        contracts.add(new DigitalTVContract(7, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(7, "rhjg", new LocalDate(1999, 2, 13, CopticChronology.getInstance()), "female", 2139876), "rwerwew"));
        contracts.add(new CellularContract(13, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(6, "llpoklx", new LocalDate(1999, 4, 2, CopticChronology.getInstance()), "male", 432467), 4567, 1245, 123));

        contracts.add(new CellularContract(8, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(1, "fewe", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        contracts.add(new CellularContract(9, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(2, "dsflsd", new LocalDate(1999, 10, 22, CopticChronology.getInstance()), "male", 313123), 231, 3123, 131));
        contracts.add(new CellularContract(10, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), new Client(3, "cdscs", new LocalDate(1999, 10, 21, CopticChronology.getInstance()), "male", 4234), 231, 4124, 1231));
        contracts.trimToSize();
        int expected = 11;
        int actual = contracts.getCapacity();
        assertEquals(expected, actual);
    }

}