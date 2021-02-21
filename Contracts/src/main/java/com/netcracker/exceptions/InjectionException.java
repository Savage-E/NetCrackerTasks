package com.netcracker.exceptions;

public class InjectionException extends Exception{
  public InjectionException(Throwable cause) {
    super(cause);
  }

  public InjectionException(String message) {
    super(message);
  }
}
