import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
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

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

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
            Parent root = FXMLLoader.load(getClass().getResource("Reg_page.fxml"));
            Stage primaryr = new Stage();
            primaryr.initStyle(StageStyle.UNDECORATED);
            primaryr.setTitle("Connect =_=");
            primaryr.setScene(new Scene(root, 428, 518));
            primaryr.show();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }

    @FXML
    public void getDetails(MouseEvent event) {

        if (nameIn.getText().isBlank() == false && passIn.getText().isBlank() == false) {
            warningLabel.setText("");

            System.out.println(nameIn.getText());
            System.out.println(passIn.getText());

            validateLogin();

        } else {
            if (nameIn.getText().isBlank() == true || passIn.getText().isBlank() == true)
                warningLabel.setText("Enter Your Username and Password!!!!");

            // if (passIn.getText().isBlank() == true)
            // warningLabel.setText("Enter Your Password!!!!");
        }
    }

    @FXML
    public void validateLogin() {
        Databaseconnection connectNow = new Databaseconnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM accountsdata WHERE Username = '" + nameIn.getText()
                + "' AND  Password = '" + passIn.getText() + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(verifyLogin);

            while (queryresult.next()) {
                if (queryresult.getInt(1) == 1) {
                    warningLabel.setText("Login Successful");
                } else {
                    warningLabel.setText("Invalid Username or Password");
                }
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
