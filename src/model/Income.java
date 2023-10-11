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

public class Income extends Database{
//	 IncomeID VARCHAR(5) PRIMARY KEY,
//    Name VARCHAR(255),
//    TotalIncome VARCHAR(255),
//    DateIncome VARCHAR(255),
//    NoteIncome VARCHAR(255)
//belom masukin Income/Outcome ID
	
	private final SimpleStringProperty IncomeID;
    private final SimpleStringProperty Name;
    private final SimpleDoubleProperty TotalIncome;
    private final SimpleStringProperty DateIncome;
    private final SimpleStringProperty NoteIncome;
	
	
	 
	public Income(String IncomeID, String Name, double TotalIncome, String DateIncome, String NoteIncome) {
		super();
		 this.IncomeID = new SimpleStringProperty(IncomeID);
	     this.Name = new SimpleStringProperty(Name);
	     this.TotalIncome = new SimpleDoubleProperty(TotalIncome);
	     this.DateIncome = new SimpleStringProperty(DateIncome);
	     this.NoteIncome = new SimpleStringProperty(NoteIncome);
	}
	
	public static ArrayList<Income> retreiveRecord() {
		ArrayList<Income> incomes=new ArrayList<>();
		
		String query = "SELECT IncomeID,Name,TotalIncome,DateIncome,NoteIncome FROM Income";
		try (PreparedStatement preparedStatement = Database.connection.prepareStatement(query)) {
		    ResultSet resultSet = preparedStatement.executeQuery();

		    while (resultSet.next()) {
		    	String IncomeID = resultSet.getString("IncomeID");
		    	String Name = resultSet.getString("Name");
		    	Double TotalIncome = resultSet.getDouble("TotalIncome");
		    	String DateIncome = resultSet.getString("DateIncome");
		    	String NoteIncome = resultSet.getString("NoteIncome");
		        
		        // Create a new ArrayList to store the data
		        Income income=new Income(IncomeID,Name,TotalIncome,DateIncome,NoteIncome);
		        incomes.add(income);
		        
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return incomes;
	}
	
	public static void insertRecord(String Name,double TotalIncome, String DateIncome,String NoteIncome) throws SQLException {
	    
        String insertSQL = "INSERT INTO Income (Name,  TotalIncome,DateIncome,NoteIncome) VALUES (?, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, Name);
            preparedStatement.setDouble(2, TotalIncome);
            preparedStatement.setString(3, DateIncome);
            preparedStatement.setString(4, NoteIncome);

            // Execute the insert statement
            preparedStatement.executeUpdate();

            System.out.println("Product added successfully!");
        }
	 }

	@Override
	public SimpleStringProperty idProperty() {
		// TODO Auto-generated method stub
		return IncomeID;
	}

	@Override
	public SimpleStringProperty name() {
		// TODO Auto-generated method stub
		return Name;
	}

	@Override
	public SimpleDoubleProperty total() {
		// TODO Auto-generated method stub
		return TotalIncome;
	}

	@Override
	public SimpleStringProperty date() {
		// TODO Auto-generated method stub
		return DateIncome;
	}

	@Override
	public SimpleStringProperty note() {
		// TODO Auto-generated method stub
		return NoteIncome;
	}

	@Override
	public ObservableValue<Double> totalObservable() {
		// TODO Auto-generated method stub
      return new SimpleObjectProperty<>(TotalIncome.get());
	}
	
	

	 
 
	
}

