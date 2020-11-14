package com.netcracker;

import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;

import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {




        Repository repository = new Repository();
        repository.add(new DigitalTVContract(31, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 34, new Person(1, "Fydor Potapov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repository.add(new DigitalTVContract(1, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 3423, new Person(3, "Sergey Mokhno", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));
        repository.add(new DigitalTVContract(5, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 2423, new Person(4, "Lolita", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "female", 21312311), "CNN,1,"));
        repository.add(new CellularContract(2, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 4323, new Person(1, "fewe342", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), 423, 3432, 123412));
        repository.add(new DigitalTVContract(4, new LocalDate(2010, 12, 12, CopticChronology.getInstance()), new LocalDate(2010, 12, 21, CopticChronology.getInstance()), 234, new Person(4, "Vlad Kotov", new LocalDate(1999, 10, 23, CopticChronology.getInstance()), "male", 21312311), "CNN,1,"));

        Repository repo1 = (Repository) repository.searchBy(u->u.getPerson().getGender()=="male");

        System.out.println(repo1.get(5).getPerson().getFio());



    }

}