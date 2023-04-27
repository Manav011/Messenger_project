package socketserver;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Encryption.Encryptdecrypt;
import GUI.Login_Controller;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Client_Controller implements Initializable {// implementing initializable so that we can work with all the
    // injected fxml variables
    private String name;

    @FXML
    private AnchorPane ap_main;

    @FXML
    private Button button_send;

    @FXML
    private TextField tf_message;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private VBox vbox_message;// vertical box for stacking things

    @FXML
    private Label name_label;

    @FXML
    private VBox users_vBox;

    @FXML
    private Button logout_button;

    @FXML
    private Stage stage;

    private Client client;

    private static final Encryptdecrypt encryptor = new Encryptdecrypt("1234567890123456");

    private String ip;

    @FXML
    public void logout(ActionEvent event) {
        stage = (Stage) ap_main.getScene().getWindow();
        try {
            client.sendMessageToServer(encryptor.encrypt(name + ": left the chat"));
        } catch (Exception e) {
            System.out.println("not sending the left the chat");
            // e.printStackTrace();
        }
        client.sendMessageToServer("q1u2i3t4");
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label serverconLabel = new Label("Enter the IP of Server");

        VBox v = new VBox(10);
        TextField ip_Field = new TextField();
        Button b = new Button("Connect");
        v.getChildren().add(serverconLabel);
        v.getChildren().add(ip_Field);
        HBox h = new HBox(b);
        h.setAlignment(Pos.CENTER);
        v.getChildren().add(h);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ip = ip_Field.getText();
                try {
                    if (!ip.equals("")) {
                        client = new Client(new Socket(ip, 1234));
                        serverconLabel.setText("Close this window to open Client Window");
                    } else {
                        serverconLabel.setText("No IP detected");
                    }

                } catch (IOException e) {
                    serverconLabel.setText("No server present on this IP");
                    System.out.println("Error Creating Client");
                    // e.printStackTrace();
                }
            }
        });

        Stage ip_stage = new Stage();
        ip_stage.setScene(new Scene(v, 225, 100));
        ip_stage.setTitle("Server IP");
        ip_stage.showAndWait();

        if (ip.equals("")) {
            System.out.println("IP not found");
            System.exit(0);
        }

        name_label.setText(Login_Controller.name);
        name = Login_Controller.name;
        try {
            client.sendMessageToServer(encryptor.encrypt(name + ": joined the chat"));
        } catch (Exception e) {
            System.out.println("not sending the left the chat");
            e.printStackTrace();
        }

        vbox_message.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        ImageView log = new ImageView(
                "/GUI/stylesheets/logout.png");
        log.setFitHeight(20);
        log.setFitWidth(20);
        logout_button.setGraphic(log);

        client.receiveMessage(vbox_message);

        ImageView send = new ImageView(
                "/GUI/stylesheets/send.png");
        send.setFitHeight(22.0);
        send.setFitWidth(28.0);
        button_send.setGraphic(send);

        tf_message.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    String messageTosend = tf_message.getText();

                    if (!messageTosend.isEmpty()) {
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER_RIGHT);
                        hBox.setPadding(new Insets(5, 5, 5, 10));

                        Text text = new Text(messageTosend);
                        TextFlow textFlow = new TextFlow(text);// TextFlow :using because if it's wrapping feature

                        textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(15,125,242);" +
                                "-fx-background-radius: 20px;");

                        textFlow.setPadding(new Insets(5, 10, 5, 10));
                        text.setFill(Color.color(.934, .935, .996));

                        hBox.getChildren().add(textFlow);
                        vbox_message.getChildren().add(hBox);

                        try {
                            client.sendMessageToServer(encryptor.encrypt(name + ": " + messageTosend));
                        } catch (Exception e) {
                            System.out.println("now sending the message from client " + name);
                            e.printStackTrace();
                        }
                        tf_message.clear();
                    }
                }
            }
        });

        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String messageTosend = tf_message.getText();

                if (!messageTosend.isEmpty()) {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 5, 10));

                    Text text = new Text(messageTosend);
                    TextFlow textFlow = new TextFlow(text);// TextFlow :using because if it's wrapping feature

                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(15,125,242);" +
                            "-fx-background-radius: 20px;");

                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(.934, .935, .996));

                    hBox.getChildren().add(textFlow);
                    vbox_message.getChildren().add(hBox);

                    try {
                        client.sendMessageToServer(encryptor.encrypt(name + ": " + messageTosend));
                    } catch (Exception e) {
                        System.out.println("now sending the message from client " + name);
                        e.printStackTrace();
                    }
                    tf_message.clear();
                }
            }
        });

    }

    public static void addLabel(String messageFromClient, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        VBox wholemsg = new VBox();

        try {
            messageFromClient = encryptor.decrypt(messageFromClient);
        } catch (Exception e) {
            System.out.println("receving part is not working for client");
            e.printStackTrace();
        }

        if (messageFromClient.endsWith("left the chat") || messageFromClient.endsWith("joined the chat")) {
            messageFromClient = messageFromClient.replace(":", "");
            Label left = new Label(messageFromClient);
            TextFlow text = new TextFlow(left);
            text.setPadding(new Insets(10, 10, 3, 150));
            wholemsg.getChildren().add(text);
        } else {
            String[] msgarr = messageFromClient.split(": ");

            Label name = new Label(msgarr[0]);
            wholemsg.getChildren().add(name);

            Text text = new Text(msgarr[1]);

            TextFlow textFlow = new TextFlow(text);
            wholemsg.getChildren().add(textFlow);

            textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                    "-fx-background-radius: 20px;");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
        }

        hBox.getChildren().add(wholemsg);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }
}
