import model.Database;
import view.HomePageView;
import view.LoginView;
import view.RegisterView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create an instance of the LoginView and pass a new LoginController to it
    	HomePageView hp=new HomePageView(primaryStage);
    	LoginView lv = new LoginView(primaryStage);
//    	RegisterView rv = new RegisterView(primaryStage);

        // Start the login view
//        hp.show();
//    	rv.show();
    	lv.show();
    	
    }


}



 