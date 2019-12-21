package com.b07.exceptions;

public class InvalidInputException extends Exception {
  /**
   * serialID for invalid input exceptions.
   */
  private static final long serialVersionUID = -4209696969420420L;

  /**
   * An exception for invalid input
   * 
   * @param message
   */
  public InvalidInputException(String message) {
    super(message);
  }

}
