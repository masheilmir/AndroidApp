package com.b07.users;

import android.content.Context;

import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.security.PasswordHelpers;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class User implements Serializable {
  private int id;
  private String name;
  private int age;
  private String address;
  private int roleId;
  private boolean authenticated;

  /**
   * This is an abstract class to show all users
   * 
   * @param id the user's id
   * @param name the user's name
   * @param age the user's age
   * @param address the user's address
   */
  public User(int id, String name, int age, String address) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
  }

  /**
   * This is an abstract class to show all users
   * 
   * @param id the user's id
   * @param name the user's name
   * @param age the user's age
   * @param address the user's address
   * @param authenticated authenticate users
   */
  public User(int id, String name, int age, String address, boolean authenticated) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
    this.authenticated = authenticated;
  }

  /**
   * 
   * @return the user's id
   */
  public int getId() {
    return id;
  }

  /**
   * 
   * @param id the new user's id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * 
   * @return the user's name
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * @param name the new user's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 
   * @return the user's age
   */
  public int getAge() {
    return age;
  }

  /**
   * 
   * @param age the new user's age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * 
   * @return the user's address
   */
  public String getAddress() {
    return address;
  }

  /**
   * 
   * @param address the new user's address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * 
   * @return the role's id
   */
  public int getRoleId() {
    return roleId;
  }

  /**
   * check whether the user's password is authenticated or not
   * 
   * @param password the new user's password
   * @return true if the user's password is authenticated, false if not
   */
  public final boolean authenticate(String password, Context context) {
    DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(context);
    this.authenticated =
          PasswordHelpers.comparePassword(select.getPasswordAndroid(this.id), password);
    return this.authenticated;
  }

  /**
   * 
   * @return true if the user's password is authenticated, false if not
   */
  public boolean isAuthenticated() {
    return this.authenticated;
  }
}
