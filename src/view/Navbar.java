package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Navbar extends HBox{
	 private Button homeButton;
	 private Button profileButton;
	 private Button calendarButton;
	 private Button statisticButton;
	 private Button consultantButton;

	 
	public Navbar() {
		// TODO Auto-generated constructor stub
		homeButton = new Button("Home");
	    profileButton = new Button("Profile");
	    calendarButton = new Button("Calendar");
	    statisticButton = new Button("Statistic");
	    consultantButton=new Button("Consultant");
	    Image image = new Image("/images/logo.png"); // Adjust the path to your image.
        ImageView imageView = new ImageView(image);
        
	    getChildren().addAll(imageView,homeButton, calendarButton,statisticButton,consultantButton,profileButton);
	    imageView.setFitWidth(50); // Set the desired width
        imageView.setFitHeight(50);
	}
	
	 public Button getHomeButton() {
	        return homeButton;
	    }
	 
	 public Button getConsultantButton() {
		 return consultantButton;
	 }

	    public Button getProfileButton() {
	        return profileButton;
	    }
	    
	    public Button getCalendarButton(){
	        return calendarButton;
	    }
	    
	    public Button getStatisticButton(){
	        return statisticButton;
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

 