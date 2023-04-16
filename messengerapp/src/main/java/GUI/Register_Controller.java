package GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Register_Controller {
    private static int userId = 4;

    @FXML
    private Button closeButton;

    @FXML
    private TextField mobilenoTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField confPassField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label confirmPassLabel;

    @FXML
    private Label registeredSuccessfullyLabel;

    @FXML
    void ExitApp(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registerButtonAction(MouseEvent event) {
        confirmPassLabel.setText("");
        registeredSuccessfullyLabel.setText("");
        Databaseconnection connectNow = new Databaseconnection();
        Connection connectDB = connectNow.getConnection();

        if (nameTextField.getText().isEmpty() == true || usernameTextField.getText().isEmpty() == true
                || mobilenoTextField.getText().isEmpty() == true || passwordField.getText().isEmpty() == true) {
            registeredSuccessfullyLabel.setText("Any field should not be empty");
            return;
        }

        String register = "INSERT INTO accountsdata VALUES(" + userId++ + ",'" + usernameTextField.getText() + "' , '"
                + nameTextField.getText() + "'," + mobilenoTextField.getText() + ",'" + passwordField.getText() + "');";

        try {

            Statement statement = connectDB.createStatement();
            if (passwordField.getText().equals(confPassField.getText())) {
                statement.executeUpdate(register); // executeUpdate because INSERT does not return something

                registeredSuccessfullyLabel.setText("User registered successfully");
            } else {
                confirmPassLabel.setText("Password is not matching!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
