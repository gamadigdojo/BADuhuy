package view;

import components.Navbar;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Consultant {

	public Consultant() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene createConsultantScene() {
		BorderPane root = new BorderPane();
		Navbar navbar = new Navbar();
		HBox navigationBar = navbar.createNavbar();
		
		VBox rows=new VBox(15);
		 rows.setPadding(new Insets(50, 80, 100, 80));
		HBox john=createConsultant("John","nice","0838");
		HBox kevin=createConsultant("Kevin","nice","0838");
		HBox maria=createConsultant("Maria","nice","0838");


		rows.getChildren().addAll(john,kevin,maria);
		 

		root.setTop(navigationBar);
		root.setCenter(rows);
		
        //add external css
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
	}
	
	HBox createConsultant(String name,String description,String contact) {
		 HBox row = new HBox();

         Label nameLabel = new Label(name);
         Label descriptionLabel = new Label(description);
         Label phoneNumberLabel = new Label(contact);
	      row.getChildren().addAll(nameLabel, descriptionLabel, phoneNumberLabel);
			row.getStyleClass().add("row");

	      return row;
	}

}
