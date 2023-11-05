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

public class Income extends Record{
	
	private final String IncomeID;
	public Income(String IncomeID,String name, Double total, String date) {
		super(name, total, date);
		this.IncomeID=IncomeID;
	}
	
	public static ArrayList<Income> retreiveRecord() {
		ArrayList<Income> incomes=new ArrayList<>();
		
		String query = "SELECT IncomeID,Name,TotalIncome,DateIncome FROM Income";
		try (PreparedStatement preparedStatement = Database.connection.prepareStatement(query)) {
		    ResultSet resultSet = preparedStatement.executeQuery();

		    while (resultSet.next()) {
		    	String IncomeID = resultSet.getString("IncomeID");
		    	String Name = resultSet.getString("Name");
		    	Double TotalIncome = resultSet.getDouble("TotalIncome");
		    	String DateIncome = resultSet.getString("DateIncome");
		        
		        // Create a new ArrayList to store the data
		        Income income=new Income(IncomeID,Name,TotalIncome,DateIncome);
		        incomes.add(income);
		        
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return incomes;
	}
	
	public static void insertRecord(String Name,double TotalIncome, String DateIncome) throws SQLException {
	    
        String insertSQL = "INSERT INTO Income (Name,  TotalIncome,DateIncome) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, Name);
            preparedStatement.setDouble(2, TotalIncome);
            preparedStatement.setString(3, DateIncome);

            // Execute the insert statement
            preparedStatement.executeUpdate();

            System.out.println("Product added successfully!");
        }
	 }

	public String getIncomeID() {
		return IncomeID;
	}

	 
 
	
}

 
