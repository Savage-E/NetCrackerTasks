package com.netcracker.contracts;

import com.netcracker.Person;
import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

/**
 * Represents the entity of Internet contracts.
 *
 * <p>Please see the {@link Contract} class for true identity
 *
 * @author Vlad Kotov
 */
public final class InternetContract extends Contract {
  private int maxSpeed;

  /**
   * Creates an Internet Contract.
   *
   * @param id             the  contract id
   * @param startDate      the date of the beginning of the contract
   * @param endDate        the end date of the contract
   * @param contractNumber the  number of the contract
   * @param person         the person entity
   * @param maxSpeed       the max speed of the internet in the contract
   */
  public InternetContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate,
                          int contractNumber, @NotNull Person person, int maxSpeed) {
    super(id, startDate, endDate, contractNumber, person);
    this.maxSpeed = maxSpeed;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(int maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
}
