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

public class AgentsDAO {
    public static ObservableList<Agents> agentList = FXCollections.observableArrayList();

    public static ObservableList<Agents> getAllAgents() {
        getAgents();
        return agentList;
    }

    public static void getAgents() {
        try {
            agentList.clear();
            String sql = "SELECT * FROM Agents";
            PreparedStatement sqlQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet sqlQueryResult = sqlQuery.executeQuery();
            while(sqlQueryResult.next()) {
                int Agent_ID = sqlQueryResult.getInt("Agent_ID");
                String Agent_Name = sqlQueryResult.getString("Agent_Name");
                String Password = sqlQueryResult.getString("Password");
                Agents agents = new Agents(Agent_ID, Agent_Name, Password);
                agentList.add(agents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * update Agent
     */
    public static void updateAgent(String Username,String Password) {
        try {
            String sql = String.format("Update Agents set Agent_Name='%s', Password='%s' WHERE Agent_ID=%s", Username, Password, MainScreen.getAgentToModify().getAgent_ID());
            JDBC.getConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * add Agents
     */
    public static void addAgent(String Username, String Password) {
        try {
            String sql = String.format("INSERT INTO Agents (Agent_Name, Password) VALUES ('%s', '%s')", Username, Password);
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
        String sql = String.format("DELETE FROM Agents WHERE Agent_ID=%s.", MainScreen.getAgentToModify().getAgent_ID());
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
