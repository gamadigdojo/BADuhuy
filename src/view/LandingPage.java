package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LandingPage {

	public LandingPage() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene createLandingScene() {
		BorderPane root = new BorderPane();
		Image logo = new Image("/images/logo.png"); // Adjust the path to your image.
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(60); // Set the desired width
        logoView.setFitHeight(60);
        root.setTop(logoView);
		
		HBox content=new HBox(20);
	    content.setPadding(new Insets(50, 80, 100, 80));
		VBox desc=new VBox(10);
		desc.getStyleClass().add("landingDesc");
		Label heading = new Label("WELCOME TO\nPIGGY POCKET");
	    heading.getStyleClass().add("landingHeading"); // Apply CSS styling if needed
        Label paragraph = new Label(
        		"Budget, Save, Thrive"
	        );
	    paragraph.getStyleClass().add("landingParagraph"); // Apply CSS styling if needed

        // Create buttons
        Button button1 = new Button("Create an account");
        button1.getStyleClass().add("btn-outline-success");
        button1.setPrefWidth(150); 
        Button button2 = new Button("Sign in");
        button2.getStyleClass().add("btn-success");
        button2.setPrefWidth(150); 
	    desc.getChildren().addAll(heading,paragraph,button1,button2);
		
		
		Image image = new Image("/images/thumbnail.png"); // Adjust the path to your image.
        ImageView imageView = new ImageView(image);
        
		
		content.getChildren().addAll(desc,imageView);
		
		root.setCenter(content);
        //add external css
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
	}

}
