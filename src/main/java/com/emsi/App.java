package com.emsi;

/**
 * Hello world!
 *
 */
import java.io.IOException;

import com.emsi.entities.Car;
import com.emsi.service.CarServiceImpl;

// import com.emsi.entities.Car;
// import com.emsi.service.CarService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("View/Login"), 500, 500);
        stage.setScene(scene);
        stage.setMinWidth(500); 
        stage.setMinHeight(500);
        stage.setMaxWidth(500); 
        stage.setMaxHeight(500);
        stage.setTitle("Cars");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main( String[] args )
    {
        CarServiceImpl carServiceImpl = new CarServiceImpl();
        // carServiceImpl.importCarsFromTextFile(Car.path + "carInputData.txt");
        // carServiceImpl.exportCarsToTextFile(Car.path + "carOutputData.txt");

        // carServiceImpl.importCarsFromExcel(Car.path + "carInputData.xlsx");
        //carServiceImpl.exportCarsToExcel(Car.path + "carOutputData.xlsx");

        // carServiceImpl.importCarsFromJson(Car.path + "carInputData.json");
        // carServiceImpl.exportCarsToJson(Car.path + "carOutputData.json");

        // for(Car car : carService.findAll())
        //     System.out.println(car);
        launch();
    }
}
