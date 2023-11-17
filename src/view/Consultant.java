package view;

import components.Navbar;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class Consultant {

	private Stage primaryStage;
	User userSession;
	public Consultant(Stage primaryStage,User userSession) {
		// TODO Auto-generated constructor stub
		this.primaryStage=primaryStage;
		this.userSession=userSession;
	}
	
	public void show() {
		BorderPane root = new BorderPane();
		Navbar navbar = new Navbar(primaryStage,userSession);
		HBox navigationBar = navbar.createNavbar();
		VBox container = new VBox();
		container.getStyleClass().add("container");

		
		VBox rows=new VBox(15);
		HBox john=createConsultant("/images/consultant1.png","Elisabeth","available at 09.00","0838951231");
		HBox kevin=createConsultant("/images/consultant2.png","Emilia","available at 09.00","08388019231");
		HBox maria=createConsultant("images/consultant3.png","Kevin","available at 10.00","08389968869");


		rows.getChildren().addAll(john,kevin,maria);
		 

		container.getChildren().add(rows);
		
		root.setTop(navigationBar);
		
		root.setCenter(container);
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	HBox createConsultant(String picture,String name,String description,String contact) {
		 HBox row = new HBox(10);
		 Image image = new Image(picture); // Adjust the path to your image.
	     ImageView imageView = new ImageView(image);
	     imageView.setFitWidth(70); // Set the desired width
	     imageView.setFitHeight(60);
	        
	     VBox desc=new VBox();
         Label nameLabel = new Label(name);
	     nameLabel.getStyleClass().add("consultantName"); // Apply CSS styling if needed
         Label descriptionLabel = new Label(description);
         Label phoneNumberLabel = new Label(contact);
         desc.getChildren().addAll(nameLabel, descriptionLabel, phoneNumberLabel);
	     row.getChildren().addAll(imageView,desc);
	     row.getStyleClass().add("row");
	     
	     row.getStyleClass().add("consultant"); // Apply CSS styling if needed


	      return row;
	}

}
