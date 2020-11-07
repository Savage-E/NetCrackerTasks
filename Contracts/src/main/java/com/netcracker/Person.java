package com.netcracker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 * Represents person entity.
 *
 * @author Vlad Kotov
 */
public final class Person {
    private int id;
    private @NotNull String fio;
    private LocalDate birthday;
    private @NotNull String gender;
    private int passport_num_series;

    /**
     * Creates a person.
     *
     * @param id                  the person id
     * @param fio                 person's full name
     * @param birthday            person's birthday
     * @param gender              person's gender
     * @param passport_num_series the person's ID(passport data)
     */
    public Person(int id, @NotNull String fio, LocalDate birthday, @NotNull String gender, int passport_num_series) {
        this.id = id;
        this.fio = fio;
        this.birthday = birthday;
        this.gender = gender;
        this.passport_num_series = passport_num_series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull String getFio() {
        return fio;
    }

    public void setFio(@NotNull String fio) {
        this.fio = fio;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public @NotNull String getGender() {
        return gender;
    }

    public void setGender(@NotNull String gender) {
        this.gender = gender;
    }

    public int getPassport_num_series() {
        return passport_num_series;
    }

    public void setPassport_num_series(int passport_num_series) {
        this.passport_num_series = passport_num_series;
    }

    public int getAge() {
        return Years.yearsBetween(birthday, LocalDate.now()).getYears();
    }
}
