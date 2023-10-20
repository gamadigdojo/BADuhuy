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
	
	private final String IncomeID;
    private final String Name;
    private final Double TotalIncome;
    private final String DateIncome;
    private final String NoteIncome;
	
	
	 
	public Income(String IncomeID, String Name, double TotalIncome, String DateIncome, String NoteIncome) {
		super();
		 this.IncomeID = IncomeID;
	     this.Name = Name;
	     this.TotalIncome = TotalIncome;
	     this.DateIncome = DateIncome;
	     this.NoteIncome = NoteIncome;
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

	public String getIncomeID() {
		return IncomeID;
	}

	public String getName() {
		return Name;
	}

	public Double getTotalIncome() {
		return TotalIncome;
	}

	public String getDateIncome() {
		return DateIncome;
	}

	public String getNoteIncome() {
		return NoteIncome;
	}

	

	 
	
	

	 
 
	
}

