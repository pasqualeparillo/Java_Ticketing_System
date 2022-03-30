package DAO;

import Controller.MainScreen;
import Database.JDBC;
import Model.Agents;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * used to handle sql queries for customers
 */
public class CustomersDAO {
    public static ObservableList<Customers> customersList = FXCollections.observableArrayList();

    public static ObservableList<Customers> getAllCustomers() {
        getCustomers();
        return customersList;
    }

    /**
     * gets all customers
     */
    public static void getCustomers() {
        try {
            customersList.clear();
            String sql = "SELECT * FROM Customers";
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while(sqlQueryResult.next()) {
                int Customer_ID = sqlQueryResult.getInt("id");
                String Customer_Name = sqlQueryResult.getString("name");
                String Email = sqlQueryResult.getString("email");
                String Department = sqlQueryResult.getString("Department");
                String Phone = sqlQueryResult.getString("Phone");
                Customers customers = new Customers(Customer_ID, Customer_Name,Email, Department, Phone);
                customersList.add(customers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * update Customer
     */
    public static void updateCustomer(String Customer_Name, String Email, String Department, String Phone) {
        try {
            String sql = String.format(" Update Tickets set name='%s', Email='%s', Department='%s', Phone='%s' WHERE id=%s", Customer_Name, Email, Department, Phone, MainScreen.getCustomerToModify().getId());
            JDBC.getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * add Customer
     */
    public static void addCustomer(String Customer_Name, String Email, String Department, String Phone) {
        try {
            String sql = String.format("INSERT INTO Customers (name, Email, Department, Phone) VALUES ('%s', '%s', '%s', '%s')", Customer_Name, Email, Department, Phone);
            JDBC.getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * handles removing agents
     * @return
     * @throws SQLException
     */
    public static ObservableList<Customers> deleteCustomer() throws SQLException {
        String sql = String.format("DELETE FROM Customers WHERE id=%s.", MainScreen.getCustomerToModify().getId());
        try {
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            sqlQuery.executeUpdate();
            return getAllCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
            return getAllCustomers();
        }
    }
}
