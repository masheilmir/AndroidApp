package com.b07.users;

public class Customer extends User {
	
	
  /**
   * This is a customer class to extend User
   * 
   * @param id the user's id
   * @param name the user's name
   * @param age the user's age
   * @param address the user's address
   */
  public Customer(int id, String name, int age, String address) {
    super(id, name, age, address);
  }

  /**
   * This is a customer class to extend User
   * @param id the user's id
   * @param name the user's name
   * @param age the user's age
   * @param address the user's address
   * @param authenticated authenticate users
   */
  public Customer(int id, String name, int age, String address, boolean authenticated) {
    super(id, name, age, address, authenticated);
  }
}
