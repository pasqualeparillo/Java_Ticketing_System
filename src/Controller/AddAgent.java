package Controller;

import DAO.AgentsDAO;
import DAO.CustomersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAgent {
    private static String fxmlPath;
    @FXML private TextField agentUsername, agentEmail;
    @FXML private PasswordField agentPassword;

    @FXML
    private void addAgent(ActionEvent event) {
        if(validate() == false) {
            AgentsDAO.addAgent(agentUsername.getText(), agentPassword.getText(), agentEmail.getText());
            exitToMain(event);
        }
    }

    private boolean validate() {
        if(
                agentUsername.getText().isEmpty() ||
                agentPassword.getText().isEmpty() ||
                agentEmail.getText().isEmpty()
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
}
