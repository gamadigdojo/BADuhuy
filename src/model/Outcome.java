package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Outcome {
//	 OutcomeID VARCHAR(5) PRIMARY KEY,
//  Name VARCHAR(255),
//  TotalOutcome VARCHAR(255),
//  DateOutcome VARCHAR(255),
//  NoteOutcome VARCHAR(255)
//belom masukin Income/Outcome ID
	String Name;	
	double TotalOutcome;
	String DateOutcome;
	String NoteOutcome;
	
	 private static Database db=new Database();
	 static Connection connection=db.getConnection(); 
	 

	public Outcome() {
		// TODO Auto-generated constructor stub
		 
	}
	
	public static void retreiveRecord() {
		
	}
	
	public static void insertRecord(String Name,double TotalOutcome, String DateOutcome, String NoteOutcome) throws SQLException {
	    	  String insertSQL = "INSERT INTO Outcome (Name,  TotalOutcome,DateOutcome,NoteOutcome) VALUES (?, ?, ?,?)";

	          try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
	              preparedStatement.setString(1, Name);
	              preparedStatement.setDouble(2, TotalOutcome);
	              preparedStatement.setString(3, DateOutcome);
	              preparedStatement.setString(4, NoteOutcome);

	              // Execute the insert statement
	              preparedStatement.executeUpdate();

	              System.out.println("Product added successfully!");
	          }
		    }
	}

