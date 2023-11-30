package iitu.tsay.quizziz;

import db.DbHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.CurrentUser;

public class HomePageController {
    @FXML
    private Label accessLevelLabel;

    @FXML
    private Label leaderboardLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label questionsLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    void initialize() {
        DbHandler dbHandler = new DbHandler();

        usernameLabel.setText(CurrentUser.username);
        accessLevelLabel.setText(String.valueOf(CurrentUser.accessLevel));
        scoreLabel.setText(String.valueOf(CurrentUser.score));

        leaderboardLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    leaderboardLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(HomePageController.class.getResource("leaderboardPage.fxml"));
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

        logOutLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    CurrentUser.username = null;
                    CurrentUser.id = 0;
                    CurrentUser.accessLevel = 0;
                    CurrentUser.score = 0;

                    logOutLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(HomePageController.class.getResource("loginPage.fxml"));
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

        questionsLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    questionsLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(HomePageController.class.getResource("questionsPage.fxml"));
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
