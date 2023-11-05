package view;

import java.sql.SQLException;
import model.Record;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class HomePage {
 
	Button addRecord=new Button("Add record");
	Label balance=new Label("Balance: 0");
	 ArrayList<Income> incomeList=Income.retreiveRecord();
     ArrayList<Outcome> outcomeList=Outcome.retreiveRecord();    
     ArrayList<Record> combinedRecords= new ArrayList<>();
 
   
     
     VBox recordList=new VBox(10); //root center layout
     HBox filter=filterButton();
     String filterSorting="Descending";
     String filterType="All";
 
	public Scene createHomeScene() {
		BorderPane root = new BorderPane();
		VBox scrollRoot=new VBox(); //adding scrollable content
		ScrollPane scrollContent = new ScrollPane(scrollRoot);
		scrollContent.setFitToWidth(true); 
		
		Navbar navbar = new Navbar();
		HBox navigationBar = navbar.createNavbar();
		scrollRoot.getChildren().add(navigationBar);
		
		HBox footer=createFooter();
	    recordList.setPadding(new Insets(50, 80, 100, 80));
		
		

      
        resetRecord();
        insertAndSortRecord("Descending");
        printRecord("All");
        scrollRoot.getChildren().add(recordList);
        
      
        
        //----------------SETUP-----------------//
        root.setCenter(scrollContent);
        root.setBottom(footer);
        
        //add external css
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
    }
	
	
	public void resetRecord() {

	     HBox header=createHeader();
	     
	    combinedRecords.clear();
		recordList.getChildren().clear();
        recordList.getChildren().add(header);
        recordList.getChildren().add(filter);
	}
	
	public HBox filterButton() {
		HBox filter=new HBox();
		
		  //filter button
		ComboBox<String> sortingOrderComboBox = new ComboBox<>();
	    sortingOrderComboBox.getItems().addAll("Ascending", "Descending");
	    sortingOrderComboBox.setValue("Descending");

	    sortingOrderComboBox.setOnAction(event -> {
            filterSorting = sortingOrderComboBox.getValue();
            // You can perform actions based on the selected option here
            resetRecord();
            insertAndSortRecord(filterSorting);
            printRecord(filterType);

        });
	    
	    ComboBox<String> sortingTypeComboBox = new ComboBox<>();
	    sortingTypeComboBox.getItems().addAll("All", "Outcome", "Income");
	    sortingTypeComboBox.setValue("All");
	    
	    sortingTypeComboBox.setOnAction(event -> {
            filterType = sortingTypeComboBox.getValue();
            resetRecord();
            insertAndSortRecord(filterSorting);
            printRecord(filterType);
        });
        filter.getChildren().addAll(sortingOrderComboBox,sortingTypeComboBox);

        return filter;
	}
	
	
	public void printRecord(String option) {
		 //output
        for(Record item:combinedRecords) {
        	HBox incomeBox=new HBox(15);
        	if(option.equals("All")||option.equals("Income")&& item instanceof Income) {
        		incomeBox.getChildren().addAll(
            			new Label("(+)"),
            			new Label(item.getName()),
            			new Label(item.getTotal().toString()),
            			new Label(item.getDate())
            			);
        		incomeBox.getStyleClass().add("homeIncomeBox");
            	recordList.getChildren().add(incomeBox);
        	}else if(option.equals("All")||option.equals("Outcome")&& item instanceof Outcome) {
        		incomeBox.getChildren().addAll(
            			new Label("(-)"),
            			new Label(item.getName()),
            			new Label(item.getTotal().toString()),
            			new Label(item.getDate())
            			);
        		incomeBox.getStyleClass().add("homeIncomeBox");
            	recordList.getChildren().add(incomeBox);
        	}
            
        }
	}
	
	public void insertAndSortRecord(String option) {
		for (Income income : incomeList) {
            combinedRecords.add(income);
        }

        for (Outcome outcome : outcomeList) {
            combinedRecords.add(outcome);
        }
        
        for (int i = 0; i < combinedRecords.size(); i++) {
            for (int j = 0; j < combinedRecords.size()- 1; j++) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date date1 = dateFormat.parse(combinedRecords.get(j).getDate());
                    Date date2 = dateFormat.parse(combinedRecords.get(j + 1).getDate());

                    if (option.equals("Descending")&& date1.compareTo(date2) < 0) {
                        // Swap the records by creating a temporary variable
                        Record temp = combinedRecords.get(j);
                        combinedRecords.set(j, combinedRecords.get(j + 1));
                        combinedRecords.set(j + 1, temp);
                    } else if(option.equals("Ascending")&& date1.compareTo(date2) > 0) {
                    	 Record temp = combinedRecords.get(j);
                         combinedRecords.set(j, combinedRecords.get(j + 1));
                         combinedRecords.set(j + 1, temp);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
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