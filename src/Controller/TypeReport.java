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
 * controller for handling the ticket type report
 */
public class TypeReport implements Initializable {
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
    @FXML private ComboBox<String> typeList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeList.getItems().add("Software");
        typeList.getItems().add("Hardware");
        typeList.getItems().add("Outage");
        typeList.getItems().add("Facility");
        typeList.setValue("Software");
        try {
            setTicketsTable("Software");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the type of ticket to search for
     * @throws SQLException
     */
    @FXML
    private void changeType() throws SQLException {
        setTicketsTable(typeList.getValue());
    }

    /**
     * sets report table results
     * @param ticketType
     * @throws SQLException
     */
    private void setTicketsTable(String ticketType) throws SQLException {
        ticketsTable.setItems(TicketDAO.getTicketsByType(ticketType));
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
