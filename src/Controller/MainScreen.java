package Controller;

import DAO.AgentsDAO;
import DAO.TicketDAO;
import DAO.CustomersDAO;
import Model.Agents;
import Model.Tickets;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * controller for handling initial main page
 */
public class MainScreen implements Initializable {
    private static String fxmlPath;

    private static Customers customerToModify;
    private static Agents agentToModify;
    private static Tickets ticketToModify;

    @FXML private TextField ticketSearch;
    @FXML private TextField customerSearch;
    @FXML private TextField agentSearch;

    @FXML private TableView<Tickets> ticketsTable;
    @FXML private TableColumn<Tickets, Integer> Ticket_ID;
    @FXML private TableColumn<Tickets, Integer> Ticket_Customer_ID;
    @FXML private TableColumn<Tickets, Integer> Ticket_Agent_ID;
    @FXML private TableColumn<Tickets, String> Ticket_Title;
    @FXML private TableColumn<Tickets, String> Ticket_Description;
    @FXML private TableColumn<Tickets, String> Ticket_Note;
    @FXML private TableColumn<Tickets, String> Ticket_Status;
    @FXML private TableColumn<Tickets, String> Ticket_Type;
    @FXML private TableColumn<Tickets, String> Ticket_Priority;

    @FXML private TableView<Customers> customersTable;
    @FXML private TableColumn<Customers, Integer> Customer_ID;
    @FXML private TableColumn<Customers, String> Customer_Name;
    @FXML private TableColumn<Customers, String> Customer_Email;
    @FXML private TableColumn<Customers, String> Customer_Phone_Number;


    @FXML private TableView<Agents> agentsTable;
    @FXML private TableColumn<Agents, String> Agent_Name, Agent_Email;

    /**
     * Sets tickets table
     */
    private void setTicketsTable() {
        ticketsTable.setItems(TicketDAO.getAllTickets());
        Ticket_ID.setCellValueFactory(new PropertyValueFactory<>("Ticket_ID"));
        Ticket_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Ticket_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Ticket_Note.setCellValueFactory(new PropertyValueFactory<>("Note"));
        Ticket_Priority.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        Ticket_Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        Ticket_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Ticket_Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        Ticket_Agent_ID.setCellValueFactory(new PropertyValueFactory<>("Agent_ID"));
    }
    /**
     * Sets customers table
     */
    private void setCustomersTable() {
        customersTable.setItems(CustomersDAO.getAllCustomers());
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Customer_Phone_Number.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Customer_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    /**
     * Sets agents table
     */
    private void setAgentsTable() {
        agentsTable.setItems(AgentsDAO.getAllAgents());
        Agent_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Agent_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTicketsTable();
        setCustomersTable();
        setAgentsTable();
    }
    /**
     * Opens reports by customer scene
     */
    @FXML
    public void reportByUser(ActionEvent actionEvent) {
        fxmlPath = "/View/UserReport.fxml";
        switchScene(actionEvent, "Reports By User");
    }
    /**
     * Opens reports by type scene
     */
    @FXML
    public void reportByType(ActionEvent actionEvent) {
        fxmlPath = "/View/TypeReport.fxml";
        switchScene(actionEvent, "Reports By Type");
    }
    /**
     * Opens reports by urgency scene
     */
    @FXML
    public void reportByUrgency(ActionEvent actionEvent) {
        fxmlPath = "/View/UrgencyReport.fxml";
        switchScene(actionEvent, "Reports By Urgency");
    }
    /**
     * Opens add customer scene
     * @param actionEvent
     */
    @FXML
    public void addCustomer(ActionEvent actionEvent) {
        fxmlPath = "/View/AddCustomer.fxml";
        switchScene(actionEvent, "Add Customer");
    }
    /**
     * Opens modify customer scene
     * @param actionEvent
     */
    @FXML
    public void modifyCustomer(ActionEvent actionEvent) {
        fxmlPath = "/View/ModifyCustomer.fxml";
        Customers selectedItem = customersTable.getSelectionModel().getSelectedItem();
        customerToModify = selectedItem;
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        if(selectedItem != null) {
            switchScene(actionEvent, "Modify Customer");

        } else {
            alertConfirm.setTitle("Please confirm");
            alertConfirm.setContentText("Either you did not select a customer or they currently have a ticket assigned to them");
            alertConfirm.showAndWait();
        }
    }

    /**
     * returns customer selected on main screen
     * @return
     */
    public static Customers getCustomerToModify() {
        return customerToModify;
    }

    /**
     * Removed selected customer
     * @param actionEvent
     */
    @FXML
    public void deleteCustomer(ActionEvent actionEvent) throws SQLException {
        Customers selectedItem = customersTable.getSelectionModel().getSelectedItem();
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        customerToModify = selectedItem;
        ObservableList<Tickets> customerTickets = TicketDAO.getTicketsByUser(customerToModify.getId());
        if(selectedItem != null && customerTickets == null) {
            alertConfirm.setTitle("Please Confirm");
            alertConfirm.setContentText("Are you sure you would like to delete this customer");
            Optional<ButtonType> result = alertConfirm.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                CustomersDAO.deleteCustomer();
            }
        } else {
            alertConfirm.setTitle("Either you did not select a customer or none available");
            alertConfirm.setContentText("Please confirm");
            alertConfirm.showAndWait();
        }
    }
    /**
     * Opens add Ticket scene
     * @param actionEvent
     */
    @FXML
    public void addTicket(ActionEvent actionEvent) {
        fxmlPath = "/View/AddTicket.fxml";
        switchScene(actionEvent, "Add Ticket");
    }
    /**
     * Opens modify Ticket scene
     * @param actionEvent
     */
    @FXML
    public void modifyTicket(ActionEvent actionEvent) {
        fxmlPath = "/View/ModifyTicket.fxml";
        Tickets selectedItem = ticketsTable.getSelectionModel().getSelectedItem();
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        ticketToModify = selectedItem;
        if(selectedItem != null) {
            switchScene(actionEvent, "Modify Ticket");
        } else {
            alertConfirm.setTitle("Please confirm");
            alertConfirm.setContentText("Either you did not select a ticket or none available");
            alertConfirm.showAndWait();
        }

    }


