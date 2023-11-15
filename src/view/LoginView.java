package view;


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

public class LoginView {
    private Stage primaryStage;

    private TextField emailField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Label errorLabel = new Label();
    private Label space = new Label();

    public LoginView() {
     //   this.db = new Database();
    }

    public Scene createLoginScene() {
        BorderPane root = new BorderPane();
        
        // Tambahkan ImageView untuk 
        Image logoImage = new Image("/images/logo.png"); // Ganti dengan path atau URL logo Anda
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(100); // Sesuaikan lebar logo sesuai kebutuhan
        logoImageView.setFitHeight(100); // Sesuaikan tinggi logo sesuai kebutuhan

        HBox logoBox = new HBox(10, logoImageView);
        logoBox.setAlignment(Pos.TOP_LEFT); // Letakkan di kiri atas
        
        Label loginLabel = new Label("Login to your Account");
        Button loginButton = new Button("Sign In");
        Label signInLabel = new Label("Don't have an Account? ");
        Label signUpButton = new Label (" Sign Up");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(event);
            }
        });
        
        VBox login = new VBox(10);
        
        loginButton.setStyle(
                "-fx-text-fill: white; " +
                "-fx-background-color: green; " +
                "-fx-padding: 10px; " +
                "-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-min-width: 200px; " +
                "-fx-font-size: 15px; " +
                "-fx-max-width: 350px; " +
                "-fx-border-style: none;");
        
        loginLabel.setStyle(
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
        
        passwordField.setStyle(
        		"-fx-focus-color: green; " +
        		        "-fx-text-box-border: green; " +
        		        "-fx-padding: 10px; " +
        		        "-fx-min-width: 150px; " +
        		        "-fx-border-radius: 20px; " +
        		        "-fx-max-width: 350px; " +
        		        "-fx-prompt-text-fill: green; ");
        
        signUpButton.setStyle(
                "-fx-text-fill: green; " );
        
        emailField.setPromptText("Email");
        passwordField.setPromptText("Password");
        
        HBox signUpBox = new HBox(signInLabel, signUpButton);
        

        Rectangle whiteBackground = new Rectangle(600, 400);
        whiteBackground.setFill(Color.WHITE);
        whiteBackground.setArcWidth(30);
        whiteBackground.setArcHeight(30);
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(whiteBackground, login);
        
        login.setAlignment(Pos.CENTER);  
        login.setSpacing(10); 
        signUpBox.setAlignment(Pos.CENTER);
        


        login.getChildren().addAll(
        		 loginLabel,
        		 space,
                 emailField,
                 passwordField,
                 errorLabel,
                 loginButton,
                 signUpBox
        );
        
        root.setStyle(
        		"-fx-background-image: url('/images/Gambar1.png'); " +
        				"-fx-background-size: cover; " +
        				"-fx-background-position: center center;");

        //----------------SETUP-----------------//
        root.setTop(logoBox);
        root.setTop(logoBox);
        root.setCenter(stackPane);
        Scene scene = new Scene(root, 700, 400);
        return scene;
    }

    
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        
        if (email.isEmpty() && password.isEmpty()) {
            errorLabel.setText("Email dan password harus diisi!");
            return;
        }
        if(email.isEmpty()) {
        	errorLabel.setText("Email harus diisi!");
            return;
        }

        
        if(password.isEmpty()) {
        	errorLabel.setText("Password harus diisi!");
            return;
        }
        
        // Validasi email harus mengandung karakter "@"
        if (!email.contains("@")) {
            errorLabel.setText("Email tidak valid. Harus mengandung karakter '@'!");
            return;
        }
//        klo password salah
//        if(!.isEmpty()) {
//        	errorLabel.setText("Email harus diisi!");
//            return;
//        }

     
        
        else {
        	System.out.println("Login Berhasil");
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