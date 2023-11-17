package components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.SharedStageHolder;
import view.AddRecord;
import view.Consultant;
import view.HomePage;
import view.LandingPage;
import view.Login;
import view.About;


public class Navbar {

	private Stage primaryStage;
	public Navbar(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		this.primaryStage=primaryStage;
	}
	
	 public HBox createNavbar() {
	        HBox navbar = new HBox(298);
	        
	        HBox left=new HBox(5);
	        Image image = new Image("/images/logo.png"); // Adjust the path to your image.
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(50); // Set the desired width
	        imageView.setFitHeight(50);
	        imageView.setOnMouseClicked(event -> {
	        	new LandingPage(primaryStage).show();
	        });
	        Button homeButton = new Button("Home");
	        homeButton.setOnAction(event-> {
	          new HomePage(primaryStage).show();
	    	});
	        Button aboutButton = new Button("About");
	        aboutButton.setOnAction(event-> {
	        	new About(primaryStage).show();
	    	});
	        Button consultantButton = new Button("Consultant");
	        consultantButton.setOnAction(event-> {
	        	new Consultant(primaryStage).show();
	    	});
	        
	        left.getChildren().addAll(imageView,homeButton, aboutButton, consultantButton);
	        Button loginButton = new Button("Login");
	        loginButton.setOnAction(event-> {
	        	new Login(primaryStage).show();
	    	});
	        loginButton.getStyleClass().add("btn-round-sm");
 	        loginButton.setStyle(
	        	"-fx-border-color: #2e7d32;" +
	        	"-fx-background-color: green;"+
	        	"-fx-text-fill: white;  "
	        		);


	        navbar.getChildren().addAll(left,loginButton);	         
	        navbar.getStyleClass().add("navbar");

	        return navbar;
	    }
	 
	 

}
