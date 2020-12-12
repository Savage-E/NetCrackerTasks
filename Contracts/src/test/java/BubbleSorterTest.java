import com.netcracker.DynamicArray;
import com.netcracker.Person;
import com.netcracker.contracts.CellularContract;
import com.netcracker.contracts.Contract;
import com.netcracker.contracts.DigitalTvContract;
import com.netcracker.sorters.BubbleSorter;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class BubbleSorterTest {

  @Test
  public void whenSortByIdThenArraySorted() {
    DynamicArray<Contract> array = new DynamicArray<>();
    array.add(new DigitalTvContract(31, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 34, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
    array.add(new DigitalTvContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 3423, new Person(3, "Andrew Betman", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
    array.add(new DigitalTvContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 2423, new Person(4, "Lolita Vorobyova", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "female", 21312311), "CNN,1,"));
    array.add(new CellularContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 4323, new Person(1, "Andrew Bolton", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
    array.add(new DigitalTvContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 234, new Person(4, "Vlad Kotov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));

    new BubbleSorter().sort(array, Comparator.comparingInt(Contract::getId));

    int expected1 = 1;
    int expected2 = 2;
    int expected3 = 4;
    int expected4 = 5;
    int expected5 = 31;

    int actual1 = array.get(0).getId();
    int actual2 = array.get(1).getId();
    int actual3 = array.get(2).getId();
    int actual4 = array.get(3).getId();
    int actual5 = array.get(4).getId();


    assertEquals(expected1, actual1);
    assertEquals(expected2, actual2);
    assertEquals(expected3, actual3);
    assertEquals(expected4, actual4);
    assertEquals(expected5, actual5);

  }
}
