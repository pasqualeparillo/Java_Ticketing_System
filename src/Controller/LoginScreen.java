package Controller;

import Database.JDBC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * controller for handling login
 */
public class LoginScreen {
    private String fxmlPath;

    @FXML private PasswordField passwordField;
    @FXML private TextField userNameField;



    public void submitLogin(ActionEvent actionEvent) {
            try {
                String sql = String.format("SELECT name, Password FROM Agents WHERE name = '%s' AND Password = '%s'", userNameField.getText(), passwordField.getText());
                PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    fxmlPath = "/View/MainScreen.fxml";
                    switchScene(actionEvent, "Ticket System");
                } else {
                    Alert invalidPassword = new Alert(Alert.AlertType.ERROR);
                    invalidPassword.setTitle("Error Dialog");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Error");
                    alert.setContentText("Username or Password Incorrect");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert invalidPassword = new Alert(Alert.AlertType.ERROR);
                invalidPassword.setTitle("Error Dialog");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Error");
                alert.setContentText("Username or Password Incorrect");
                alert.showAndWait();
            }
    }

    /**
     * Helper function used in programs to close a scene and open another
     * @param event - an event handler passed to change scene
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

    public void exitProgram() {
        System.exit(0);
    }
}
