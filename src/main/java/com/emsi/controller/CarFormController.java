package com.emsi.controller;

import com.emsi.dao.impl.DB;
import com.emsi.entities.Car;
import com.emsi.service.CarService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarFormController {
    @FXML
    public TextField makeTextField;
    @FXML
    public TextField modelTextField;
    @FXML
    public TextField productionYearTextField;
    @FXML
    public TextField mpgTextField;
    @FXML
    public TextField kilometrageTextField;
    @FXML
    public TextField maxSpeedTextField;
    @FXML
    public TextField fuelTypeTextField;
    @FXML
    public TextField transmissionTypeTextField;
    @FXML
    public Button createUpdateButton;

    public CarService carService = new CarService();

    public void initialize() {
        setupDatabaseConnection();
        if (CarListController.tableCars != null) {
            createUpdateButton.setText("Update");
            fillTable();
        } else {
            createUpdateButton.setText("Create");
        }
    }

    public void setupDatabaseConnection() {
        DB.getConnection();
    }
    @FXML
    public void createCar() {
        if (CarListController.tableCars != null) {
            updateCar();
            return;
        }
        if (makeTextField.getText().isEmpty() || modelTextField.getText().isEmpty()
                || productionYearTextField.getText().isEmpty() ||
                mpgTextField.getText().isEmpty() || kilometrageTextField.getText().isEmpty()
                || maxSpeedTextField.getText().isEmpty() ||
                fuelTypeTextField.getText().isEmpty() || transmissionTypeTextField.getText().isEmpty()) {
            showErrorMessage("error", "Missing input", "Please enter all the required fields.");
            return;
        }
        String make = makeTextField.getText();
        String model = modelTextField.getText();
        int productionYear = Integer.parseInt(productionYearTextField.getText());
        double mpg = Double.parseDouble(mpgTextField.getText());
        int kilometrage = Integer.parseInt(kilometrageTextField.getText());
        int maxSpeed = Integer.parseInt(maxSpeedTextField.getText());
        String fuelType = fuelTypeTextField.getText();
        String transmissionType = transmissionTypeTextField.getText();

        Car carInsert = new Car(make, model, productionYear, mpg, kilometrage, maxSpeed, fuelType, transmissionType);
        carService.save(carInsert);

        System.out.println("Car inserted successfully!");
        showErrorMessage("information", "Valid input", "Car inserted successfully!");
        Stage formStage = (Stage) makeTextField.getScene().getWindow();
        formStage.close();
    }

    @FXML
    public void fillTable() {
        Car newValue = CarListController.tableCars;
        makeTextField.setText(newValue.getMake());
        modelTextField.setText(newValue.getModel());
        productionYearTextField.setText(String.valueOf(newValue.getProductionYear()));
        mpgTextField.setText(String.valueOf(newValue.getMpg()));
        kilometrageTextField.setText(String.valueOf(newValue.getKilometrage()));
        maxSpeedTextField.setText(String.valueOf(newValue.getMaxSpeed()));
        fuelTypeTextField.setText(newValue.getFuelType());
        transmissionTypeTextField.setText(newValue.getTransmissionType());
    }
    @FXML
    public void updateCar() {
        if (makeTextField.getText().isEmpty() || modelTextField.getText().isEmpty()
                || productionYearTextField.getText().isEmpty() ||
                mpgTextField.getText().isEmpty() || kilometrageTextField.getText().isEmpty()
                || maxSpeedTextField.getText().isEmpty() ||
                fuelTypeTextField.getText().isEmpty() || transmissionTypeTextField.getText().isEmpty()) {
            showErrorMessage("error", "Missing input", "Please enter all the required fields.");
            return;
        }
        Car selectedCar = CarListController.tableCars;
        if (selectedCar == null) {
            System.out.println("Please select a car to update.");
            showErrorMessage("error", "Invalid Selection", "Please select a car to update.");
            return;
        }
        int register = selectedCar.getRegister();
        String make = makeTextField.getText();
        String model = modelTextField.getText();
        int productionYear = Integer.parseInt(productionYearTextField.getText());
        double mpg = Double.parseDouble(mpgTextField.getText());
        int kilometrage = Integer.parseInt(kilometrageTextField.getText());
        int maxSpeed = Integer.parseInt(maxSpeedTextField.getText());
        String fuelType = fuelTypeTextField.getText();
        String transmissionType = transmissionTypeTextField.getText();
        Car carUpdate = new Car(register, make, model, productionYear, mpg, kilometrage, maxSpeed, fuelType, transmissionType);
        carService.update(carUpdate);
        System.out.println("Car updated successfully!");
        showErrorMessage("information", "Valid input", "Car updated successfully!");
        Stage formStage = (Stage) makeTextField.getScene().getWindow();
        formStage.close();
    }

    
    @FXML
    public void clearCar() {
        makeTextField.clear();
        modelTextField.clear();
        productionYearTextField.clear();
        mpgTextField.clear();
        kilometrageTextField.clear();
        maxSpeedTextField.clear();
        fuelTypeTextField.clear();
        transmissionTypeTextField.clear();
    }

    public void showErrorMessage(String type, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (type == "error") {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        else if (type == "information") {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        else if (type == "warning") {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        else if (type == "confirm") {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/emsi/View/styles.css").toExternalForm());
        alert.showAndWait();
    }
}
