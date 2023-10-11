package view;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        BorderPane root = new BorderPane();

        
        //------------------Center Layout------------------//
 
        TableView<Database> OutputTable = new TableView<>();
        ArrayList<Income> incomeList=Income.retreiveRecord();
        ArrayList<Outcome> outputList=Outcome.retreiveRecord();
      
        TableColumn<Database, String> id= new TableColumn<>("Id");
        TableColumn<Database, String> name= new TableColumn<>("Name");
        TableColumn<Database, Double> total= new TableColumn<>("Total");
        TableColumn<Database, String> date= new TableColumn<>("Date");
        TableColumn<Database, String> note= new TableColumn<>("Note");
        
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().name());
        total.setCellValueFactory(cellData -> cellData.getValue().totalObservable());
        date.setCellValueFactory(cellData -> cellData.getValue().date());
        note.setCellValueFactory(cellData -> cellData.getValue().note());
        
        // Add the columns to the TableView
        OutputTable.getColumns().addAll(id, name,total,date,note);

        // Populate the TableView with data from the ArrayList
        OutputTable.getItems().addAll(incomeList);
        OutputTable.getItems().addAll(outputList);
        
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
        root.setCenter(OutputTable);
        root.setBottom(footer);
        return new Scene(root, 700, 500);
    }
	
	void moveAddRecord() {
		Scene AddRecordScene = new AddRecord().createAddScene();
        SharedStageHolder.getPrimaryStage().setScene(AddRecordScene);
	}

}
