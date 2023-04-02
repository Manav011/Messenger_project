import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primary) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login_page.fxml"));

        // primary.initStyle(StageStyle.UNDECORATED);
        primary.setTitle("Connect =_=");
        primary.setScene(new Scene(root, 700, 700));

        primary.setResizable(true);
        primary.setMinHeight(648.0);
        primary.setMinWidth(700.0);
        primary.show();
    }
}
