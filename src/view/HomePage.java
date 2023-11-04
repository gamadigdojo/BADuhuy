package view;

import java.sql.SQLException;
import model.Record;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import components.Navbar;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class HomePage {
 
	Button addRecord=new Button("Add record");
	Label balance=new Label("Balance: 0");
	 ArrayList<Income> incomeList=Income.retreiveRecord();
     ArrayList<Outcome> outcomeList=Outcome.retreiveRecord();    
     ArrayList<Record> combinedRecords= new ArrayList<>();
 
	public Scene createHomeScene() {
		BorderPane root = new BorderPane();
		VBox scrollRoot=new VBox();
		ScrollPane scrollContent = new ScrollPane(scrollRoot);
		scrollContent.setFitToWidth(true); 
		
		Navbar navbar = new Navbar();
		HBox navigationBar = navbar.createNavbar();
		scrollRoot.getChildren().add(navigationBar);
		
		HBox header=createHeader();
		HBox footer=createFooter();
        
      
		VBox recordList=new VBox(20); //root center layout
        recordList.setPadding(new Insets(50, 80, 100, 80));
        recordList.getChildren().add(header);
        insertAndSortRecord();
        //output
        for(Record item:combinedRecords) {
        	HBox incomeBox=new HBox(15);
        	if(item instanceof Income) {
        		incomeBox.getChildren().addAll(
            			new Label("(+)"),
            			new Label(item.getName()),
            			new Label(item.getTotal().toString()),
            			new Label(item.getDate())
            			);
        	}else if(item instanceof Outcome) {
        		incomeBox.getChildren().addAll(
            			new Label("(-)"),
            			new Label(item.getName()),
            			new Label(item.getTotal().toString()),
            			new Label(item.getDate())
            			);
        	}
            incomeBox.getStyleClass().add("homeIncomeBox");
        	recordList.getChildren().add(incomeBox);
        }
        scrollRoot.getChildren().add(recordList);
        
      
        
        //----------------SETUP-----------------//
        root.setCenter(scrollContent);
        root.setBottom(footer);
        
        //add external css
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
    }
	
	public void insertAndSortRecord() {
		for (Income income : incomeList) {
            combinedRecords.add(income);
        }

        for (Outcome outcome : outcomeList) {
            combinedRecords.add(outcome);
        }
        
        int n = combinedRecords.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Record record1 = combinedRecords.get(j);
                Record record2 = combinedRecords.get(j + 1);
                if (record1.getDate().compareTo(record2.getDate()) > 0) {
                    // Swap the records if they are out of order
                    combinedRecords.set(j, record2);
                    combinedRecords.set(j + 1, record1);
                }
            }
        }
	}
	
	public HBox createFooter(){
		  HBox footer=new HBox(); //root balance& addrecord
	        addRecord.setOnAction(event-> {
	        	moveAddRecord();
	    	});
	        footer.getChildren().addAll(
	        		balance,
	        		addRecord
	        		);
	        footer.getStyleClass().add("homeRecord"); //adding style to record;
		return footer;
	}
	
	 public HBox createHeader() {
		  HBox header = new HBox();  //root header box
	        VBox goalBar=new VBox();  
	        TextField inputBar = new TextField();
	        inputBar.getStyleClass().add("goalInput");
	        goalBar.getChildren().addAll(new Label("What is your main goal in using Piggy Pocket?"), inputBar);
	        goalBar.getStyleClass().add("goalBar");      
	        Image image = new Image("/images/logo.png"); // Adjust the path to your image.
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(100); // Set the desired width
	        imageView.setFitHeight(100); // Set the desired height
	        HBox.setHgrow(goalBar, javafx.scene.layout.Priority.ALWAYS);
	        
	        header.getChildren().addAll(imageView,goalBar); 
	        header.getStyleClass().add("homeHeader"); //adding style to header
	        


	        return header;
	    }
	
	void moveAddRecord() {
		Scene AddRecordScene = new AddRecord().createAddScene();
        SharedStageHolder.getPrimaryStage().setScene(AddRecordScene);
	}
}