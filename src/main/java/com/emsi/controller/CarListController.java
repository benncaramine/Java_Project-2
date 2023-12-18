package com.emsi.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.emsi.dao.impl.DB;
import com.emsi.entities.Car;
import com.emsi.service.CarService;
import com.emsi.service.CarServiceImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CarListController {
    @FXML
    public TableView<Car> carTableView;
    
    @FXML
    public Label usernameLabel;
    
    public CarService carService = new CarService();
    public static Car tableCars = null;
    
    public void initialize() {
        setupDatabaseConnection();
        setupTableSelectionListener();
        usernameLabel.setText("Welcome " + LoginController.usernameTF + " !");
    }

    public void setupDatabaseConnection() {
        DB.getConnection();
    }

    @FXML
    public void createCar() throws IOException {
        tableCars = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/emsi/View/CarForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500); 
        stage.setMinHeight(600);
        stage.setMaxWidth(500); 
        stage.setMaxHeight(600);
        stage.setTitle("Car Creation");
        stage.setOnHidden(event -> {
            readCar();
        });
        stage.show();
    }

    @FXML
    public void readCar() {
        carTableView.getItems().clear();
        ArrayList<Car> cars = (ArrayList<Car>) carService.findAll();
        for (Car car : cars) {
            carTableView.getItems().add(car);
        }
    }

    @FXML
    public void updateCar() throws IOException {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar == null) {
            System.out.println("Please select a car to update.");
            showErrorMessage("error", "Invalid Selection", "Please select a car to update.");
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/emsi/View/CarForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500); 
        stage.setMinHeight(600);
        stage.setMaxWidth(500); 
        stage.setMaxHeight(600);
        stage.setTitle("Car Modification");
        stage.setOnHidden(event -> {
            readCar();
        });
        stage.show();
        
    }

    @FXML
    public void deleteCar() {
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();

        if (selectedCar == null) {
            System.out.println("Please select a car to delete.");
            showErrorMessage("error", "Invalid Selection", "Please select a car to delete.");
            return;
        }
        carService.remove(selectedCar);
        System.out.println("Car deleted successfully!");
        showErrorMessage("information", "Valid input", "Car deleted successfully!");
        readCar(); // Refresh the table view after deleting a car
    }

    public void setupTableSelectionListener() {
        carTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Car>() {
            @Override
            public void changed(ObservableValue<? extends Car> observable, Car oldValue, Car newValue) {
                if (newValue != null) {
                    tableCars = newValue;
                } else {
                    tableCars = null;
                }
            }
        });
    }

    @FXML
    public void importData() {
        CarServiceImpl carServiceImpl = new CarServiceImpl();
        carServiceImpl.importCarsFromExcel(Car.path + "carInputData.xlsx");
        System.out.println("Cars imported successfully!");
        showErrorMessage("information", "Valid input", "Cars imported successfully!");
        readCar(); // Refresh the table view after importing cars
    }

    @FXML
    public void exportData() {
        CarServiceImpl carServiceImpl = new CarServiceImpl();
        carServiceImpl.exportCarsToExcel(Car.path + "carOutputData.xlsx");
        System.out.println("Cars exported successfully!");
        showErrorMessage("information", "Valid input", "Cars exported successfully!");
        readCar(); // Refresh the table view after exporting the car
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