    /**
     * returns ticket selected on main screen
     * @return
     */
    public static Tickets getTicketToModify() {
        return ticketToModify;
    }

    /**
     * Removed selected ticket
     * @param actionEvent
     */
    @FXML
    public void deleteTicket(ActionEvent actionEvent) throws SQLException {
        Tickets selectedItem = ticketsTable.getSelectionModel().getSelectedItem();
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        ticketToModify = selectedItem;
        if(selectedItem != null) {
            alertConfirm.setTitle("Please confirm");
            alertConfirm.setContentText("Are you sure you would like to delete this ticket");
            Optional<ButtonType> result = alertConfirm.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                TicketDAO.deleteTicket();
            }
        } else {
            alertConfirm.setTitle("Either you did not select a ticket or none available");
            alertConfirm.setContentText("Please confirm");
            alertConfirm.showAndWait();
        }
    }
    @FXML
    public void addAgent(ActionEvent actionEvent) {
        fxmlPath = "/View/AddAgent.fxml";
        switchScene(actionEvent, "Add Agent");
    }
    /**
     * Opens modify customer scene
     * @param actionEvent
     */
    @FXML
    public void modifyAgent(ActionEvent actionEvent) {
        fxmlPath = "/View/ModifyAgent.fxml";
        Agents selectedItem = agentsTable.getSelectionModel().getSelectedItem();
        agentToModify = selectedItem;
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        if(selectedItem != null) {
            switchScene(actionEvent, "Modify Agent");
        } else {
            alertConfirm.setTitle("Please confirm");
            alertConfirm.setContentText("Either you did not select an agent or none available");
            alertConfirm.showAndWait();
        }

    }
    /**
     * returns ticket selected on main screen
     * @return
     */
    public static Agents getAgentToModify() {
        return agentToModify;
    }

    /**
     * Removed selected ticket
     * @param actionEvent
     */
    @FXML
    public void deleteAgent(ActionEvent actionEvent) throws SQLException {
        Agents selectedItem = agentsTable.getSelectionModel().getSelectedItem();
        agentToModify = selectedItem;
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        ObservableList<Tickets> agentTickets = TicketDAO.getTicketsByAgent(agentToModify.getId());
        if(selectedItem != null && agentTickets == null) {
            alertConfirm.setTitle("Are you sure you would like to delete this agent");
            alertConfirm.setContentText("Please confirm");
            Optional<ButtonType> result = alertConfirm.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                AgentsDAO.deleteAgent();
            }
        } else {
            alertConfirm.setTitle("Please confirm");
            alertConfirm.setContentText("Either you did not select an agent or the agent has a ticket assigned to them");
            alertConfirm.showAndWait();
        }
    }
    /**
     * Search method - if search is not null search for a part || if it is null return all parts calls searchForPart & pass's value
     */
    @FXML
    private void searchForTicket() {
        if(ticketSearch.getText() != null) {
            if (!ticketSearch.getText().trim().isEmpty()) {
                ticketsTable.setItems(searchForTicket(ticketSearch.getText()));
            } else{
                ticketsTable.setItems(TicketDAO.getAllTickets());
            }
        }
    }
    /**
     * Search method - checks if search value is an int or string -> calls search methods for either depending. Returns result
     * @param search - string or integer you would like to search for
     */
    private ObservableList<Tickets> searchForTicket(String search) {
        ObservableList<Tickets> foundTickets = FXCollections.observableArrayList();
        try {
            if(isInt(search)) {
                int searchedInt = Integer.parseInt(search);
                for(Tickets t: TicketDAO.getAllTickets()) {
                    if(t.getTicket_ID() == searchedInt || t.getCustomer_ID() == searchedInt) {
                        foundTickets.add(t);
                    }
                }
            } else {
                for(Tickets t: TicketDAO.getAllTickets()) {
                    String status = t.getStatus().toLowerCase(Locale.ROOT);
                    String searchParam = search.toLowerCase(Locale.ROOT);
                    if(status.contains(searchParam) || t.getNote().toLowerCase(Locale.ROOT).contains(search) || t.getDescription().toLowerCase(Locale.ROOT).contains(search) || t.getType().toLowerCase(Locale.ROOT).contains(search) ||  t.getTitle().toLowerCase(Locale.ROOT).contains(search)) {
                        foundTickets.add(t);
                    }
                }
            }
        }
        catch  (NumberFormatException e) {
            System.out.println(e);
        }
        return foundTickets;
    }

    /**
     * Search method - if search is not null search for a part || if it is null return all parts calls searchForPart & pass's value
     */
    @FXML
    private void searchForCustomers() {
        if(customerSearch.getText() != null) {
            if (!customerSearch.getText().trim().isEmpty()) {
                customersTable.setItems(searchForCustomer(customerSearch.getText()));
            } else{
                customersTable.setItems(CustomersDAO.getAllCustomers());
            }
        }
    }
    /**
     * Search method - checks if search value is an int or string -> calls search methods for either depending. Returns result
     * @param search - string or integer you would like to search for
     */
    private ObservableList<Customers> searchForCustomer(String search) {
        ObservableList<Customers> foundCustomers = FXCollections.observableArrayList();
        ObservableList<Customers> allCustomerList = CustomersDAO.getAllCustomers();
        try {
            if(isInt(search)) {
                int searchedInt = Integer.parseInt(search);
                for(Customers c: allCustomerList) {
                    if(c.getId() == searchedInt) {
                        foundCustomers.add(c);
                    }
                }
            } else {
                for(Customers c: allCustomerList) {
                    String cname = c.getName().toLowerCase(Locale.ROOT);
                    String searchParam = search.toLowerCase(Locale.ROOT);
                    if(cname.contains(searchParam) || c.getEmail().toLowerCase(Locale.ROOT).contains(searchParam)) {
                        foundCustomers.add(c);
                    }
                }
            }
        }
        catch  (NumberFormatException e) {
            System.out.println(e);
        }
        return foundCustomers;
    }

    /**
     * Search method - if search is not null search for a part || if it is null return all parts calls searchForPart & pass's value
     */
    @FXML
    private void searchForAgent() {
        if(agentSearch.getText() != null) {
            if (!agentSearch.getText().trim().isEmpty()) {
                agentsTable.setItems(searchForAgent(agentSearch.getText()));
            } else{
                agentsTable.setItems(AgentsDAO.getAllAgents());
            }
        }
    }
    /**
     * Search method - checks if search value is an int or string -> calls search methods for either depending. Returns result
     * @param search - string or integer you would like to search for
     */
    private ObservableList<Agents> searchForAgent(String search) {
        ObservableList<Agents> foundAgents = FXCollections.observableArrayList();
        try {
            if(isInt(search)) {
                int searchedInt = Integer.parseInt(search);
                for(Agents a: AgentsDAO.getAllAgents()) {
                    if(a.getId() == searchedInt) {
                        foundAgents.add(a);
                    }
                }
            } else {
                for(Agents a: AgentsDAO.getAllAgents()) {
                    String name = a.getName().toLowerCase(Locale.ROOT);
                    String email = a.getEmail().toLowerCase(Locale.ROOT);
                    String searchParam = search.toLowerCase(Locale.ROOT);
                    if(name.contains(searchParam) || email.contains(searchParam)) {
                        foundAgents.add(a);
                    }
                }
            }
        }
        catch  (NumberFormatException e) {
            System.out.println(e);
        }
        return foundAgents;
    }
    /**
     * Helper function to switch scenes
     * @param title
     * @param event
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
     * Helper function used to check if a value is an integer or not
     * @param searchQuery - an event handler passed to change scene
     */
    public boolean isInt(String searchQuery) {
        try {
            Integer.parseInt(searchQuery);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    /**
     * Exit the program
     */
    @FXML
    public void exitProgram() {
        System.exit(0);
    }
}
