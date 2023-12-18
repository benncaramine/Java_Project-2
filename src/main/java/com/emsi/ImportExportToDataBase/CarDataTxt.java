package com.emsi.ImportExportToDataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.emsi.entities.Car;
import com.emsi.service.CarService;

public class CarDataTxt {
    private CarService carService = new CarService();

    public void importDataFromTextFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Car> carList = new ArrayList<>();
            String readLine = br.readLine();

            while (readLine != null) {
                String[] carData = readLine.split("\\|");

                Car car = new Car();
                car.setRegister(Integer.parseInt(carData[0].trim()));
                car.setMake(carData[1].trim());
                car.setModel(carData[2].trim());
                car.setProductionYear(Integer.parseInt(carData[3].trim()));
                car.setMpg(Double.parseDouble(carData[4].trim()));
                car.setKilometrage(Integer.parseInt(carData[5].trim()));
                car.setMaxSpeed(Integer.parseInt(carData[6].trim()));
                car.setFuelType(carData[7].trim());
                car.setTransmissionType(carData[8].trim());

                carList.add(car);
                readLine = br.readLine();
            }
            System.out.println("Importing Data From text is done!\nWaiting for saving to database...");
            for (Car car : carList) {
                carService.saveOrUpdate(car);
            }
            System.out.println("Saving Cars From Text To Database is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToTextFile(String filePath) {
        ArrayList<Car> cars = (ArrayList<Car>) carService.findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Car car : cars) {
                String carData = car.getRegister() + " | " + car.getMake() + " | " + car.getModel() + " | " +
                        car.getProductionYear() + " | " + car.getMpg() + " | " + car.getKilometrage() + " | " +
                        car.getMaxSpeed() + " | " + car.getFuelType() + " | " + car.getTransmissionType();

                bw.write(carData);
                bw.newLine();
            }

            System.out.println("Exporting Data From Database To Text is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}