package GUI;

import Encryption.Encryptdecrypt;
import database.Databaseconnection;
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
import javafx.stage.StageStyle;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class Login_Controller {
    public static String name;

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

    public static String getName(){
        return name;
    }
    public void regPageLoader() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Reg_page.fxml"));
            Stage primaryr = new Stage();
            primaryr.initStyle(StageStyle.UNDECORATED);
            primaryr.setTitle("Connect =_=");
            primaryr.setScene(new Scene(root, 410, 525));
            primaryr.setX(555);
            primaryr.setY(143);
            primaryr.show();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }

    @FXML
    public void getDetails(MouseEvent event) {

        if (nameIn.getText().isBlank() == false && passIn.getText().isBlank() == false) {
            warningLabel.setText("");

            name = nameIn.getText();
            System.out.println(name);
//            pass = passIn.getText();

//            System.out.println(name);
//            System.out.println(pass);

            validateLogin();

        } else {
            if (nameIn.getText().isBlank() == true || passIn.getText().isBlank() == true)
                warningLabel.setText("Enter Your Username and Password!!!!");

            // if (passIn.getText().isBlank() == true)
            // warningLabel.setText("Enter Your Password!!!!");
        }
    }

    public void validateLogin(){
        Databaseconnection conector=new Databaseconnection();
        Connection con = conector.getConnection();
        try {

            PreparedStatement verifylogin=con.prepareStatement("SELECT * FROM accountsdata WHERE username = ?");
            verifylogin.setString(1,name);
            ResultSet rs=verifylogin.executeQuery();
            if (rs.next()){
                String encrypted_pass_fetched_from_db=rs.getString(5);
                Encryptdecrypt encryptor =new Encryptdecrypt("1234567890123456");
                String enccrypted_pass=encryptor.encrypt(passIn.getText());
                if(enccrypted_pass.equals(encrypted_pass_fetched_from_db)){

                    warningLabel.setText("Succsesfully loggedin");
                    Parent root = FXMLLoader.load(getClass().getResource("/GUI/client.fxml"));
                    Stage second = new Stage();
                    second.setTitle("Connect =_=");
                    second.setScene(new Scene(root, 460, 520));
                    second.show();
                }
                else {
                    warningLabel.setText("Wrong password entered!!");
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
