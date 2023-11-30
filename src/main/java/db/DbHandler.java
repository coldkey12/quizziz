package db;

import models.CurrentUser;
import models.Question;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {
    Connection dbConnection;

    public DbHandler() {
    }

    ;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/quizziz";
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(connectionString, "root", "");
        return this.dbConnection;
    }

    public boolean login(String username, String password) {
        String insert = "SELECT * FROM users WHERE username =? AND password =? ";
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
                CurrentUser.id = resultSet.getInt("id");
                CurrentUser.username = resultSet.getString("username"); // eto koroche chtobi tipo polzovatel zashel v akkaunt i eto tipo sessiya
                CurrentUser.accessLevel = resultSet.getInt("accessLevel");
                CurrentUser.score = resultSet.getInt("score");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    public boolean checkExistance(String username) {
        String insert = "SELECT * FROM users WHERE username= ?";
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    public void addUser(String username, String password) {
        String insert = "INSERT INTO users(username,password,accessLevel,score) VALUES(?,?,1,0)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Question> getAllQuestions() {
        String insert = "SELECT * FROM questions";
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<Integer> options = new ArrayList<>();
                questions.add(new Question(resultSet.getLong("id"),
                        resultSet.getString("text"),
                        getOptionsById(resultSet.getInt("id")),
                        resultSet.getInt("correctAnswerIndex")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return questions;
    }

    public List<Integer> getOptionsById(Integer question_id) {
        String insert = "SELECT * FROM options WHERE question_id=?";
        List<Integer> options = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, question_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                options.add(resultSet.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return options;
    }

    public String getOptionNameById(Integer id) {
        String insert = "SELECT text FROM options WHERE id = ?";
        String name = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = new String();
                name = resultSet.getString("text");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    public Question getQuestionById(Integer id) {
        String insert = "SELECT * FROM questions WHERE id = ?";
        Question question = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                question = new Question(resultSet.getLong("id"),
                        resultSet.getString("text"),
                        getOptionsById(resultSet.getInt("id")),
                        resultSet.getInt("correctAnswerIndex"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    public void addPointByUserId(Long id) {
        String insert = "UPDATE users SET score = ? WHERE id = ?";
        try {
            int score = getUserById(id).getScore();
            score+=1;
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1,score);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public User getUserById(Long id) {
        String insert = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("accessLevel"),
                        resultSet.getInt("score"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> getAllUsersAsc(){
        String insert = "SELECT * FROM users ORDER BY score DESC";
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
