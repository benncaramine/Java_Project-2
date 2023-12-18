package com.emsi.importExtract;
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

public class Qst5_excel {
    public static void main(String[] args) throws Exception {
        try (FileInputStream fis = new FileInputStream(Car.path + "carInputData.xlsx")) {
            System.out.print("Reading from file... ");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            ArrayList<Car> list = new ArrayList<Car>();
            Car c = null;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // skip the header row
                    continue;
                }
                c = new Car();
                Cell registerCell = row.getCell(0);
                Cell makeCell = row.getCell(1);
                Cell modelCell = row.getCell(2);
                Cell yearCell = row.getCell(3);
                Cell mpgCell = row.getCell(4);
                Cell kilometrageCell = row.getCell(5);
                Cell maxSpeedCell = row.getCell(6);
                Cell fuelTypeCell = row.getCell(7);
                Cell transmissionTypeCell = row.getCell(8);

                c.setRegister((int) registerCell.getNumericCellValue());
                c.setMake(makeCell.getStringCellValue());
                c.setModel(modelCell.getStringCellValue());
                c.setProductionYear((int) yearCell.getNumericCellValue());
                c.setMpg(mpgCell.getNumericCellValue());
                c.setKilometrage((int) kilometrageCell.getNumericCellValue());
                c.setMaxSpeed((int) maxSpeedCell.getNumericCellValue());
                c.setFuelType(fuelTypeCell.getStringCellValue());
                c.setTransmissionType(transmissionTypeCell.getStringCellValue());

                list.add(c);
            }
            System.out.println("Success");
            System.out.print("Writing to file... ");
            Sheet outputSheet = workbook.createSheet("Output");
            int rowIndex = 0;
            Row headerRow = outputSheet.createRow(rowIndex++);
            headerRow.createCell(0).setCellValue("Register");
            headerRow.createCell(1).setCellValue("Make");
            headerRow.createCell(2).setCellValue("Model");
            headerRow.createCell(3).setCellValue("Year");
            headerRow.createCell(4).setCellValue("MPG");
            headerRow.createCell(5).setCellValue("Kilometrage");
            headerRow.createCell(6).setCellValue("MaxSpeed");
            headerRow.createCell(7).setCellValue("FuelType");
            headerRow.createCell(8).setCellValue("TransmissionType");

            for (Car car : list) {
                Row outputRow = outputSheet.createRow(rowIndex++);
                outputRow.createCell(0).setCellValue(car.getRegister());
                outputRow.createCell(1).setCellValue(car.getMake());
                outputRow.createCell(2).setCellValue(car.getModel());
                outputRow.createCell(3).setCellValue(car.getProductionYear());
                outputRow.createCell(4).setCellValue(car.getMpg());
                outputRow.createCell(5).setCellValue(car.getKilometrage());
                outputRow.createCell(6).setCellValue(car.getMaxSpeed());
                outputRow.createCell(7).setCellValue(car.getFuelType());
                outputRow.createCell(8).setCellValue(car.getTransmissionType());
            }

            try (FileOutputStream fout = new FileOutputStream(Car.path + "carOutputData.xlsx")) {
                workbook.write(fout);
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
            workbook.close();
            System.out.println("Success");
            System.out.println("Done!");
        }
    }
}
