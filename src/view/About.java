package view;

import components.Navbar;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class About {

	public About() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene createAboutScene() {
		BorderPane root = new BorderPane();
		Navbar navbar = new Navbar();
		HBox navigationBar = navbar.createNavbar();
		
		VBox content=new VBox();
		 Image image = new Image("/images/logo.png"); // Adjust the path to your image.
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(100); // Set the desired width
	        imageView.setFitHeight(100);
		Label heading=new Label("About Us");
        heading.getStyleClass().add("aboutHeading"); 
        Label paragraph = new Label(
        	    "Welcome to Piggy Pocket, your trusted companion in managing finances and achieving financial\n"
        	    + "freedom. We are an innovative website designed specifically to help you record, manage, and plan\n"
        	    + "your finances with ease, while providing direct access to consultations with experienced financial experts."
        	);
		content.getChildren().addAll(heading,imageView,paragraph);
		content.getStyleClass().add("aboutVBox");

		




		root.setTop(navigationBar);
		root.setCenter(content);
        //add external css
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
	}

}
