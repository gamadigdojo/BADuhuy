package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Database;

public class Login {
    private Stage primaryStage;

    private TextField emailField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Label errorLabel = new Label();

    public Login() {
     //   this.db = new Database();
    }

    public Scene createLoginScene() {
		BorderPane root = new BorderPane();

        VBox login= new VBox(10);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(event);
            }
        });

        login.getChildren().addAll(
            new Label("Email:"),
            emailField,
            new Label("Password:"),
            passwordField,
            errorLabel,
            loginButton
        );

        //----------------SETUP-----------------//
        root.setCenter(login);
        
        //add external css
        Scene scene = new Scene(root, 700, 500);
//        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
    }

    
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validasi email harus mengandung karakter "@"
        if (!email.contains("@")) {
            errorLabel.setText("Email tidak valid. Harus mengandung karakter '@'.");
            return;
        }

        // Validasi email dan password tidak boleh kosong
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Email dan password harus diisi.");
            return;
        }
        
        else {
        	 errorLabel.setText("Login berhasil");
        }

        // Lakukan pengecekan dengan data yang ada di database
//        boolean validUser = validateUser(email, password);
//
//        if (validUser) {
//            // Jika email dan password valid, lanjutkan ke halaman beranda
//            System.out.println("Login berhasil. Redirect ke halaman beranda.");
//        } else {
//            // Jika email dan password tidak sesuai dengan data di database
//            errorLabel.setText("Email atau password salah. Silakan coba lagi.");
//        }
    }
}