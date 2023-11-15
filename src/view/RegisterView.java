package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Database;

public class RegisterView {
    private Stage primaryStage;
    private Database db;
    Connection connection;

    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Label errorLabel = new Label();

    public RegisterView() {
    }

   public Scene createRegisterScene() {
    BorderPane root = new BorderPane();
    
    // Tambahkan ImageView untuk 
    Image logoImage = new Image("/images/logo.png"); // Ganti dengan path atau URL logo Anda
    ImageView logoImageView = new ImageView(logoImage);
    logoImageView.setFitWidth(100); // Sesuaikan lebar logo sesuai kebutuhan
    logoImageView.setFitHeight(100); // Sesuaikan tinggi logo sesuai kebutuhan

    HBox logoBox = new HBox(10, logoImageView);
    logoBox.setAlignment(Pos.TOP_LEFT); // Letakkan di kiri atas
    
    Label registerLabel = new Label("Register your Account");
    Button registerButton = new Button("Create an account");
    Label signInLabel = new Label("Already have an Account? ");
    Label signInButton = new Label (" Sign in");

    registerButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
				handleRegistration(event);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    });
    
    VBox register = new VBox(10);
    
    registerButton.setStyle(
            "-fx-text-fill: white; " +
            "-fx-background-color: green; " +
            "-fx-padding: 10px; " +
            "-fx-border-radius: 20px; " +
            "-fx-background-radius: 20px; " +
            "-fx-font-weight: bold; " +
            "-fx-min-width: 150px; " +
            "-fx-font-size: 15px; " +
            "-fx-max-width: 300px; " +
            "-fx-border-style: none;");
    
    registerLabel.setStyle(
            "-fx-font-weight: 800; " +
            "-fx-font-size: 50px; " +
            "-fx-text-fill: green;");
    
    emailField.setStyle(
    		"-fx-focus-color: green; " +
    		        "-fx-text-box-border: green; " +
    		        "-fx-padding: 10px; " +
    		        "-fx-min-width: 150px; " +
    		        "-fx-border-radius: 20px; " +
    		        "-fx-max-width: 350px; " +
    		        "-fx-prompt-text-fill: green; ");
    
    errorLabel.setStyle(
    		"-fx-text-fill: red;"
    		);

    nameField.setStyle(
    		"-fx-focus-color: green; " +
    		        "-fx-text-box-border: green; " +
    		        "-fx-padding: 10px; " +
    		        "-fx-min-width: 150px; " +
    		        "-fx-border-radius: 20px; " +
    		        "-fx-max-width: 350px; " +
    		        "-fx-prompt-text-fill: green; ");
    
    passwordField.setStyle(
    		"-fx-focus-color: green; " +
    		        "-fx-text-box-border: green; " +
    		        "-fx-padding: 10px; " +
    		        "-fx-min-width: 150px; " +
    		        "-fx-border-radius: 20px; " +
    		        "-fx-max-width: 350px; " +
    		        "-fx-prompt-text-fill: green; ");
    
    signInButton.setStyle(
            "-fx-text-fill: green; " );
    
    emailField.setPromptText("Email");
    nameField.setPromptText("Name");
    passwordField.setPromptText("Password");
    
    HBox signInBox = new HBox(signInLabel, signInButton);
    

    Rectangle whiteBackground = new Rectangle(600, 400);
    whiteBackground.setFill(Color.WHITE);
    whiteBackground.setArcWidth(30);
    whiteBackground.setArcHeight(30);
    
    StackPane stackPane = new StackPane();
    stackPane.getChildren().addAll(whiteBackground, register);
    
    register.setAlignment(Pos.CENTER);  
    register.setSpacing(10); 
    signInBox.setAlignment(Pos.CENTER);
    


    register.getChildren().addAll(
    		 registerLabel,
             nameField,
             emailField,
             passwordField,
             errorLabel,
             registerButton,
             signInBox
    );
    
    root.setStyle(
    		"-fx-background-image: url('/images/background.png'); " +
    				"-fx-background-size: cover; " +
    				"-fx-background-position: center center;");

    //----------------SETUP-----------------//
    root.setTop(logoBox);
    root.setTop(logoBox);
    root.setCenter(stackPane);
    // Tambahkan CSS eksternal jika diperlukan
    // scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
    Scene scene = new Scene(root, 700, 500);
    return scene;
}
   
    private void handleRegistration(ActionEvent event) throws SQLException {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        
        if (email.isEmpty() && password.isEmpty() && name.isEmpty()) {
            errorLabel.setText("Nama, Email, dan Password harus diisi!");
            return;
        }
        
        if (email.isEmpty() && password.isEmpty()) {
            errorLabel.setText("Email, dan Password harus diisi!");
            return;
        }
        
        if (name.isEmpty() && password.isEmpty()) {
            errorLabel.setText("Nama, dan Password harus diisi!");
            return;
        }
        
        if (name.isEmpty() && email.isEmpty()) {
            errorLabel.setText("Nama, dan Email harus diisi!");
            return;
        }
        
        if (email.isEmpty()) {
            errorLabel.setText("Email harus diisi!");
            return;
        }
        
        if (name.isEmpty()) {
            errorLabel.setText("Nama harus diisi!");
            return;
        }
        
        if (password.isEmpty()) {
            errorLabel.setText("Password harus diisi!");
            return;
        }

        // Validasi email harus mengandung karakter "@"
        if (!email.contains("@")) {
            errorLabel.setText("Email tidak valid. Harus mengandung karakter '@'!");
            return;
        }else {
        	errorLabel.setText("Berhasil");
//        	 Validasi email, password, dan nama tidak boleh kosong
       

        // Simpan data ke database
        String insertSQL = "INSERT INTO User (Name,Email,Password) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            // Execute the insert statement
            preparedStatement.executeUpdate();


        // Tampilkan pesan sukses atau alihkan ke halaman lain jika diperlukan
        System.out.println("Registrasi berhasil. Redirect ke halaman lain.");
        	
        }

        }
    }
}
