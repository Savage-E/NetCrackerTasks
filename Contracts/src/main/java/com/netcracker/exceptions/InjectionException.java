package com.netcracker.exceptions;

/**
 * Exception class for handling exceptions of the Injector.
 */
public class InjectionException extends Exception{
  public InjectionException(Throwable cause) {
    super(cause);
  }

  public InjectionException(String message) {
    super(message);
  }
}
