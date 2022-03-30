package Controller;

import DAO.CustomersDAO;
import DAO.TicketDAO;
import Model.Customers;
import Model.Tickets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * controller for handling the ticket user report
 */
public class UserReport implements Initializable {
    private String fxmlPath;
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
    @FXML private ComboBox<String> customerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Customers c: CustomersDAO.getAllCustomers()) {
            customerList.setValue(c.getName());
            customerList.getItems().add(c.getName());
            try {
                setTicketsTable(c.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * populates the report table with results
     * @param customer_ID
     * @throws SQLException
     */
    private void setTicketsTable(int customer_ID) throws SQLException {
        ticketsTable.setItems(TicketDAO.getTicketsByUser(customer_ID));
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
     * Sets the ticket customer to search for
     * @throws SQLException
     */
    @FXML
    private void changeUser() throws SQLException {
        int customer_ID = 0;
        String Customer_Name = customerList.getValue();
        for(int i=0; i < CustomersDAO.customersList.size(); i++) {
            if(CustomersDAO.customersList.get(i).getName() == Customer_Name) {
                customer_ID = CustomersDAO.customersList.get(i).getId();
            }
        }
        setTicketsTable(customer_ID);
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
     * ExitPage helper to change scenes
     * @param event
     */
    @FXML
    private void exitToMain(ActionEvent event) {
        fxmlPath = "/View/MainScreen.fxml";
        switchScene(event, "Appointment Management");
    }
}
