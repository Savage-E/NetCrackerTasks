package com.netcracker.entities;

import com.netcracker.util.xml.DateXmlAdapter;
import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents parent class for contracts.
 *
 * @author Vlad Kotov
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({DigitalTvContract.class,InternetContract.class,CellularContract.class})
public class Contract {
  @XmlElement
  private int id;

  @XmlJavaTypeAdapter(DateXmlAdapter .class)
  @XmlElement
  private @NotNull LocalDate startDate;

  @XmlJavaTypeAdapter(DateXmlAdapter .class)
  @XmlElement
  private @NotNull LocalDate endDate;
  @XmlElement
  private int contractNumber;
  @XmlElement
  private @NotNull Person person;


  /**
   * Creates a contract.
   *
   * @param id             the contract id
   * @param startDate      the date of the beginning of the contract
   * @param endDate        the end date of the contract
   * @param contractNumber the number of the contract
   * @param person         person entity
   */
  public Contract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate,
                  int contractNumber, @NotNull Person person) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.contractNumber = contractNumber;
    this.person = person;
  }

  public Contract() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public @NotNull LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(@NotNull LocalDate startDate) {
    this.startDate = startDate;
  }

  public @NotNull LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(@NotNull LocalDate endDate) {
    this.endDate = endDate;
  }

  public int getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(int contractNumber) {
    this.contractNumber = contractNumber;
  }

  public @NotNull Person getPerson() {
    return person;
  }

  public void setPerson(@NotNull Person person) {
    this.person = person;
  }
}