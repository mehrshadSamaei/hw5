package repository;

import com.maktab.entity.Product;
import util.DatabaseUtil;

import java.sql.*;

public class ProductRepository {
    private Connection connection;
    private Product product;

    public Connection getConnection() {
        return connection;
    }

    public void showGetProducts() throws SQLException, ClassNotFoundException {
        DatabaseUtil databaseUtil = new DatabaseUtil();
        Statement statement = databaseUtil.getConnection().createStatement();
        String query = "select * from hw5db.product";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.print(resultSet.getInt("id") + ". ");
            System.out.println(resultSet.getString("title"));
            System.out.println("Available: " + resultSet.getInt("counter"));
            System.out.println("Price: " + resultSet.getInt("price"));
            System.out.println("==============");
        }
    }


    public void calculateAvailableProducts(int product_id, boolean addOrRem) throws SQLException, ClassNotFoundException {
        int availableProductUpdate = 0;
        String updateSql = "";
        DatabaseUtil databaseUtil = new DatabaseUtil();
        Statement statement = databaseUtil.getConnection().createStatement();
        String query = "select * from hw5db.product";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            if (product_id == resultSet.getInt("id")) {
                updateSql = "update hw5db.product set counter = ? where id = ?";

                if (addOrRem == true) {
                    if (resultSet.getInt("counter") != 0)
                        availableProductUpdate = resultSet.getInt("counter") - 1;
                }

                if (addOrRem == false) {
                    availableProductUpdate = resultSet.getInt("counter") + 1;
                }


                PreparedStatement preparedStatement = databaseUtil.getConnection().prepareStatement(updateSql);
                preparedStatement.setInt(1, availableProductUpdate);
                preparedStatement.setInt(2, product_id);
                preparedStatement.executeUpdate();
            }
        }
    }
}
