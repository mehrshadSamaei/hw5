package repository;

import util.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepository {
    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public OrderRepository() {
    }

    public void insertIntoOrder(int product_id, int user_id) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ApplicationContext();
        // find rows with same user_id and product_id
        int sameRows = 0;
        String findRowsQuery = "select * from hw5db.order where product_id = ? and user_id = ?";
        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(findRowsQuery);
        preparedStatement.setInt(1, product_id);
        preparedStatement.setInt(2, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt("product_id") == product_id
                    && resultSet.getInt("user_id") == user_id) {
                sameRows++;
                break;
            }
        }
        //
        //System.out.println("sameRows: "+sameRows);
        if (sameRows == 0) {
            String query = "insert into hw5db.order(product_id,numbers,user_id) values(?,?,?)";

            preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, product_id);
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, user_id);
            preparedStatement.executeUpdate();

        }
    }

    public void updateOrder(int product_id, int user_id, boolean firstTime) throws SQLException, ClassNotFoundException {
        int productsInCart = 1;
        ApplicationContext context = new ApplicationContext();
        String selectNumbers = "select numbers from hw5db.order where product_id = ? and user_id = ?";
        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(selectNumbers);
        preparedStatement.setInt(1, product_id);
        preparedStatement.setInt(2, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!firstTime) {
            while (resultSet.next()) {
                productsInCart = resultSet.getInt("numbers") + 1;
                //System.out.println("productsInCart: "+productsInCart);
                String addProductNumber = "update hw5db.order set numbers = ? where product_id = ? and user_id = ?";
                preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(addProductNumber);
                preparedStatement.setInt(1, productsInCart);
                preparedStatement.setInt(2, product_id);
                preparedStatement.setInt(3, user_id);
                preparedStatement.executeUpdate();

            }
        }

        showOrder(product_id, user_id);
    }

    public void showOrder(int product_id, int user_id) throws SQLException, ClassNotFoundException {
        System.out.println("==============");
        System.out.println("Your Order:");

        ApplicationContext context = new ApplicationContext();
        String query = "select p.title,c.numbers,u.id from hw5db.order as c " +
                "join hw5db.product as p on c.product_id = p.id " +
                "join hw5db.user_table as u on c.user_id = u.id";

        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt("id") == user_id) {
                System.out.print(resultSet.getString("title") + "\t");
                System.out.println("Numbers: " + resultSet.getInt("numbers") + "\t");
            }

        }

    }

    public void removeProductFromOrder(int product_id, int user_id) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ApplicationContext();
        System.out.println("Select product to remove from order:");
        int productId = context.getIntSc().nextInt();
        String quer = "select * from having hw5db.order where product_id = ? and user_id = ?";
        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(quer);
        preparedStatement.setInt(1, productId);
        preparedStatement.setInt(2, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getInt("numbers") == 1) {
                String query = "delete from hw5db.order where product_id = ? and user_id = ?";
                preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
                preparedStatement.setInt(1, productId);
                preparedStatement.setInt(2, user_id);
                preparedStatement.execute();
                break;
            } else {
                int newNumber = resultSet.getInt("numbers") - 1;
                String query = "update hw5db.order set numbers = ? where product_id = ? and user_id = ?";
                preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
                preparedStatement.setInt(1, newNumber);
                preparedStatement.setInt(2, productId);
                preparedStatement.setInt(3, user_id);
                preparedStatement.executeUpdate();
                break;
            }
        }
        //context = new ApplicationContext();
        showOrder(product_id, user_id);
    }

    public void emptyOrder(int user_id) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ApplicationContext();
        String query = "delete from hw5db.order where user_id = ?";
        PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
    }


}
