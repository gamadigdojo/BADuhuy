package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

        VBox register = new VBox(10);
        Button registerButton = new Button("Register");
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

        register.getChildren().addAll(
            new Label("Nama:"),
            nameField,
            new Label("Email:"),
            emailField,
            new Label("Password:"),
            passwordField,
            errorLabel,
            registerButton
        );

        //----------------SETUP-----------------//
        root.setCenter(register);
        //add external css
        Scene scene = new Scene(root, 700, 500);
//        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        return scene;
    }

    private void handleRegistration(ActionEvent event) throws SQLException {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        
        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            errorLabel.setText("Nama, email, dan password harus diisi.");
            return;
        }

        // Validasi email harus mengandung karakter "@"
        if (!email.contains("@")) {
            errorLabel.setText("Email tidak valid. Harus mengandung karakter '@'.");
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
