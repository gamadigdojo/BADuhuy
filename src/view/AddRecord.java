package view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Database;
import model.Income;
import model.Outcome;
import model.SharedStageHolder;

public class AddRecord {
	  
	
	 
 	private ToggleButton toggleButton = new ToggleButton("Add new record");
	
	//input field
	Label labelName = new Label("Input Name:");
    TextField fieldName = new TextField();
    Label labelPrice = new Label("Input Total Income/Outcome:");
    TextField fieldPrice = new TextField();
    Label labelDate = new Label("Input date:");
    TextField fieldDate = new TextField();
    TextArea fieldDescription = new TextArea();
    ComboBox<String> comboBox = new ComboBox<>();
    private Button addButton = new Button("Add new record");
   

	public AddRecord() {
      
	}
	 
	public Scene createAddScene() {
		//-------------------NAVBAR------------------------//
        Navbar navbar = new Navbar();
        navbar.getHomeButton().setOnAction(event -> {
            // Create and set the home scene
            Scene homeScene = new HomePageView().createHomeScene();
            SharedStageHolder.getPrimaryStage().setScene(homeScene); // Access the primary stage
        });
        navbar.getProfileButton().setOnAction(event -> {
            // Create and set the profile view scene
            Scene profileScene = new ProfileView().createProfileScene();
            SharedStageHolder.getPrimaryStage().setScene(profileScene); // Access the primary stage
        });
		//-------------------NAVBAR------------------------//
        BorderPane root = new BorderPane();
        
        Label labelDescription = new Label("Enter Note Income/Outcome:");
    	fieldDescription.setPromptText("Enter text here");
    	fieldDescription.setPrefColumnCount(20);
    	fieldDescription.setPrefRowCount(5);
    	comboBox.getItems().addAll("Income", "Outcome");
    	comboBox.setValue("Income");
        
        //------------------CENTER LAYOUT-----------------//
    	VBox inputBox = new VBox(10); // 10 is the spacing between elements
    	inputBox.getChildren().addAll(
    			comboBox,labelName, fieldName, labelPrice, fieldPrice,labelDate,fieldDate,labelDescription,fieldDescription
    			);
    	addButton.setOnAction(event-> {
    		try {
    			insertProduct();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	});
    	
    	VBox display = new VBox(10); // 10 is the spacing between elements
    	display.getChildren().addAll(
    		new Label("Welcome, user!"),
    	    new Label("Add your income/outcome"),
    	    toggleButton
    	);
    	
    	toggleButton.setOnAction(event -> {
    		if(toggleButton.isSelected()) {
    			display.getChildren().add(inputBox);
    			inputBox.getChildren().add(addButton);
    	        toggleButton.setText("Back");
    		}else {
    			display.getChildren().remove(inputBox);
    			inputBox.getChildren().remove(addButton);
    	        toggleButton.setText("Add new record");
    		}
    	
    	});

    	HBox spacing=new HBox(10);
    	spacing.getChildren().addAll(display);
        
        root.setTop(navbar);
        root.setCenter(spacing);
//        root.setBottom(footer);
        return new Scene(root, 700, 500);
	}
	

void insertProduct() throws SQLException {
//	 IncomeID VARCHAR(5) PRIMARY KEY,
//	    Name VARCHAR(255),
//	    TotalIncome VARCHAR(255),
//	    DateIncome VARCHAR(255),
//	    NoteIncome VARCHAR(255)
  //belom masukin Income/Outcome ID
	
	String name = fieldName.getText();
    double total = Double.parseDouble(fieldPrice.getText());
    String date=fieldDate.getText();
    String note = fieldDescription.getText();
	
	 String type=comboBox.getValue();
	    if(type.equals("Income")) {
	    	Income.insertRecord(name,total, date ,note);
	    }else if(type.equals("Outcome")) {
	    	Outcome.insertRecord(name, total, date, note);
	    }
	
    fieldName.clear();
    fieldDate.clear();
    fieldPrice.clear();
    fieldDescription.clear();
    
}

}








 




