package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public RegisterView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.db = new Database();
        connection = db.getConnection();
    }

    public void show() {
        VBox root = new VBox(10);
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

        root.getChildren().addAll(
            new Label("Nama:"),
            nameField,
            new Label("Email:"),
            emailField,
            new Label("Password:"),
            passwordField,
            errorLabel,
            registerButton
        );

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
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

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
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
