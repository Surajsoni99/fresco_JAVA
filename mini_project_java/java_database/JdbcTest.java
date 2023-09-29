package mini_project_java.java_database;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcTest {
    DbOperations dbOp = new DbOperations();
    @org.junit.Test
    public void test1() throws Exception{
        RunningScripts scr = new RunningScripts();
        scr.runDbScript();

        dbOp.insertCategory("Fruit");
        dbOp.insertCategory("Vegetable");
        dbOp.insertCategory("Dairy");
        ArrayList record = dbOp.getCategoryById(2);
        assertEquals(record.get(0), 2);
        assertEquals(record.get(1), "Vegetable");
        ResultSet rs = dbOp.getAllCategory();
        rs.next();
        assertEquals(rs.getInt("id"),1);
        assertEquals(rs.getString("type"),"Fruit");
        rs.next();
        assertEquals(rs.getInt("id"),2);
        assertEquals(rs.getString("type"),"Vegetable");
        rs.next();
        assertEquals(rs.getInt("id"),3);
        assertEquals(rs.getString("type"),"Dairy");
    }
    @org.junit.Test
    public void test2() throws Exception{
        ResultSet rs = DbUtil.getConnection().createStatement().executeQuery("select id, type from category");
        rs.next();
        assertEquals(rs.getInt("id"),1);
        assertEquals(rs.getString("type"),"Fruit");
        rs.next();
        assertEquals(rs.getInt("id"),2);
        assertEquals(rs.getString("type"),"Vegetable");
        rs.next();
        assertEquals(rs.getInt("id"),3);
        assertEquals(rs.getString("type"),"Dairy");
    }
    public void helper1(ResultSet rs) throws SQLException {
        rs.next();
        assertEquals(rs.getInt("id"),1);
        assertEquals(rs.getString("name"),"Brinjal");
        assertEquals((int)rs.getFloat("price"),67);
        assertEquals(rs.getInt("category_id"), 2);
        rs.next();
        assertEquals(rs.getInt("id"),2);
        assertEquals(rs.getString("name"),"Apple");
        assertEquals((int)rs.getFloat("price"),120);
        assertEquals(rs.getInt("category_id"), 1);
        rs.next();
        assertEquals(rs.getInt("id"),3);
        assertEquals(rs.getString("name"),"Milk");
        assertEquals((int)rs.getFloat("price"),25);
        assertEquals(rs.getInt("category_id"), 3);
        rs.next();
        assertEquals(rs.getInt("id"),4);
        assertEquals(rs.getString("name"),"Curd");
        assertEquals((int)rs.getFloat("price"),20);
        assertEquals(rs.getInt("category_id"), 3);
    }
    @org.junit.Test
    public void test3() throws Exception{
        dbOp.insertProduct("Brinjal", 67, "Vegetable");
        dbOp.insertProduct("Apple", 120, "Fruit");
        dbOp.insertProduct("Milk", 25, "Dairy");
        dbOp.insertProduct("Curd", 20, "Dairy");
        ArrayList record = dbOp.getProductById(2);
        assertEquals(record.get(0), 2);
        assertEquals(record.get(1), "Apple");
        assertEquals(record.get(2), 120.0f);
        assertEquals(record.get(3), 1);
        ResultSet rs = dbOp.getAllProduct();
        helper1(rs);
    }
    @org.junit.Test
    public void test4() throws Exception{
        ResultSet rs = DbUtil.getConnection().createStatement().executeQuery("select id, name, price, category_id from product");
        helper1(rs);
    }
    public void test5() throws Exception{
        dbOp.insertOrder("Apple", Date.valueOf("2019-01-19"));
        dbOp.insertOrder("Milk", Date.valueOf("2018-02-20"));
        ArrayList record = dbOp.getOrderById(2);
        assertEquals(record.get(0), 2);
        assertEquals(record.get(1), 3);
        assertEquals(record.get(2), Date.valueOf("2018-02-20"));
        ResultSet rs = dbOp.getAllOrder();
        rs.next();
        assertEquals(rs.getInt("id"),1);
        assertEquals(rs.getInt("product_id"),2);
        assertEquals(rs.getDate("order_date"), Date.valueOf("2019-01-19"));
        rs.next();
        assertEquals(rs.getInt("id"),2);
        assertEquals(rs.getInt("product_id"),3);
        assertEquals(rs.getDate("order_date"), Date.valueOf("2018-02-20"));
    }
}