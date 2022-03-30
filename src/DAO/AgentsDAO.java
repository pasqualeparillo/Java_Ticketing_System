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
 * used to handle sql queries for agents
 */
public class AgentsDAO {
    public static ObservableList<Agents> agentList = FXCollections.observableArrayList();

    public static ObservableList<Agents> getAllAgents() {
        getAgents();
        return agentList;
    }

    /**
     * gets all agents and adds them to list
     */
    public static void getAgents() {
        try {
            agentList.clear();
            String sql = "SELECT * FROM Agents";
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while(sqlQueryResult.next()) {
                int Agent_ID = sqlQueryResult.getInt("id");
                String Agent_Name = sqlQueryResult.getString("name");
                String Password = sqlQueryResult.getString("Password");
                String Email = sqlQueryResult.getString("email");
                Agents agents = new Agents(Agent_ID, Agent_Name, Password, Email);
                agentList.add(agents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * update Agent
     */
    public static void updateAgent(String Username,String Password, String Email) {
        try {
            String sql = String.format("Update Agents set name='%s', Password='%s', Email='%s' WHERE Agent_ID=%s", Username, Password, Email, MainScreen.getAgentToModify().getId());
            JDBC.getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * add Agents
     */
    public static void addAgent(String Username, String Password, String Email) {
        try {
            String sql = String.format("INSERT INTO Agents (name, Password, email) VALUES ('%s', '%s', '%s')", Username, Password, Email);
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
    public static ObservableList<Agents> deleteAgent() throws SQLException {
        String sql = String.format("DELETE FROM Agents WHERE Agent_ID=%s.", MainScreen.getAgentToModify().getId());
        try {
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            sqlQuery.executeUpdate();
            return getAllAgents();
        } catch (SQLException e) {
            e.printStackTrace();
            return getAllAgents();
        }
    }

}
