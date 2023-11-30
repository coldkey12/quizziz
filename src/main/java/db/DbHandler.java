package db;

import models.CurrentUser;

import java.sql.*;

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
                CurrentUser.id = resultSet.getInt(1);
                CurrentUser.username = resultSet.getString(2);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

}
