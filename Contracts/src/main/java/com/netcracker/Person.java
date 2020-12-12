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
  private int passportNumSeries;

  /**
   * Creates a person.
   *
   * @param id                  the person id
   * @param fio                 person's full name
   * @param birthday            person's birthday
   * @param gender              person's gender
   * @param passportNumSeries  the person's ID(passport data)
   */
  public Person(int id, @NotNull String fio, LocalDate birthday,
                @NotNull String gender, int passportNumSeries) {
    this.id = id;
    this.fio = fio;
    this.birthday = birthday;
    this.gender = gender;
    this.passportNumSeries = passportNumSeries;
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
    return passportNumSeries;
  }

  public void setPassport_num_series(int passportNumSeries) {
    this.passportNumSeries = passportNumSeries;
  }

  public int getAge() {
    return Years.yearsBetween(birthday, LocalDate.now()).getYears();
  }
}
