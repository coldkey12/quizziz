package iitu.tsay.quizziz;

import db.DbHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.CurrentUser;
import models.User;

public class LeaderboardController {
    @FXML
    private Label homeLabel;

    @FXML
    private ListView<String> leaderList;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label questionsLabel;

    @FXML
    private Label topOneLabel;

    @FXML
    void initialize(){
        DbHandler dbHandler = new DbHandler();

        for (User user : dbHandler.getAllUsersAsc()) {
            leaderList.getItems().add(user.getUsername() + " / " + user.getScore());
        }

        topOneLabel.setText(dbHandler.getAllUsersAsc().get(0).getUsername());

        homeLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    homeLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(LeaderboardController.class.getResource("homePage.fxml"));
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
                    loader.setLocation(LeaderboardController.class.getResource("questionsPage.fxml"));
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
                    loader.setLocation(LeaderboardController.class.getResource("loginPage.fxml"));
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
