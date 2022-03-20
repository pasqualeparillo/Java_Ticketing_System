package Controller;

import DAO.CustomersDAO;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomer implements Initializable {
    @FXML public TextField customerID, customerName, customerEmail, customerPhone;
    @FXML public ComboBox<String> customerDepartment;

    private static String fxmlPath;

    @FXML
    private void saveCustomer(ActionEvent event) {
        if(validate() == false) {
            CustomersDAO.updateCustomer(customerName.getText(), customerEmail.getText(), customerDepartment.getValue(), customerPhone.getText());
            exitToMain(event);
        }
    }

    private boolean validate() {
        if(
                customerName.getText().isEmpty() ||
                        customerDepartment.getValue() == null ||
                        customerEmail.getText().isEmpty() ||
                        customerPhone.getText().isEmpty()

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
    private void populateFields() {
        customerDepartment.getItems().add("IT");
        customerDepartment.getItems().add("Finance");
        customerDepartment.getItems().add("Marketing");
        customerDepartment.getItems().add("Human Resources");
        for(Customers c: CustomersDAO.getAllCustomers()) {
            if(c.getCustomer_ID() == MainScreen.getCustomerToModify().getCustomer_ID()) {
                customerDepartment.setValue(c.getDepartment());
            }
        }
        customerPhone.setText(MainScreen.getCustomerToModify().getPhone());
        customerEmail.setText(MainScreen.getCustomerToModify().getEmail());
        customerID.setText(Integer.toString(MainScreen.getCustomerToModify().getCustomer_ID()));
        customerName.setText(MainScreen.getCustomerToModify().getCustomer_Name());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateFields();
    }
}
