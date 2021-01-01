package com.netcracker.validators;


/**
 * Represents an info of validation.
 *
 * @author Vlad Kotov
 */
public class Message {

  private String message;
  private Status status;

  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }



}
