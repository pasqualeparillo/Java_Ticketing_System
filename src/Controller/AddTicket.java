package Controller;

import DAO.AgentsDAO;
import DAO.CustomersDAO;
import DAO.TicketDAO;
import Model.Agents;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * controller for adding tickets
 */
public class AddTicket implements Initializable {
    @FXML public TextField ticketDescription, ticketTitle, ticketNote;
    @FXML public ComboBox<String> ticketType, ticketStatus, ticketPriority, ticketCustomer, ticketAgent;

    private static String fxmlPath;

    /**
     * Saves a ticket
     * @param event
     */
    @FXML
    private void saveTicket(ActionEvent event) {
        if(validate() == false) {
            int customerID = 0;
            int agentID = 0;
            for(Customers c: CustomersDAO.getAllCustomers()) {
                if(Objects.equals(c.getName(), ticketCustomer.getValue())) {
                    customerID = c.getId();
                }
            }
            for(Agents a: AgentsDAO.getAllAgents()) {
                if(Objects.equals(a.getName(), ticketAgent.getValue())) {
                    agentID = a.getId();
                }
            }
            TicketDAO.addTicket(ticketTitle.getText(),ticketDescription.getText(), ticketNote.getText(), ticketType.getValue(), ticketStatus.getValue(), ticketPriority.getValue(), customerID, agentID);
            exitToMain(event);

        }
    }
    /**
     * Validated user input fields
     */
    private boolean validate() {
        if(
                ticketType.getValue() == null ||
                ticketStatus.getValue() == null ||
                ticketNote.getText().isEmpty() ||
                ticketPriority.getValue() == null ||
                ticketCustomer.getValue() == null ||
                ticketAgent.getValue() == null ||
                ticketDescription.getText().isEmpty()
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Missing field");
            alert.setContentText("Please be sure to fill out all fields.");
            alert.showAndWait();
            return true;
        } else {
            return false;
        }
    }
    /**
     * ExitPage helper to change scenes
     * @param event
     */
    @FXML
    private void exitToMain(ActionEvent event) {
        fxmlPath = "/View/MainScreen.fxml";
        switchScene(event, "Appointment Management");
    }
    /**
     * scene switching helper to change scenes
     * @param event
     * @param title
     */
    public void switchScene(ActionEvent event, String title)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * populates ticket fields
     */
    private void populateFields() {
        // Add ticket customers
        for(Customers c: CustomersDAO.getAllCustomers()) {
            String customer_name = c.getName();
            ticketCustomer.getItems().add(customer_name);
        }
        // Add ticket agents
        for(Agents a: AgentsDAO.getAllAgents()) {
            String agent_name = a.getName();
            ticketAgent.getItems().add(agent_name);
            System.out.println(agent_name);
        }
        // Add ticket priority
        ticketPriority.getItems().add("High");
        ticketPriority.getItems().add("Medium");
        ticketPriority.getItems().add("Low");
        // Add ticket Status
        ticketStatus.getItems().add("Open");
        ticketStatus.getItems().add("Closed");
        // Add ticket type
        ticketType.getItems().add("Software");
        ticketType.getItems().add("Hardware");
        ticketType.getItems().add("Outage");
        ticketType.getItems().add("Facility");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateFields();

    }
}
