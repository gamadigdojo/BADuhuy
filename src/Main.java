import model.Database;
import model.SharedStageHolder;
import view.AddRecord;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomePageView;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {    
        SharedStageHolder.setPrimaryStage(primaryStage); // Set the primary stage

        primaryStage.setTitle("JavaFX Shared Variable Example");

        // Create initial scene
        Scene HomePage = new HomePageView().createHomeScene();

        // Set the initial scene
        primaryStage.setScene(HomePage);

        primaryStage.show();
    }


}



 