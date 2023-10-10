package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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

}

 