package com.netcracker.validators;

import com.netcracker.entities.Contract;

/**
 *Represents validation for age.
 *@author Vlad Kotov
 */
public class AgeValidator implements Validator<Contract> {
  /**
   * Validates the contract on the correct age.
   *
   * @param contract the contract to validate
   * @return the message with result of validation
   */
  @Override
  public Message validate(Contract contract) {

    Message message = new Message();
    int age = contract.getPerson().getAge();
    if (age > 14 && age <= 85) {
      message.setStatus(Status.OK);
      return message;
    } else if (age > 85 && age < 120) {
      message.setStatus(Status.WARNING);
      message.setMessage("The age of the person is too great perhaps there is a mistake");
      return message;
    } else {
      message.setMessage("Invalid age of the person."
              + " Age cannot be less than 14 and greater than 120");
      message.setStatus(Status.ERROR);
      return message;
    }
  }
}
