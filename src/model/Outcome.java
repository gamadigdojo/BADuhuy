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
	private final SimpleStringProperty OutcomeID;
    private final SimpleStringProperty Name;
    private final SimpleDoubleProperty TotalOutcome;
    private final SimpleStringProperty DateOutcome;
    private final SimpleStringProperty NoteOutcome;
	
    
	
	public Outcome(String OutcomeID, String Name, Double TotalOutcome, String DateOutcome, String NoteOutcome) {
		super();
		 this.OutcomeID = new SimpleStringProperty(OutcomeID);
	     this.Name = new SimpleStringProperty(Name);
	     this.TotalOutcome = new SimpleDoubleProperty(TotalOutcome);
	     this.DateOutcome = new SimpleStringProperty(DateOutcome);
	     this.NoteOutcome = new SimpleStringProperty(NoteOutcome);
	}
	
	public static ArrayList<Outcome> retreiveRecord() {
		ArrayList<Outcome> outcomes=new ArrayList<>();
		
		String query = "SELECT OutcomeID,Name,TotalOutcome,DateOutcome,NoteOutcome FROM Outcome";
		try (PreparedStatement preparedStatement = Database.connection.prepareStatement(query)) {
		    ResultSet resultSet = preparedStatement.executeQuery();

		    while (resultSet.next()) {
		    	String OutcomeID = resultSet.getString("OutcomeID");
		    	String Name = resultSet.getString("Name");
		    	Double TotalOutcome = resultSet.getDouble("TotalOutcome");
		    	String DateOutcome = resultSet.getString("DateOutcome");
		    	String NoteOutcome = resultSet.getString("NoteOutcome");
		        
		        // Create a new ArrayList to store the data
		        Outcome outcome=new Outcome(OutcomeID,Name,TotalOutcome,DateOutcome,NoteOutcome);
		        outcomes.add(outcome);
		        
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return outcomes;
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

	@Override
	public SimpleStringProperty idProperty() {
		// TODO Auto-generated method stub
		return OutcomeID;
	}

	@Override
	public SimpleStringProperty name() {
		// TODO Auto-generated method stub
		return Name;
	}

	@Override
	public SimpleDoubleProperty total() {
		// TODO Auto-generated method stub
		return TotalOutcome;
	}

	@Override
	public SimpleStringProperty date() {
		// TODO Auto-generated method stub
		return DateOutcome;
	}

	@Override
	public SimpleStringProperty note() {
		// TODO Auto-generated method stub
		return NoteOutcome;
	}

	@Override
	public ObservableValue<Double> totalObservable() {
		// TODO Auto-generated method stub
	      return new SimpleObjectProperty<>(TotalOutcome.get());
	}
	
	
	 
	}

