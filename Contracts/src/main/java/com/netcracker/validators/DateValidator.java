package com.netcracker.validators;

import com.netcracker.entities.Contract;
import org.joda.time.LocalDate;

/**
 *
 */
public class DateValidator implements Validator<Contract> {
  /**
   * @param contract
   * @return Validates the contract on correct
   */
  @Override
  public Message validate(Contract contract) {
    Message message = new Message();
    LocalDate startDate = contract.getStartDate();
    LocalDate endDate = contract.getEndDate();

    LocalDate currentDate = LocalDate.now();
    if (currentDate.compareTo(startDate) < 0) {
      message.setStatus(Status.ERROR);
      message.setMessage("Invalid date. The start day of the contract cannot be greater than " +
              "the current day");
      return message;

    } else if (currentDate.compareTo(endDate) == 0) {
      message.setStatus(Status.WARNING);
      message.setMessage("Contract expires today");
      return message;
    } else if (currentDate.compareTo(endDate) > 0) {
      message.setStatus(Status.WARNING);
      message.setMessage("Contract expired");
      return message;

    } else if (startDate.compareTo(endDate) >= 0) {
      message.setStatus(Status.ERROR);
      message.setMessage("Invalid dates. The start day of the contract cannot be greater than" +
              " the end date or equal to it");
      return message;
    } else {
      message.setStatus(Status.OK);
      return message;
    }

  }
}
