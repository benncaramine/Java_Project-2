package com.emsi.controller;

import java.io.IOException;

import com.emsi.service.CarService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    public CarService carService = new CarService();
    public static String usernameTF;

    @FXML
    public TextField usernameTextField;

    @FXML
    public PasswordField passwordField;


    @FXML
    public void login() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        usernameTF = username;

        if (carService.validateUser(username, password)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/emsi/View/CarList.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 1170, 700);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setMinWidth(1170); 
                stage.setMinHeight(700);
                stage.setMaxWidth(1170); 
                stage.setMaxHeight(700);
                stage.setTitle("Car Application");
                stage.show();
                
                // Close the login window
                Stage loginStage = (Stage) usernameTextField.getScene().getWindow();
                loginStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showErrorMessage("Incorrect input", "Please enter the correct username and password.");
        }
    }

    public void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/emsi/View/styles.css").toExternalForm());
        alert.showAndWait();
    }
}