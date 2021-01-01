package com.netcracker.validators;

import com.netcracker.entities.Contract;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Represents validation for full name.
 *
 * <p>Please see the {@link com.netcracker.validators.Validator} interface for true identity
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

    String fio = contract.getPerson().getFio();

    Pattern pattern = Pattern.compile("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
    Matcher matcher;
    matcher = pattern.matcher(fio);

    if (!matcher.matches()) {
      message.setMessage("Invalid FIO");
      message.setStatus(Status.ERROR);
      return message;
    }

    message.setStatus(Status.OK);
    return message;
  }

}
