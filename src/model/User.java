package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	PreparedStatement asd;
	
	public User(String firstName,String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 public static User validateUser(String email, String password) {
			String query = "SELECT * FROM MsUser WHERE email = ? AND password = ?";

	        try (PreparedStatement preparedStatement =  Database.connection.prepareStatement(query)) {
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, password);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            	if (resultSet.next()) {
	                    // If a user with provided credentials exists, return the User object
	                    String firstName = resultSet.getString("firstName");
	                    String lastName = resultSet.getString("lastName");
	                    return new User(firstName,lastName, email, password);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception appropriately
 	        }
	        
	        return null;
	    }
}
