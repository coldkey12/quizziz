package iitu.tsay.quizziz;

import animations.Shaker;
import db.DbHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginPageController {
    @FXML
    private Label alertLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpLabel;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameField;

    @FXML
    void initialize() {
        DbHandler dbHandler = new DbHandler();

        submitButton.setOnAction(actionEvent -> {
            if (dbHandler.login(usernameField.getText(), passwordField.getText()) && !usernameField.getText().equals("")) {
                try {
                    submitButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(LoginPageController.class.getResource("homePage.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Shaker usernameShaker = new Shaker(usernameField);
                Shaker passwordShaker = new Shaker(passwordField);
                usernameShaker.shake();
                passwordShaker.shake();
                usernameField.setText("");
                passwordField.setText("");
                alertLabel.setText("Username or password is invalid");
            }
        });

        signUpLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    submitButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(LoginPageController.class.getResource("signUpPage.fxml"));
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
    }
}