package util;

import com.maktab.entity.User;
import repository.AddressRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationContext {
    private DatabaseUtil databaseUtil = new DatabaseUtil();
    private Connection connection;
    private Scanner intSc = new Scanner(System.in);
    private Scanner stSc = new Scanner(System.in);
    private Menu menu = new Menu();
    private UserRepository userRepository = new UserRepository();
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();
    private AddressRepository addressRepository = new AddressRepository();
    private User user = new User();

    public ApplicationContext() throws SQLException, ClassNotFoundException {
    }

    public User getUser() {
        return user;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public Menu getMenu() {
        return menu;
    }

    public Scanner getIntSc() {
        return intSc;
    }

    public Scanner getStSc() {
        return stSc;
    }

    public DatabaseUtil getDatabaseUtil() {
        return databaseUtil;
    }

    public void setDatabaseUtil(DatabaseUtil databaseUtil) {
        this.databaseUtil = databaseUtil;
    }

    public Connection getConnection() {

//        Connection connection = new Connection();
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
