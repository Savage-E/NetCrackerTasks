package com.netcracker.entities;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

/**
 * Represents parent class for contracts.
 *
 * @author Vlad Kotov
 */
public class Contract {
  private int id;
  private @NotNull LocalDate startDate;
  private @NotNull LocalDate endDate;
  private int contractNumber;


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
  protected Contract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate,
                     int contractNumber, @NotNull Person person) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.contractNumber = contractNumber;
    this.person = person;
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