package com.b07.database.helper;

import com.b07.users.*;
import com.b07.exceptions.*;

public class InputValidator {
	/**
	 * Check if the input is valid
	 */
  public static boolean checkRoles(String message) {
    try {
      Roles.valueOf(message.toUpperCase());
      return true;
    } catch (IllegalArgumentException ex) {
      return false;
    }

  }

}
