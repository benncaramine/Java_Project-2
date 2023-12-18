package com.emsi.ImportExportToDataBase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emsi.entities.Car;
import com.emsi.service.CarService;

public class CarDataExcel {
    private CarService carService = new CarService();

    public void importDataFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            ArrayList<Car> carList = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // skip the header row
                    continue;
                }

                Car car = new Car();
                Cell registerCell = row.getCell(0);
                Cell makeCell = row.getCell(1);
                Cell modelCell = row.getCell(2);
                Cell yearCell = row.getCell(3);
                Cell mpgCell = row.getCell(4);
                Cell kilometrageCell = row.getCell(5);
                Cell maxSpeedCell = row.getCell(6);
                Cell fuelTypeCell = row.getCell(7);
                Cell transmissionTypeCell = row.getCell(8);

                car.setRegister((int) registerCell.getNumericCellValue());
                car.setMake(makeCell.getStringCellValue());
                car.setModel(modelCell.getStringCellValue());
                car.setProductionYear((int) yearCell.getNumericCellValue());
                car.setMpg(mpgCell.getNumericCellValue());
                car.setKilometrage((int) kilometrageCell.getNumericCellValue());
                car.setMaxSpeed((int) maxSpeedCell.getNumericCellValue());
                car.setFuelType(fuelTypeCell.getStringCellValue());
                car.setTransmissionType(transmissionTypeCell.getStringCellValue());

                carList.add(car);
            }

            System.out.println("Importing Data From Excel is done!\nWaiting for saving to database...");
            for (Car car : carList) {
                carService.saveOrUpdate(car);
            }
            System.out.println("Saving Cars From Excel To Database is done!");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToExcel(String filePath) {
        ArrayList<Car> cars = (ArrayList<Car>) carService.findAll();

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Car Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Register");
            headerRow.createCell(1).setCellValue("Make");
            headerRow.createCell(2).setCellValue("Model");
            headerRow.createCell(3).setCellValue("Year");
            headerRow.createCell(4).setCellValue("MPG");
            headerRow.createCell(5).setCellValue("Kilometrage");
            headerRow.createCell(6).setCellValue("MaxSpeed");
            headerRow.createCell(7).setCellValue("FuelType");
            headerRow.createCell(8).setCellValue("TransmissionType");

            int rowIndex = 1;

            for (Car car : cars) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(car.getRegister());
                dataRow.createCell(1).setCellValue(car.getMake());
                dataRow.createCell(2).setCellValue(car.getModel());
                dataRow.createCell(3).setCellValue(car.getProductionYear());
                dataRow.createCell(4).setCellValue(car.getMpg());
                dataRow.createCell(5).setCellValue(car.getKilometrage());
                dataRow.createCell(6).setCellValue(car.getMaxSpeed());
                dataRow.createCell(7).setCellValue(car.getFuelType());
                dataRow.createCell(8).setCellValue(car.getTransmissionType());
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            workbook.close();
            System.out.println("Exporting Data From Database To Excel is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}