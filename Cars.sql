CREATE TABLE Car 
( 
    register INT NOT NULL AUTO_INCREMENT,
    make VARCHAR(50), 
    model VARCHAR(50), 
    production_year INT, 
    mpg DOUBLE, 
    kilometrage INT, 
    maxSpeed INT, 
    fuelType VARCHAR(20), 
    transmissionType VARCHAR(20), 
    PRIMARY KEY (register) 
);

CREATE table Auth 
(   
    username VARCHAR(50), 
    password VARCHAR(50), 
    PRIMARY KEY (username) 
);

INSERT INTO Auth (username, password) VALUES ('admin', '123');

INSERT INTO Car (make, model, production_year, mpg, kilometrage, maxSpeed, fuelType, transmissionType) 
VALUES ('Toyota', 'Corolla', 2019, 30.0, 10000, 120, 'Gasoline', 'Automatic');

INSERT INTO Car (make, model, production_year, mpg, kilometrage, maxSpeed, fuelType, transmissionType) 
VALUES ('Honda', 'Civic', 2020, 35.0, 5000, 130, 'Gasoline', 'Manual');

INSERT INTO Car (make, model, production_year, mpg, kilometrage, maxSpeed, fuelType, transmissionType) 
VALUES ('Ford', 'Mustang', 2018, 25.0, 20000, 150,'Gasoline', 'Automatic');

INSERT INTO Car (make, model, production_year, mpg,kilometrage,maxSpeed,fuelType ,transmissionType) 
VALUES ('Tesla','Model S',2021 ,100.0 ,100,'200','Electric','Automatic');

INSERT INTO Car (make ,model ,production_year ,mpg ,kilometrage ,maxSpeed ,fuelType ,transmissionType) 
VALUES ('BMW','X5',2017 ,20.0 ,30000,'140','Diesel','Automatic');