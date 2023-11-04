package components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.SharedStageHolder;
import view.AddRecord;
import view.Consultant;
import view.HomePage;
import view.Login;
import view.About;


public class Navbar {

	public Navbar() {
		// TODO Auto-generated constructor stub
	}
	
	 public HBox createNavbar() {
	        HBox navbar = new HBox();
	        
	        // Add navigation elements to the navbar
	        Image image = new Image("/images/logo.png"); // Adjust the path to your image.
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(50); // Set the desired width
	        imageView.setFitHeight(50);
	        Button homeButton = new Button("Home");
	        homeButton.setOnAction(event-> {
	        	Scene HomeScene = new HomePage().createHomeScene();
		        SharedStageHolder.getPrimaryStage().setScene(HomeScene);
	    	});
	        Button aboutButton = new Button("About");
	        aboutButton.setOnAction(event-> {
	        	Scene AboutScene= new About().createAboutScene();
		        SharedStageHolder.getPrimaryStage().setScene(AboutScene);
	    	});
	        Button consultantButton = new Button("Consultant");
	        consultantButton.setOnAction(event-> {
	        	Scene ConsultantScene= new Consultant().createConsultantScene();
		        SharedStageHolder.getPrimaryStage().setScene(ConsultantScene);
	    	});
	        
	        navbar.getChildren().addAll(imageView,homeButton, aboutButton, consultantButton);
	        Button loginButton = new Button("Login");
	        loginButton.setOnAction(event-> {
	        	Scene loginScene = new Login().createLoginScene();
		        SharedStageHolder.getPrimaryStage().setScene(loginScene);
	    	});

	        navbar.getChildren().add(loginButton);	         
	        navbar.getStyleClass().add("navbar");

	        return navbar;
	    }
	 
	 

}
