package repository;

import util.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressRepository {
    private Connection connection;

    public AddressRepository(Connection connection) {
        this.connection = connection;
    }

    public AddressRepository() {

    }

    public void insertAddress(ApplicationContext context, int user_id) throws SQLException, ClassNotFoundException {
        String query = "insert into hw5db.address (state,city,street,postal_code,user_id) " +
                "values(?,?,?,?,?)";
//        ApplicationContext context = new ApplicationContext();
        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
        preparedStatement.setString(1, context.getUser().getAddress().getState());
        preparedStatement.setString(2, context.getUser().getAddress().getCity());
        preparedStatement.setString(3, context.getUser().getAddress().getStreet());
        preparedStatement.setString(4, context.getUser().getAddress().getPostalCode());
        preparedStatement.setInt(5, user_id);
        System.out.println("user_id: " + user_id);
        preparedStatement.executeUpdate();
    }
}
