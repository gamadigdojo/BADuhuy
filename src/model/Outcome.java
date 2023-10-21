package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Outcome extends Database{
	private final String OutcomeID;
    private final String Name;
    private final Double TotalOutcome;
    private final String DateOutcome;
	
	public Outcome(String OutcomeID, String Name, Double TotalOutcome, String DateOutcome) {
		super();
		 this.OutcomeID = OutcomeID;
	     this.Name = Name;
	     this.TotalOutcome = TotalOutcome;
	     this.DateOutcome = DateOutcome;
	}
	
	public static ArrayList<Outcome> retreiveRecord() {
		ArrayList<Outcome> outcomes=new ArrayList<>();
		
		String query = "SELECT OutcomeID,Name,TotalOutcome,DateOutcome FROM Outcome";
		try (PreparedStatement preparedStatement = Database.connection.prepareStatement(query)) {
		    ResultSet resultSet = preparedStatement.executeQuery();

		    while (resultSet.next()) {
		    	String OutcomeID = resultSet.getString("OutcomeID");
		    	String Name = resultSet.getString("Name");
		    	Double TotalOutcome = resultSet.getDouble("TotalOutcome");
		    	String DateOutcome = resultSet.getString("DateOutcome");
		        
		        // Create a new ArrayList to store the data
		        Outcome outcome=new Outcome(OutcomeID,Name,TotalOutcome,DateOutcome);
		        outcomes.add(outcome);
		        
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return outcomes;
	}

	 
	
	public static void insertRecord(String Name,double TotalOutcome, String DateOutcome) throws SQLException {
	    	  String insertSQL = "INSERT INTO Outcome (Name,  TotalOutcome,DateOutcome) VALUES (?, ?, ?)";

	          try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
	              preparedStatement.setString(1, Name);
	              preparedStatement.setDouble(2, TotalOutcome);
	              preparedStatement.setString(3, DateOutcome);

	              // Execute the insert statement
	              preparedStatement.executeUpdate();

	              System.out.println("Product added successfully!");
	          }
		    }

	public String getOutcomeID() {
		return OutcomeID;
	}

	public String getName() {
		return Name;
	}

	public Double getTotalOutcome() {
		return TotalOutcome;
	}

	public String getDateOutcome() {
		return DateOutcome;
	}
	
	
	 
	
	}

 