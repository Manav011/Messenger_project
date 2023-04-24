package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.BreakIterator;

import Encryption.Encryptdecrypt;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import database.Databaseconnection;

public class Register_Controller {
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
    private Label warningOnRegistration;

    @FXML
    void ExitApp(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registerButtonAction(MouseEvent event) {
        confirmPassLabel.setText("");
        registeredSuccessfullyLabel.setText("");
        Databaseconnection conector = new Databaseconnection();
        Connection con = conector.getConnection();


        if (nameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty()
                || mobilenoTextField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            warningOnRegistration.setText("All fields are required");
            return;
        }
        if(mobilenoTextField.getText().length()!=10){
            warningOnRegistration.setText("Mobile number should be 10 digit");
            return;
        }
        if(!isNumeric(mobilenoTextField.getText())){
            warningOnRegistration.setText("Mobile number should be numeric only");
            return;
        }
        if(mobilenoTextField.getText().length()!=10){
            warningOnRegistration.setText("Mobile number should be 10 digit");
            return;
        }
        if(passwordField.getText().length()<=4){
            warningOnRegistration.setText("Password should be atleast 5 characters");
            return;
        }

        try {
            PreparedStatement check_userid_stmt = con
                    .prepareStatement("SELECT * FROM accountsdata WHERE username = ?;");
            PreparedStatement check_mn_stmt = con
                    .prepareStatement("SELECT * FROM accountsdata WHERE mobilenumber = ?;");

            PreparedStatement upstmt = con
                    .prepareStatement("insert into accountsdata (username,name,mobilenumber,pass) values(?,?,?,?)");

            // String register = "INSERT INTO accountsdata VALUES(" + userId++ + ",'" +
            // usernameTextField.getText() + "' , '"
            // + nameTextField.getText() + "'," + mobilenoTextField.getText() + ",'" +
            // passwordField.getText() + "');";
            check_userid_stmt.setString(1, usernameTextField.getText());
            check_mn_stmt.setString(1, mobilenoTextField.getText());
            ResultSet rs_ch_uid = check_userid_stmt.executeQuery();
            ResultSet rs_ch_mn = check_mn_stmt.executeQuery();
            if (rs_ch_uid.next()) {
                registeredSuccessfullyLabel.setText("This username is already taken");
            } else if (rs_ch_mn.next()) {
                registeredSuccessfullyLabel.setText("Account already exist on this mobile number");
            } else {
                upstmt.setString(1, usernameTextField.getText());
                upstmt.setString(2, nameTextField.getText());
                upstmt.setString(3, mobilenoTextField.getText());
                Encryptdecrypt encryptor = new Encryptdecrypt("1234567890123456");
                if (passwordField.getText().equals(confPassField.getText())) {
                    String encrypted_pass = encryptor.encrypt(passwordField.getText());
                    upstmt.setString(4, encrypted_pass);
                    upstmt.executeUpdate();
                    warningOnRegistration.setText("");
                    registeredSuccessfullyLabel.setText("User registered successfully");
                } else {
                    confirmPassLabel.setText("Password is not matching!!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}