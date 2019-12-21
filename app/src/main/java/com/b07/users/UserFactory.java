package com.b07.users;

public class UserFactory {
	
	/**
	 * This is a class to make users
	 * 
	 * @param userType the user's type
	 * @param id the user id
	 * @param name the user's name
	 * @param age the user's age
	 * @param address the user's address
	 * @return admin
	 * @return employee
	 * @return customer
	 */
	public static User makeUser(String userType, int id, String name, int age, String address) {
		if(userType.equals("Admin")) {
			return new Admin(id, name, age, address);
		}else if(userType.equals("Employee")) {
			return new Employee(id, name, age, address);
		}else {
			return new Customer(id, name, age, address);
		}
	}
	
	
	/**
	 * This is a class to make users
	 * 
	 * @param userType the user's type
	 * @param id the user id
	 * @param name the user's name
	 * @param age the user's age
	 * @param address the user's address
	 * @param authenticated to authenticate users
	 * @return admin
	 * @return employee
	 * @return customer
	 */
	public static User makeUser(String userType, int id, String name, int age, String address, boolean authenticated) {
		if(userType.equals("Admin")) {
			return new Admin(id, name, age, address, authenticated);
		}else if(userType.equals("Employee")) {
			return new Employee(id, name, age, address, authenticated);
		}else {
			return new Customer(id, name, age, address, authenticated);
		}
	}


}
