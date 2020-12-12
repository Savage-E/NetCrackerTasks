package com.netcracker.validators;

/**
 * Interface provides validate method.
 *
 * @author Vlad Kotov
 */
public interface Validator <T>{
  /**
   * Validates the contract on specified condition.
   *
   * @param data the data to validate
   * @return the message with info
   */
   Message validate(T data);
}
