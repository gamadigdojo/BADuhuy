package view;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        VBox topBox = new VBox();
        
        
        navbar.setupNavbar();
        topBox.getChildren().add(navbar);
        //------------------Header--------------------------//
        
        BorderPane root = new BorderPane();
        
        VBox additionalHeader = new VBox();
        Label headerLabel = new Label("what's your main goal today");
        TextField inputBar = new TextField();
        additionalHeader.getChildren().addAll(headerLabel, inputBar);
        
        topBox.getChildren().add(additionalHeader);

        
        //------------------Center Layout------------------//
        ArrayList<Income> incomeList=Income.retreiveRecord();
        ArrayList<Outcome> outcomeList=Outcome.retreiveRecord();
      
        VBox recordList=new VBox(15);
        for(Income income: incomeList) {
        	HBox incomeBox=new HBox(15);
        	incomeBox.getChildren().addAll(
        			new Label(income.getIncomeID()),
        			new Label(income.getName()),
        			new Label(income.getTotalIncome().toString()),
        			new Label(income.getDateIncome()),
        			new Label(income.getNoteIncome())
        			);
        	recordList.getChildren().add(incomeBox);
        }
        
        for(Outcome outcome: outcomeList) {
        	HBox outcomeBox=new HBox(15);
        	outcomeBox.getChildren().addAll(
        			new Label(outcome.getOutcomeID()),
        			new Label(outcome.getName()),
        			new Label(outcome.getTotalOutcome().toString()),
        			new Label(outcome.getDateOutcome()),
        			new Label(outcome.getNoteOutcome())
        			);
        	recordList.getChildren().add(outcomeBox);
        }
       
        
        //-----------------Footer Layout------------------//
        addRecord.setOnAction(event-> {
        	moveAddRecord();
    	});
        HBox footer=new HBox();
        footer.getChildren().addAll(
        		balance,
        		addRecord
        		);
        
        
        Insets padding = new Insets(50, 80, 100, 80); // Insets: top, right, bottom, left
        root.setPadding(padding);
        root.setTop(topBox);
        root.setCenter(recordList);
        root.setBottom(footer);
        return new Scene(root, 700, 500);
    }
	
	void moveAddRecord() {
		Scene AddRecordScene = new AddRecord().createAddScene();
        SharedStageHolder.getPrimaryStage().setScene(AddRecordScene);
	}
	
 

}
