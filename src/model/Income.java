package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Income {
//	 IncomeID VARCHAR(5) PRIMARY KEY,
//    Name VARCHAR(255),
//    TotalIncome VARCHAR(255),
//    DateIncome VARCHAR(255),
//    NoteIncome VARCHAR(255)
//belom masukin Income/Outcome ID
	String Name;	
	double TotalIncome;
	String DateIncome;
	String NoteIncome;
	
	 private static Database db=new Database();
	 static Connection connection=db.getConnection(); 
	
	public Income() {
	}
	
	public static void retreiveRecord() {
//		String query = "SELECT IncomeID,Name,TotalIncome,DateIncome,NoteIntcome FROM your_table";
//		try (Statement statement = connection.createStatement()) {
//		    ResultSet resultSet = statement.executeQuery(query);
//		    while (resultSet.next()) {
//		        DataModel data = new DataModel(resultSet.getString("column1"), resultSet.getString("column2"));
//		        tableView.getItems().add(data);
//		    }
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
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
}

