package GUI;

import Encryption.Encryptdecrypt;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.stage.StageStyle;

import java.sql.*;

import database.*;

import java.io.IOException;
import java.util.Objects;

public class Login_Controller {
    String name, pass;

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private AnchorPane SUB1Anchorpane;

    @FXML
    private Label warningLabel;

    @FXML
    private Button Login;

    @FXML
    private Button ExitBu;

    @FXML
    private TextField nameIn;

    @FXML
    private Button regButton;

    @FXML
    private PasswordField passIn;

    @FXML
    private Label showPass;

    @FXML
    private ToggleButton showPassButton;

    public void regPageLoader() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/Reg_page.fxml")));
            Stage primaryr = new Stage();
            // primaryr.initStyle(StageStyle.UNDECORATED);
            primaryr.setTitle("Connect =_=");
            primaryr.setScene(new Scene(root, 428, 518));
            primaryr.show();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }

    @FXML
    public void getDetails(MouseEvent event) {

        if (nameIn.getText().isEmpty() == false && passIn.getText().isEmpty() == false) {
            warningLabel.setText("");

            System.out.println(nameIn.getText());
            System.out.println(passIn.getText());

            validateLogin();

        } else {
            if (nameIn.getText().isEmpty() == true || passIn.getText().isEmpty() == true)
                warningLabel.setText("Enter Your Username and Password!!!!");

            // if (passIn.getText().isEmpty() == true)
            // warningLabel.setText("Enter Your Password!!!!");
        }
    }

    @FXML
    public void validateLogin(){
        Databaseconnection conector=new Databaseconnection();
        Connection con = conector.getConnection();

//        String verifyLogin = "SELECT count(1) FROM accountsdata WHERE Username = '" + nameIn.getText()
//                + "' AND  Password = '" + passIn.getText() + "';";

        try {
            PreparedStatement verifylogin=con.prepareStatement("SELECT * FROM accountsdata WHERE username = ?");
            verifylogin.setString(1,nameIn.getText());
            ResultSet rs=verifylogin.executeQuery();
            if (rs.next()){
                String encrypted_pass=rs.getString(5);
                Encryptdecrypt decryptor =new Encryptdecrypt("1234567890123456");
                String decryptedpass=decryptor.decrypt(encrypted_pass);
                if(decryptedpass.equals(passIn.getText())){
                    warningLabel.setText("Succsesfully loggedin");
                }
                else {
                    warningLabel.setText("Wrong password");
                }

            }
            else{
                warningLabel.setText("User with this Username doesn't exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void keyTypedEvent(KeyEvent event) {
        if (showPassButton.isSelected())
            showPass.textProperty().bind(Bindings.concat(passIn.getText()));
    }

    @FXML
    void showPassword(MouseEvent event) {
        if (showPassButton.isSelected()) {

            showPass.setVisible(true);
            showPass.textProperty().bind(Bindings.concat(passIn.getText()));
            showPassButton.setText("Hide");

        } else {
            showPass.setVisible(false);
            showPassButton.setText("Show");
        }
    }

    @FXML
    void ExitApp(MouseEvent event) {
        Stage stage = (Stage) ExitBu.getScene().getWindow();
        stage.close();
    }

}
