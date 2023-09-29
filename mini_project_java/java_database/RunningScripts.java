package mini_project_java.java_database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;





public class RunningScripts {
    public void runDbScript() throws Exception {

        // getting some error whole reading from db.sql file
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();

            // Drop tables
            statement.executeUpdate("DROP TABLE IF EXISTS orders");
            statement.executeUpdate("DROP TABLE IF EXISTS product");
            statement.executeUpdate("DROP TABLE IF EXISTS category");

            // Create tables
            statement.executeUpdate("CREATE TABLE category(id int AUTO_INCREMENT PRIMARY KEY, type varchar(25))");
            statement.executeUpdate("CREATE TABLE product(id int AUTO_INCREMENT PRIMARY KEY, name varchar(20), price float, category_id int references category(id))");
            statement.executeUpdate("CREATE TABLE orders(id int AUTO_INCREMENT PRIMARY KEY, product_id int references product(id), order_date date)");

            System.out.println("Tables created successfully.");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

