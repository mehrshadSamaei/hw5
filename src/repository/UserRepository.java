package repository;

import com.maktab.entity.User;
import util.ApplicationContext;

import java.sql.*;

public class UserRepository {
    private Connection connection;

    public UserRepository() {
    }

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException {
        String query = "insert into hw5db.user_table(fullName ,username,password,mobileNum,email) " +
                "values(?,?,?,?,?)";
        ApplicationContext context = new ApplicationContext();
        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getMobileNum());
        preparedStatement.setString(5, user.getEmailAddress());
        preparedStatement.executeUpdate();

    }

    public boolean checkUser(User user) throws SQLException {
        boolean userIn = false;

        String query = "select username, password from hw5db";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if (resultSet.getString("username").equals(user.getUserName())
                    && resultSet.getString("password").equals(user.getPassword())) {
                userIn = true;
            }

        }
        return userIn;
    }

}
