package view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Database;

public class HomePageView {
	 private Stage primaryStage;
	 private Database db;
	 
	//input new product
	private ToggleButton toggleButton = new ToggleButton("Add Expense");
	private Button addButton = new Button("Add Expense");
	Label labelName = new Label("Input expense Name:");
    TextField fieldName = new TextField();
    Label labelPrice = new Label("Input expense Price:");
    TextField fieldPrice = new TextField();
    TextArea fieldDescription = new TextArea();
    Connection connection;  

	public HomePageView(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		this.primaryStage = primaryStage;
        this.db = new Database();
	}
	 
	
	public void show() {

	Label labelDescription = new Label("Enter expense Description:");
	fieldDescription.setPromptText("Enter text here");
	fieldDescription.setPrefColumnCount(20);
	fieldDescription.setPrefRowCount(5);
 

	primaryStage.setTitle("Simpan uang");

	//input box
	VBox inputBox = new VBox(10); // 10 is the spacing between elements
	inputBox.getChildren().addAll(labelName, fieldName, labelPrice, fieldPrice,labelDescription,fieldDescription);
 

	addButton.setOnAction(event-> {
		System.out.println("button clicked");
		//insertProduct();
	});
	
	//sideBar
	VBox sideBar = new VBox(10); // 10 is the spacing between elements
	sideBar.getChildren().addAll(
		new Label("Welcome, user!"),
	    new Label("Add your expense"),
	    toggleButton
	);
	
	toggleButton.setOnAction(event -> {
		if(toggleButton.isSelected()) {
			sideBar.getChildren().add(inputBox);
			inputBox.getChildren().add(addButton);
	        toggleButton.setText("Back");
		}else {
			sideBar.getChildren().remove(inputBox);
			inputBox.getChildren().remove(addButton);
	        toggleButton.setText("Add Expense");
		}
	
	});

		//main bar
		HBox root = new HBox(10);
		root.getChildren().addAll( sideBar);
		
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
}

 

void insertProduct() throws SQLException {
  //belom masukin productID
    String name = fieldName.getText();
    double price = Double.parseDouble(fieldPrice.getText());
    String description= fieldDescription.getText();
    
    String insertSQL = "INSERT INTO product (product_name,  product_price,product_description) VALUES (?, ?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
        preparedStatement.setString(1, name);
        preparedStatement.setDouble(2, price);
        preparedStatement.setString(3, description);

        // Execute the insert statement
        preparedStatement.executeUpdate();

        System.out.println("Product added successfully!");
    }
    
    //clear the input
    fieldName.clear();
    fieldPrice.clear();
    fieldDescription.clear();
    
}

}








 




