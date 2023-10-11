package view;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Income;
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
        BorderPane root = new BorderPane();

        
        //------------------Center Layout------------------//
        
     // Create a TableView
        TableView<Income> incomeTable = new TableView<>();
        ArrayList<Income> incomeList=Income.retreiveRecord();
        
        //testing purposes
        
        for(Income income: incomeList) {
        	System.out.println("data set 1");
        }
        
        TableColumn<Income, String> IncomeID= new TableColumn<>("IncomeID");
        TableColumn<Income, String> Name= new TableColumn<>("Name");
        TableColumn<Income, Double> TotalIncome= new TableColumn<>("TotalIncome");
        TableColumn<Income, String> DateIncome= new TableColumn<>("DateIncome");
        TableColumn<Income, String> NoteIncome= new TableColumn<>("NoteIncome");

        IncomeID.setCellValueFactory(cellData -> cellData.getValue().incomeIDProperty());
        Name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TotalIncome.setCellValueFactory(cellData -> cellData.getValue().totalIncomeObservable());
        DateIncome.setCellValueFactory(cellData -> cellData.getValue().dateIncomeProperty());
        NoteIncome.setCellValueFactory(cellData -> cellData.getValue().noteIncomeProperty());
        
        // Add the columns to the TableView
        incomeTable.getColumns().addAll(IncomeID, Name,TotalIncome,DateIncome,NoteIncome);

        // Populate the TableView with data from the ArrayList
        incomeTable.getItems().addAll(incomeList);
        
        //-----------------Footer Layout------------------//
        addRecord.setOnAction(event-> {
        	moveAddRecord();
    	});
        HBox footer=new HBox();
        footer.getChildren().addAll(
        		balance,
        		addRecord
        		);
        
        root.setTop(navbar);
        root.setCenter(incomeTable);
        root.setBottom(footer);
        return new Scene(root, 700, 500);
    }
	
	void moveAddRecord() {
		Scene AddRecordScene = new AddRecord().createAddScene();
        SharedStageHolder.getPrimaryStage().setScene(AddRecordScene);
	}

}
