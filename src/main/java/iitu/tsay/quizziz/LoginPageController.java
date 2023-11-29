package iitu.tsay.quizziz;

import db.DbHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPageController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private Text signUpLabel;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameField;

    @FXML
    void initialize(){
        DbHandler dbHandler = new DbHandler();
    }
}