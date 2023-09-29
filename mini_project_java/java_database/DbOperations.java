package mini_project_java.java_database;

import java.sql.*;
import java.util.ArrayList;


public class DbOperations {
    public boolean insertCategory(String type) throws SQLException{
        Connection connection = DbUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO category (type) VALUES (?)");
            preparedStatement.setString(1, type);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
        }
    }

    public ArrayList<?> getCategoryById(int id) throws SQLException {
        ArrayList<Object> categories = new ArrayList();
        Connection connection = DbUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category WHERE id = ? order by id asc");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                categories.add(id1);
                String type = resultSet.getString("type");
                categories.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return categories;
    }

    public ResultSet getAllCategory() {
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM category order by id asc");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertProduct(String name, float price, String category_id) {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM category WHERE type = ?");
            preparedStatement.setString(1, category_id);

            int id123=1;
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id123 = resultSet.getInt("id");
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO product (name, price, category_id) VALUES (?, ?, ?)");
            preparedStatement2.setString(1, name);
            preparedStatement2.setFloat(2, price);
            preparedStatement2.setInt(3, id123);
            int rowsInserted = preparedStatement2.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Object> getProductById(int id) {
        ArrayList<Object> products = new ArrayList();
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                Integer cat_id = resultSet.getInt("category_id");
                products.add(id1);
                products.add(name);
                products.add(price);
                products.add(cat_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ResultSet getAllProduct() {
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM product");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertOrder(String product_name, Date date) {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (product_id, order_date) VALUES (?, ?)");
            int product_id = getProductIDByName(product_name);
            if (product_id != -1) {
                preparedStatement.setInt(1, product_id);
                preparedStatement.setDate(2, date);
                int rowsInserted = preparedStatement.executeUpdate();
                return rowsInserted > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getOrderById(int id) {
        ArrayList<String> orders = new ArrayList();
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                Date order_date = resultSet.getDate("order_date");
                String product_name = getProductNameByID(product_id);
                orders.add("Product: " + product_name + ", Order Date: " + order_date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public ResultSet getAllOrder() {
        try {
            Connection connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM orders order by id asc");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getProductIDByName(String name) {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM product WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1; // Product not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String getProductNameByID(int id) {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return ""; // Product name not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
}