package com.netcracker.validators;

import com.netcracker.entities.Contract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Vlad Kotov
 */
public class FioValidator implements Validator<Contract> {
  /**
   * Validates the contract on correct full name.
   *
   * @param contract the contract to validate
   * @return the message with result of validation
   */
  @Override
  public Message validate(Contract contract) {
    Message message = new Message();
    String[] fio = contract.getPerson().getFio().split(" ");

    Pattern pattern = Pattern.compile("/^[a-z ,.'-]+$/i");
    Matcher matcher;
    for (String s : fio) {
      matcher = pattern.matcher(s);
      if (!matcher.matches()) {
        message.setMessage("Invalid FIO");
        message.setStatus(Status.ERROR);
        return message;
      }
    }
    message.setStatus(Status.OK);
    return message;
  }

}
