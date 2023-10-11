package view;

import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
//        TableView<DataModel> tableView = new TableView<>();
//        TableColumn<DataModel, String> column1 = new TableColumn<>("Column 1");
//        TableColumn<DataModel, String> column2 = new TableColumn<>("Column 2");
//
//        tableView.getColumns().addAll(column1, column2);
        
        
        VBox layout = new VBox();
        layout.getChildren().addAll( 
        		new Label("asiap"),
        		new Label("apaiya")
        		); // Add your home view content here
        
        
       
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
        root.setCenter(layout);
        root.setBottom(footer);
        return new Scene(root, 700, 500);
    }
	
	void moveAddRecord() {
		Scene AddRecordScene = new AddRecord().createAddScene();
        SharedStageHolder.getPrimaryStage().setScene(AddRecordScene);
	}

}
