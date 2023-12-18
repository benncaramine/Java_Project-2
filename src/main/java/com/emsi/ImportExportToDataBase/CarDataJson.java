package com.emsi.ImportExportToDataBase;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.emsi.entities.Car;
import com.emsi.service.CarService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CarDataJson {
    private CarService carService = new CarService();

    public void importDataFromJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            ArrayList<Car> carList = gson.fromJson(reader, new TypeToken<ArrayList<Car>>() {
            }.getType());
            System.out.println("Importing Data From Json is done!\nWaiting for saving to database...");
            for (Car car : carList) {
                carService.saveOrUpdate(car);
            }
            System.out.println("Saving Cars From Json To Database is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToJson(String filePath) {
        ArrayList<Car> cars = (ArrayList<Car>) carService.findAll();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cars);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("Exporting Data From Database To Json is done!");

    }
}