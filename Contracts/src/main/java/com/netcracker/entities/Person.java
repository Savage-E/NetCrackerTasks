package com.netcracker.entities;

import com.netcracker.util.xml.DateXmlAdapter;
import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents person entity.
 *
 * @author Vlad Kotov
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public final class Person {
  @XmlElement
  private int id;
  @XmlElement
  private @NotNull String fio;

  @XmlJavaTypeAdapter(DateXmlAdapter.class)
  @XmlElement
  private LocalDate birthday;

  @XmlElement
  private @NotNull String gender;

  @XmlElement
  private int passportNumSeries;

  /**
   * Creates a person.
   *
   * @param id                the person id
   * @param fio               person's full name
   * @param birthday          person's birthday
   * @param gender            person's gender
   * @param passportNumSeries the person's ID(passport data)
   */
  public Person(int id, @NotNull String fio, LocalDate birthday,
                @NotNull String gender, int passportNumSeries) {
    this.id = id;
    this.fio = fio;
    this.birthday = birthday;
    this.gender = gender;
    this.passportNumSeries = passportNumSeries;
  }
  public Person(){

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

  /**
   * Returns age of the specified person.
   *
   * @return the person age
   */
  public int getAge() {
    LocalDate localDate = LocalDate.now();
    int start = birthday.year().get();
    int end = LocalDate.now().year().get();

    return end - start;
  }

}
