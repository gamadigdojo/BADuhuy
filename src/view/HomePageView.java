package view;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Database;
import model.Income;
import model.Outcome;
import model.SharedStageHolder;

public class HomePageView {
 
	Button addRecord=new Button("Add record");
	Label balance=new Label("Balance: 0");
 

	public HomePageView() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Scene createHomeScene() {
		BorderPane root = new BorderPane();
		 
        
        
        //------------------Header--------------------------//
        HBox header = new HBox();  //root header box
        VBox goalBar=new VBox();  
        Label headerLabel = new Label("what's your main goal today");
        TextField inputBar = new TextField();
        goalBar.getChildren().addAll(new Label("Hello, user"),headerLabel, inputBar);
        
        Image image = new Image("/images/logo.png"); // Adjust the path to your image.
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the desired width
        imageView.setFitHeight(100); // Set the desired height
        
        
        header.getChildren().addAll(imageView,goalBar); 
        header.getStyleClass().add("homeHeader"); //adding style to header
        
        //-----------------Balance & Addrecord------------------//
        HBox record=new HBox(); //root balance& addrecord
        addRecord.setOnAction(event-> {
        	moveAddRecord();
    	});
        record.getChildren().addAll(
        		balance,
        		addRecord
        		);
        record.getStyleClass().add("homeRecord"); //adding style to record;
        //------------------Center Layout------------------//
        VBox recordList=new VBox(20); //root center layout

        ArrayList<Income> incomeList=Income.retreiveRecord();
        ArrayList<Outcome> outcomeList=Outcome.retreiveRecord();
        
      
        recordList.setPadding(new Insets(50, 80, 100, 80));
        recordList.getChildren().add(header);
        recordList.getChildren().add(record);
        
        for(Income income: incomeList) {
        	HBox incomeBox=new HBox(15);
        	incomeBox.getChildren().addAll(
        			new Label("(+)"),
        			new Label(income.getName()),
        			new Label(income.getTotalIncome().toString()),
        			new Label(income.getDateIncome())
        			);
            incomeBox.getStyleClass().add("homeIncomeBox");
        	recordList.getChildren().add(incomeBox);
        }
        
        for(Outcome outcome: outcomeList) {
        	HBox outcomeBox=new HBox(15);
        	outcomeBox.getChildren().addAll(
        			new Label("(-)"),
        			new Label(outcome.getName()),
        			new Label(outcome.getTotalOutcome().toString()),
        			new Label(outcome.getDateOutcome())
        			);
            outcomeBox.getStyleClass().add("homeIncomeBox");
        	recordList.getChildren().add(outcomeBox);
        }
       
        
     
        
        //----------------SETUP-----------------//
        root.setCenter(recordList);
        
        //add external css
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
    }
	
	void moveAddRecord() {
		Scene AddRecordScene = new AddRecord().createAddScene();
        SharedStageHolder.getPrimaryStage().setScene(AddRecordScene);
	}
}