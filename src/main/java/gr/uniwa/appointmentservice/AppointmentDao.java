/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gr.uniwa.appointmentservice;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author evan
 */
public class AppointmentDao
{

	/**
	 *
	 * @return connection to sqlite database
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:/home/evan/Documents/netprog/sqlite.db");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("getConnection!" + e.getMessage());
		}
		return conn;
	}

	public AppointmentDao() {
		String sql = "CREATE TABLE IF NOT EXISTS \"appointments\" ("
					 + "\"id\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
					 + "\"patient\"	TEXT NOT NULL,"
					 + "\"doctor\"	TEXT NOT NULL,"
					 + "\"room\"	TEXT NOT NULL,"
					 + "\"date\"	TEXT NOT NULL,"
					 + "\"time\"	TEXT NOT NULL"
					 + ");";

		try (Connection conn = getConnection()) {
			Statement stmnt;
			stmnt = conn.createStatement();
			stmnt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 *
	 * @param app an appointment object to create in the db
	 *
	 * @return 0 for success or 1 for failure
	 */
	public int create(Appointment app) {
		String sql = "INSERT INTO appointments(\"patient\", \"doctor\", \"room\", \"date\", \"time\")\n"
					 + "VALUES(?, ?, ?, ?, ?);";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, app.getPatient());
			prepStmnt.setString(2, app.getDoctor());
			prepStmnt.setString(3, app.getRoom());
			prepStmnt.setString(4, app.getDate());
			prepStmnt.setString(5, app.getTime());

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("create!" + e.getMessage());
		}
		return 1;
	}

	/**
	 *
	 * @param id the id of returned appointment
	 *
	 * @return an appointment if exists null if not
	 */
	public Appointment retrieve(int id) {
		Appointment appointment = null;
		String sql = "SELECT * FROM appointments WHERE id = ?;";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			ResultSet resultSet;
			String patient, doctor, room, date, time;

			prepStmnt = conn.prepareStatement(sql);
			prepStmnt.setInt(1, id);
			prepStmnt.execute();

			resultSet = prepStmnt.getResultSet();

			if (resultSet.next()) { //if resultSet is not empty
				patient = resultSet.getString("patient");
				doctor = resultSet.getString("doctor");
				room = resultSet.getString("room");
				date = resultSet.getString("date");
				time = resultSet.getString("time");

				appointment = new Appointment(id, patient, doctor, room, date,
											  time);
			}
		} catch (SQLException e) {
			System.out.println("retrieve!" + e.getMessage());
		}
		return appointment;
	}

	/**
	 *
	 * @return a list of all appointments
	 */
	public List<Appointment> retrieveAll() {
		List<Appointment> apps = new LinkedList<>();
		String sql = "SELECT * FROM appointments;";

		try (Connection conn = getConnection()) {
			Statement stmnt;
			ResultSet resultSet;
			int id;
			String patient, doctor, room, date, time;

			stmnt = conn.createStatement();
			stmnt.execute(sql);

			resultSet = stmnt.getResultSet();

			if (resultSet == null) { //if no appointments exist return null
				return null;
			}

			while (resultSet.next()) { //if resultSet is not empty
				id = resultSet.getInt("id");
				patient = resultSet.getString("patient");
				doctor = resultSet.getString("doctor");
				room = resultSet.getString("room");
				date = resultSet.getString("date");
				time = resultSet.getString("time");

				apps.add(new Appointment(id, patient, doctor, room, date, time));
			}
		} catch (SQLException e) {
			System.out.println("retrieveAll!" + e.getMessage());
		}
		return apps;
	}

	/**
	 *
	 * @param id  the id of appointment to update
	 * @param app the Appointment object
	 *
	 * @return 0 success -1,1 failures
	 */
	public int update(int id, Appointment app) {
		String sql = "UPDATE appointments set "
					 + "\"patient\" = ?,"
					 + "\"doctor\" = ?,"
					 + "\"room\" = ?,"
					 + "\"date\" = ?,"
					 + "\"time\" = ?"
					 + "WHERE id = ?;";
		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, app.getPatient());
			prepStmnt.setString(2, app.getDoctor());
			prepStmnt.setString(3, app.getRoom());
			prepStmnt.setString(4, app.getDate());
			prepStmnt.setString(5, app.getTime());
			prepStmnt.setInt(6, id);

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			switch (updateCount) {
				case 1:
					return 0;
				case -1:
					return -1;
				default:
					return 1;
			}

		} catch (SQLException e) {
			System.out.println("update!" + e.getMessage());
		}
		return 1; //if reached, an exception was thrown
	}

	/**
	 *
	 * @param id the id of the appointment to delete
	 *
	 * @return 0 for success, -1 for failure
	 */
	public int delete(int id) {
		String sql = "DELETE FROM appointments WHERE id = " + id + ";";
		Statement stmnt;

		try (Connection conn = getConnection()) {
			int updateCount;
			stmnt = conn.createStatement();
			stmnt.execute(sql);

			updateCount = stmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			}


		} catch (SQLException e) {
			System.out.println("update!" + e.getMessage());
		}

		return -1;
	}

}
