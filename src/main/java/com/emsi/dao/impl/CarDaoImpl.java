package com.emsi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emsi.dao.CarDao;
import com.emsi.entities.Car;

public class CarDaoImpl implements CarDao {

	private Connection conn = DB.getConnection();

	@Override
	public void insert(Car car) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO Car (make, model, production_year, mpg, kilometrage, maxSpeed, fuelType, transmissionType) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, car.getMake());
			ps.setString(2, car.getModel());
			ps.setInt(3, car.getProductionYear());
			ps.setDouble(4, car.getMpg());
			ps.setInt(5, car.getKilometrage());
			ps.setInt(6, car.getMaxSpeed());
			ps.setString(7, car.getFuelType());
			ps.setString(8, car.getTransmissionType());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					car.setRegister(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.err.println("problème d'insertion d'une voiture");;
		} finally {
			DB.closeStatement(ps);
		}
	}
//////////**************************************** */
	@Override
	public void update(Car car) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE Car SET make = ?, model = ?, production_year = ?, mpg = ?, kilometrage = ?, maxSpeed = ?, fuelType = ?, transmissionType = ? " +
			"WHERE register = ?");

			ps.setString(1, car.getMake());
			ps.setString(2, car.getModel());
			ps.setInt(3, car.getProductionYear());
			ps.setDouble(4, car.getMpg());
			ps.setInt(5, car.getKilometrage());
			ps.setInt(6, car.getMaxSpeed());
			ps.setString(7, car.getFuelType());
			ps.setString(8, car.getTransmissionType());
			ps.setInt(9, car.getRegister());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'une voiture");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer register) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM Car WHERE register = ?");
			
			ps.setInt(1, register);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'une voiture");;
		} finally {
			DB.closeStatement(ps);
		}
	}
//////////**************************************** */
	@Override
	public Car findById(Integer register) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM Car WHERE register = ?");

			ps.setInt(1, register);

			rs = ps.executeQuery();

			if (rs.next()) {
				Car car = new Car();

				car.setRegister(rs.getInt("register"));
				car.setMake(rs.getString("make"));
				car.setModel(rs.getString("model"));
				car.setProductionYear(rs.getInt("production_year"));
				car.setMpg(rs.getDouble("mpg"));
				car.setKilometrage(rs.getInt("kilometrage"));
				car.setMaxSpeed(rs.getInt("max_speed"));
				car.setFuelType(rs.getString("fuel_type"));
				car.setTransmissionType(rs.getString("transmission_type"));

				return car;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("Problème de requête pour trouver une voiture");
			return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Car> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM Car");
			rs = ps.executeQuery();

			List<Car> listCar = new ArrayList<>();

			while (rs.next()) {
				Car car = new Car();

				car.setRegister(rs.getInt("register"));
				car.setMake(rs.getString("make"));
				car.setModel(rs.getString("model"));
				car.setProductionYear(rs.getInt("production_year"));
				car.setMpg(rs.getDouble("mpg"));
				car.setKilometrage(rs.getInt("kilometrage"));
				car.setMaxSpeed(rs.getInt("maxSpeed"));
				car.setFuelType(rs.getString("fuelType"));
				car.setTransmissionType(rs.getString("transmissionType"));

				listCar.add(car);
			}

			return listCar;
		} catch (SQLException e) {
			System.err.println("Problème de requête pour sélectionner une liste de voitures");
			return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
	@Override
	public boolean validateUser(String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM Auth WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println("Problème de requête pour trouver un utilisateur");
			return false;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
}
