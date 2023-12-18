package com.emsi.importExtract;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.emsi.entities.Car;

public class Qst5_txt {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(Car.path + "carInputData.txt"))) {
            ArrayList<Car> list = new ArrayList<Car>();
            Car c = null;
            String readLine = br.readLine();

            while (readLine != null) {

                String[] car = readLine.split("\\|");

                c = new Car();
                c.setRegister(Integer.parseInt(car[0].trim()));
                c.setMake(car[1].trim());
                c.setModel(car[2].trim());
                c.setProductionYear(Integer.parseInt(car[3].trim()));
                c.setMpg(Double.parseDouble(car[4].trim()));
                c.setKilometrage(Integer.parseInt(car[5].trim()));
                c.setMaxSpeed(Integer.parseInt(car[6].trim()));
                c.setFuelType(car[7].trim());
                c.setTransmissionType(car[8].trim());

                list.add(c);
                readLine = br.readLine();
            }

            try (FileOutputStream fout = new FileOutputStream(Car.path + "carOutputData.txt")) {

                for (Car car : list) {
                    fout.write(car.toString().getBytes());
                    fout.write('\n');

                    System.out.println("Car: " + car.toString());
                }
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
            System.out.println("Done!");
        }
    }
}