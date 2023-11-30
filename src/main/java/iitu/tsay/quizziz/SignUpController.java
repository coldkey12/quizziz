package iitu.tsay.quizziz;

import animations.Shaker;
import db.DbHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.CurrentUser;

public class SignUpController {
    @FXML
    private Label alertLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField rePasswordField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameField;

    @FXML
    void initialize() {
        DbHandler dbHandler = new DbHandler();

        loginLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    loginLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(SignUpController.class.getResource("loginPage.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        submitButton.setOnAction(actionEvent -> {
            try {
                if (!dbHandler.checkExistance(usernameField.getText()) &&
                        passwordField.getText().equals(rePasswordField.getText()) &&
                        !usernameField.getText().equals("")) {
                    dbHandler.addUser(usernameField.getText(), passwordField.getText());

                    loginLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(SignUpController.class.getResource("loginPage.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    Shaker usernameShaker = new Shaker(usernameField);
                    Shaker passwordShaker = new Shaker(passwordField);
                    Shaker rePasswordShaker = new Shaker(rePasswordField);
                    usernameShaker.shake();
                    passwordShaker.shake();
                    rePasswordShaker.shake();
                    usernameField.setText("");
                    passwordField.setText("");
                    rePasswordField.setText("");
                    alertLabel.setText("Username is taken or passwords don't match");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
