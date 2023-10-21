import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Database;

public class LoginView {
    private Stage primaryStage;
    private Database db;
    Connection connection;

    private TextField emailField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Label errorLabel = new Label();

    public LoginView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.db = new Database();
        connection = db.getConnection();
    }

    public void show() {
        VBox root = new VBox(10);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(event);
            }
        });

        root.getChildren().addAll(
            new Label("Email:"),
            emailField,
            new Label("Password:"),
            passwordField,
            errorLabel,
            loginButton
        );

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
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