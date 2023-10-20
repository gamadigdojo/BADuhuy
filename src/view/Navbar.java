package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Navbar extends HBox{
	 private Button homeButton;
	 private Button profileButton;
	public Navbar() {
		// TODO Auto-generated constructor stub
		homeButton = new Button("Home");
	    profileButton = new Button("Profile");
	    getChildren().addAll(homeButton, profileButton);
	}
	
	 public Button getHomeButton() {
	        return homeButton;
	    }

	    public Button getProfileButton() {
	        return profileButton;
	    }
	    
    public void setupNavbar() {
        
        
        this.homeButton.setStyle(  
        	"-fx-background-color: lightgray;"+
        	"-fx-padding: 10px 20px;"+
        	"-fx-margin: 10px;"+
        	"-fx-font-size: 16px"+
        	"-fx-font-weight: bold"
        		);
        this.profileButton.setStyle(  
            	"-fx-background-color: lightgray;"+
            	"-fx-padding: 10px 20px;"+
            	"-fx-margin: 10px;"+
            	"-fx-font-size: 16px"+
            	"-fx-font-weight: bold"
            		);

        // Add the additional header VBox to the parent VBox
       
    }

}

 