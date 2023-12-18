module com.emsi {
    requires javafx.fxml;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires java.sql;
    //requires com.google.gson;
    requires gson;
    requires poi;
    requires poi.ooxml;
    opens com.emsi to javafx.fxml;
    opens com.emsi.entities to gson;
    exports com.emsi;
    exports com.emsi.entities;
    exports com.emsi.controller;
    exports com.emsi.service;
}
