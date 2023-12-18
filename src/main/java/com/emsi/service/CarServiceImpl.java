package com.emsi.service;

import com.emsi.ImportExportToDataBase.CarDataExcel;
import com.emsi.ImportExportToDataBase.CarDataJson;
import com.emsi.ImportExportToDataBase.CarDataTxt;

public class CarServiceImpl extends CarService {

    private CarDataExcel carDataExcel;
	private CarDataTxt carDataTxt;
	private CarDataJson carDataJson;
	
    public CarServiceImpl() {
        carDataExcel = new CarDataExcel();
        carDataTxt = new CarDataTxt();
		carDataJson = new CarDataJson();
    }

    public void importCarsFromExcel(String filePath) {
		carDataExcel.importDataFromExcel(filePath);
	}

    public void exportCarsToExcel(String filePath) {
        carDataExcel.exportDataToExcel(filePath);
    }
    
    public void importCarsFromTextFile(String filePath) {
		carDataTxt.importDataFromTextFile(filePath);
	}

	public void exportCarsToTextFile(String filePath) {
		carDataTxt.exportDataToTextFile(filePath);
	}

    public void importCarsFromJson(String filePath) {
        carDataJson.importDataFromJson(filePath);
    }

	public void exportCarsToJson(String filePath) {
		carDataJson.exportDataToJson(filePath);
	}
}