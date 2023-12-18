package com.emsi.importExtract;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.emsi.entities.Car;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Qst5_json {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader(Car.path + "carInputData.json")) {
            // Using Gson library to deserialize JSON file into a list of Car objects
            Gson gson = new GsonBuilder().create();
            ArrayList<Car> carList = gson.fromJson(reader, new TypeToken<ArrayList<Car>>(){}.getType());

            // Printing read data to console
            System.out.println("Read from file:");
            for (Car car : carList) {
                System.out.println(car);
            }

            // Writing modified data to output file
            try (FileWriter writer = new FileWriter(Car.path + "carOutputData.json")) {
                gson.toJson(carList, writer);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
            System.out.println("Done!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}