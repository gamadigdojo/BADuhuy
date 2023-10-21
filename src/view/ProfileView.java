package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SharedStageHolder;

public class ProfileView {

    private Stage primaryStage;
	public ProfileView() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene createProfileScene() {
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
        
        VBox layout = new VBox();
        layout.getChildren().addAll( 
        		new Label("haloo"),
        		new Label("user")
        		); // Add your home view content here
        
//        navbar.setupNavbar();
        root.setTop(navbar);
        root.setCenter(layout);
        return new Scene(root, 700, 500);
    }

}
