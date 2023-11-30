package iitu.tsay.quizziz;

import db.DbHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.AnswerHandler;
import models.CurrentUser;
import models.Question;
import models.QuestionsHandler;

public class QuestionsController {
    @FXML
    private Label homeLabel;

    @FXML
    private Label leaderboardLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Button optionFourButton;

    @FXML
    private Button optionOneButton;

    @FXML
    private Button optionThreeButton;

    @FXML
    private Button optionTwoButton;

    @FXML
    private Label questionLabel;

    @FXML
    private ListView<String> questionsList;

    @FXML
    void initialize() {
        DbHandler dbHandler = new DbHandler();

        for (Question question : dbHandler.getAllQuestions()) {
            questionsList.getItems().add(question.getText());
        }

        leaderboardLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    leaderboardLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(QuestionsController.class.getResource("leaderboardPage.fxml"));
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
                    loader.setLocation(QuestionsController.class.getResource("loginPage.fxml"));
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

        homeLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    homeLabel.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(QuestionsController.class.getResource("homePage.fxml"));
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

        questionsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int index = questionsList.getSelectionModel().getSelectedIndex();
                QuestionsHandler.question = dbHandler.getQuestionById(index + 1);
                questionLabel.setText(QuestionsHandler.question.getText());
                optionOneButton.setText(dbHandler.getOptionNameById(QuestionsHandler.question.getOptions().get(0)));
                optionTwoButton.setText(dbHandler.getOptionNameById(QuestionsHandler.question.getOptions().get(1)));
                optionThreeButton.setText(dbHandler.getOptionNameById(QuestionsHandler.question.getOptions().get(2)));
                optionFourButton.setText(dbHandler.getOptionNameById(QuestionsHandler.question.getOptions().get(3)));
            }
        });

        optionOneButton.setOnAction(actionEvent -> {
            if (checkAnswer(QuestionsHandler.question.getOptions().get(0))) {
                questionLabel.setText("YES YOU'RE RIGHT");
            } else {
                questionLabel.setText("nope, try again");
            }
        });
        optionTwoButton.setOnAction(actionEvent -> {
            if (checkAnswer(QuestionsHandler.question.getOptions().get(1))) {
                questionLabel.setText("YES YOU'RE RIGHT");
            } else {
                questionLabel.setText("nope, try again");
            }
        });
        optionThreeButton.setOnAction(actionEvent -> {
            if (checkAnswer(QuestionsHandler.question.getOptions().get(2))) {
                questionLabel.setText("YES YOU'RE RIGHT");
            } else {
                questionLabel.setText("nope, try again");
            }
        });
        optionFourButton.setOnAction(actionEvent -> {
            if (checkAnswer(QuestionsHandler.question.getOptions().get(3))) {
                questionLabel.setText("YES YOU'RE RIGHT");
            } else {
                questionLabel.setText("nope, try again");
            }
        });

    }

    public static boolean checkAnswer(Integer id) {
        DbHandler dbHandler = new DbHandler();
        if (QuestionsHandler.question.getCorrectAnswerIndex() == id) {
            dbHandler.addPointByUserId((long) CurrentUser.id);
            return true;
        } else {
            return false;
        }
    }
}
