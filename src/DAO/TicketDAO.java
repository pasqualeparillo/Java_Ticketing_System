package DAO;

import Controller.MainScreen;
import Database.JDBC;
import Model.Tickets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
/**
 * used to handle sql queries for tickets
 */
public class TicketDAO {
    public static ObservableList<Tickets> ticketsList = FXCollections.observableArrayList();

    public static ObservableList<Tickets> getAllTickets() {
        getTickets();
        return ticketsList;
    }

    /**
     * gets all tickets
     */
    public static void getTickets() {
        try {
           ticketsList.clear();
           String sql = "SELECT * FROM Tickets";
           PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
           ResultSet sqlQueryResult = sqlQuery.executeQuery();
           while(sqlQueryResult.next()) {
               int Ticket_ID = sqlQueryResult.getInt("Ticket_ID");
               int Agent_ID = sqlQueryResult.getInt("Agent_ID");
               int Customer_ID = sqlQueryResult.getInt("Customer_ID");
               String Priority = sqlQueryResult.getString("Priority");
               String Title = sqlQueryResult.getString("Title");
               String Description = sqlQueryResult.getString("Description");
               String Note = sqlQueryResult.getString("Note");
               String Type = sqlQueryResult.getString("Type");
               String Status = sqlQueryResult.getString("Status");
               Tickets tickets = new Tickets(Ticket_ID, Title, Description,Note, Type, Status, Priority, Customer_ID, Agent_ID);
               ticketsList.add(tickets);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * returns tickets by customer_ID
     * @return
     */
    public static ObservableList<Tickets> getTicketsByUser(int customer_ID) {
        String sql = String.format("SELECT * FROM Tickets WHERE Customer_ID=%s.", customer_ID);
        try {
            ticketsList.clear();
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while (sqlQueryResult.next()) {
                int Ticket_ID = sqlQueryResult.getInt("Ticket_ID");
                int Agent_ID = sqlQueryResult.getInt("Agent_ID");
                int Customer_ID = sqlQueryResult.getInt("Customer_ID");
                String Priority = sqlQueryResult.getString("Priority");
                String Title = sqlQueryResult.getString("Title");
                String Description = sqlQueryResult.getString("Description");
                String Note = sqlQueryResult.getString("Note");
                String Type = sqlQueryResult.getString("Type");
                String Status = sqlQueryResult.getString("Status");
                Tickets tickets = new Tickets(Ticket_ID, Title, Description,Note, Type, Status, Priority, Customer_ID, Agent_ID);
                ticketsList.add(tickets);
                return ticketsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * returns tickets by customer_ID
     * @return
     */
    public static ObservableList<Tickets> getTicketsByAgent(int agent_ID) {
        String sql = String.format("SELECT * FROM Tickets WHERE Agent_ID=%s.", agent_ID);
        try {
            ticketsList.clear();
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while (sqlQueryResult.next()) {
                int Ticket_ID = sqlQueryResult.getInt("Ticket_ID");
                int Agent_ID = sqlQueryResult.getInt("Agent_ID");
                int Customer_ID = sqlQueryResult.getInt("Customer_ID");
                String Priority = sqlQueryResult.getString("Priority");
                String Title = sqlQueryResult.getString("Title");
                String Description = sqlQueryResult.getString("Description");
                String Note = sqlQueryResult.getString("Note");
                String Type = sqlQueryResult.getString("Type");
                String Status = sqlQueryResult.getString("Status");
                Tickets tickets = new Tickets(Ticket_ID, Title, Description,Note, Type, Status, Priority, Customer_ID, Agent_ID);
                ticketsList.add(tickets);
                return ticketsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * returns tickets by type
     * @return
     */
    public static ObservableList<Tickets> getTicketsByType(String ticketType) {
        String sql = String.format("SELECT * FROM Tickets WHERE Type='%s'", ticketType);
        try {
            ticketsList.clear();
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while (sqlQueryResult.next()) {
                int Ticket_ID = sqlQueryResult.getInt("Ticket_ID");
                int Agent_ID = sqlQueryResult.getInt("Agent_ID");
                int Customer_ID = sqlQueryResult.getInt("Customer_ID");
                String Priority = sqlQueryResult.getString("Priority");
                String Title = sqlQueryResult.getString("Title");
                String Description = sqlQueryResult.getString("Description");
                String Note = sqlQueryResult.getString("Note");
                String Type = sqlQueryResult.getString("Type");
                String Status = sqlQueryResult.getString("Status");
                Tickets tickets = new Tickets(Ticket_ID, Title, Description,Note, Type, Status, Priority, Customer_ID, Agent_ID);
                ticketsList.add(tickets);
            }
            return ticketsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * returns tickets by urgency
     * @return
     */
    public static ObservableList<Tickets> getTicketsByUrgency(String urgency) {
        String sql = String.format("SELECT * FROM Tickets WHERE Priority='%s'", urgency);
        try {
            ticketsList.clear();
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while (sqlQueryResult.next()) {
                int Ticket_ID = sqlQueryResult.getInt("Ticket_ID");
                int Agent_ID = sqlQueryResult.getInt("Agent_ID");
                int Customer_ID = sqlQueryResult.getInt("Customer_ID");
                String Priority = sqlQueryResult.getString("Priority");
                String Title = sqlQueryResult.getString("Title");
                String Description = sqlQueryResult.getString("Description");
                String Note = sqlQueryResult.getString("Note");
                String Type = sqlQueryResult.getString("Type");
                String Status = sqlQueryResult.getString("Status");
                Tickets tickets = new Tickets(Ticket_ID, Title, Description,Note, Type, Status, Priority, Customer_ID, Agent_ID);
                ticketsList.add(tickets);
                return ticketsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * update Ticket
     */
    public static void updateTicket(String Title,String Description, String Note, String Type, String Status,String Priority, int Customer_ID, int Agent_ID, int Ticket_ID) {
        try {
            String sql = String.format(" Update Tickets set Title='%s', Description='%s', Note='%s', Type='%s', Status='%s', Priority='%s', Customer_ID=%s,Agent_ID=%s WHERE Ticket_ID=%s", Title, Description,Note, Type, Status, Priority,Customer_ID,Agent_ID, Ticket_ID);
            JDBC.getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * add Ticket
     */
    public static void addTicket(String Title,String Description, String Note, String Type, String Status,String Priority, int Customer_ID, int Agent_ID) {
        try {
            String sql = String.format("INSERT INTO Tickets (Title, Description, Note, Type, Status, Priority, Customer_ID,Agent_ID) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %s, %s)", Title, Description,Note, Type, Status, Priority,Customer_ID,Agent_ID);
            JDBC.getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * handles removing tickets
     * @return
     * @throws SQLException
     */
    public static ObservableList<Tickets> deleteTicket() throws SQLException {
        String sql = String.format("DELETE FROM Tickets WHERE Ticket_ID=%s.", MainScreen.getTicketToModify().getTicket_ID());
        try {
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            sqlQuery.executeUpdate();
            return getAllTickets();
        } catch (SQLException e) {
            e.printStackTrace();
            return getAllTickets();
        }
    }
}
