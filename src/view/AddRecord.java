package view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    ComboBox<String> comboBox = new ComboBox<>();
    private Button addButton = new Button("Add new record");
    
    DatePicker datePicker = new DatePicker();

    // Define a custom date format using DateTimeFormatter
    DateTimeFormatter customDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   

	public AddRecord() {
      
	}
	 
	public Scene createAddScene() {
		BorderPane root = new BorderPane();
        
        //------------------CENTER LAYOUT-----------------//
        VBox display = new VBox(10); //center layout root
        
        Label labelDescription = new Label("Enter Note Income/Outcome:");
    	comboBox.getItems().addAll("Income", "Outcome");
    	comboBox.setValue("Income");
    	
        datePicker.setValue(LocalDate.now());
    	
    	VBox inputBox = new VBox(10); // 10 is the spacing between elements
    	inputBox.getChildren().addAll(
    			comboBox,labelName, fieldName, labelPrice, fieldPrice,labelDate,datePicker
    			);
    	addButton.setOnAction(event-> {
    		try {
    			insertProduct();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	});
    	
    	 // 10 is the spacing between elements
    	display.getChildren().addAll(
    		new Label("Welcome, user!"),
    	    new Label("Add your income/outcome"),
    	    inputBox,
    	    addButton
    	);
    	
    	 
       
    	
    	display.getStyleClass().add("addRecordDisplay"); //adding style

    	//-----------------------------SETUP------------------------------
    	Scene scene = new Scene(root, 700, 500);
        root.setCenter(display);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
	}
	

void insertProduct() throws SQLException {
	
	LocalDate selectedDate = datePicker.getValue();
	String name = fieldName.getText();
    double total = Double.parseDouble(fieldPrice.getText());
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
    String formattedDate = selectedDate.format(dateFormatter);
	
	 String type=comboBox.getValue();
	    if(type.equals("Income")) {
	    	Income.insertRecord(name,total, formattedDate);
	    }else if(type.equals("Outcome")) {
	    	Outcome.insertRecord(name, total, formattedDate);
	    }
	
    fieldName.clear();
    datePicker.setValue(LocalDate.now());
    fieldPrice.clear();    
    
	Scene HomePageScene= new HomePageView().createHomeScene();
    SharedStageHolder.getPrimaryStage().setScene(HomePageScene);
}

}








 




