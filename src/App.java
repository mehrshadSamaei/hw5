import util.ApplicationContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ApplicationContext();
        boolean addOrRem = true;

        Register(context);
        context.getUserRepository().insertUser(context.getUser());
        //
        int user_id = settingForeignKey(context);
        //

        context.getAddressRepository().insertAddress(context, user_id);
        loginMenu();
        context.getMenu().showSuccessfully();
        context.getMenu().showWelcome();
        context.getMenu().showProductsList();
        context.getMenu().showSelectProduct();

        int select = context.getIntSc().nextInt();

        context.getProductRepository().calculateAvailableProducts(select, addOrRem);

        //show carts
        settingCart(context, select, user_id);
        //
        selectInCartMenu(context, select, addOrRem, user_id);
    }


    public static int userIdForFK(ApplicationContext context) throws SQLException, ClassNotFoundException {
        Statement statement = context.getDatabaseUtil().getConnection().createStatement();
        String query = "select * from hw5db.user_table order by id";
        ResultSet resultSet = statement.executeQuery(query);
        //System.out.println("username: "+user.getUsername());
        int user_id = 0;
        while (resultSet.next()) {
            if (context.getUser().getUserName() != null) {
                if (context.getUser().getUserName().equals(resultSet.getString("username"))) {
                    user_id = resultSet.getInt("id");
                    return user_id;
                }
            }
        }
        return user_id;
    }

    public static void selectInCartMenu(ApplicationContext context, int select, boolean addOrRem, int user_id) throws SQLException, ClassNotFoundException {

        while (true) {

            context.getMenu().orderMenu();

            int choose = context.getIntSc().nextInt();

            if (choose == 1) {
                context.getMenu().showProductsList();
                context.getMenu().showSelectProduct();
                select = context.getIntSc().nextInt();
                context.getProductRepository().calculateAvailableProducts(select, addOrRem);
                context.getOrderRepository().insertIntoOrder(select, user_id);
                context.getOrderRepository().updateOrder(select, user_id, false);
            }

            if (choose == 2) {
                addOrRem = false;
                context.getOrderRepository().removeProductFromOrder(select, user_id);
                context.getProductRepository().calculateAvailableProducts(select, addOrRem);

            }

            if (choose == 3) {
                context.getOrderRepository().emptyOrder(user_id);
                System.out.println("Done!");
                System.exit(0);
            }

            if (choose == 4)
                loginMenu();
        }
    }

    public static void Register(ApplicationContext context) throws SQLException, ClassNotFoundException {
        context.getMenu().showGetUserName();
        String username = context.getStSc().nextLine();

        context.getMenu().showGetUserfullName();
        String fullName = context.getStSc().nextLine();

        context.getMenu().showGetPassword();
        String password = context.getStSc().nextLine();

        context.getMenu().showGetState();
        String state = context.getStSc().nextLine();

        context.getMenu().showGetCity();
        String city = context.getStSc().nextLine();

        context.getMenu().showGetStreet();
        String street = context.getStSc().nextLine();

        context.getMenu().showGetPhone();
        String phoneNumber = context.getStSc().nextLine();

        context.getMenu().showGetEmail();
        String email = context.getStSc().nextLine();

        context.getMenu().showGetPostalCode();
        String postalCode = context.getStSc().nextLine();

        context.getUser().setFullName(fullName);
        context.getUser().setUserName(username);
        context.getUser().setPassword(password);
        context.getUser().setEmailAddress(email);
        context.getUser().setMobileNum(phoneNumber);
        context.getUser().setAddress(1, context.getUser().getId(), state, city, street, postalCode);


    }

    public static int settingForeignKey(ApplicationContext context) throws SQLException, ClassNotFoundException {
//        App application = new App();
//        ApplicationContext context = new ApplicationContext();

        int user_id = App.userIdForFK(context);
        return user_id;
    }

    public static void settingCart(ApplicationContext context, int select, int user_id) throws SQLException, ClassNotFoundException {
        context.getOrderRepository().insertIntoOrder(select, user_id);
        context.getOrderRepository().updateOrder(select, user_id, true);
    }

    public static void loginMenu() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ApplicationContext();
        while (true) {
            System.out.println("username:");
            String username = context.getStSc().nextLine();
            System.out.println("password:");
            String password = context.getStSc().nextLine();
            String query = "select * from hw5db.user_table where username = '" + (username) + "' and password = '" + (password) + "'";
            PreparedStatement preparedStatement = context.getDatabaseUtil().getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            boolean userIsIn = true;
            boolean addOrRem = true;
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)) {
                    userIsIn = true;
                    context.getMenu().showSuccessfully();
                    context.getUser().setFullName(resultSet.getString("fullName"));
                    context.getUser().setPassword(resultSet.getString("password"));
                    context.getUser().setMobileNum(resultSet.getString("mobileNum"));
                    context.getUser().setEmailAddress(resultSet.getString("email"));
                    context.getUser().setId(resultSet.getInt("id"));
                    context.getMenu().showWelcome();
                    context.getMenu().showProductsList();
                    context.getMenu().showSelectProduct();
                    int select = context.getIntSc().nextInt();
                    int user_id = context.getUser().getId();
                    context.getProductRepository().calculateAvailableProducts(select, addOrRem);

                    //show order
                    settingCart(context, select, user_id);
                    //
                    selectInCartMenu(context, select, addOrRem, user_id);
                }
            }
            if (userIsIn)
                System.out.println("Wrong user or password!");
        }
    }
}
