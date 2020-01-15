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
public class Dao
{

	private static final String PATH = "jdbc:sqlite:/home/evan/Documents/netprog/sqlite.db";
	/**
	 *
	 * @return connection to sqlite database
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(PATH);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("getConnection!" + e.getMessage());
		}
		return conn;
	}

	public Dao() {
	}

	/**
	 *
	 * @param app an appointment object to createAppointment in the db
	 *
	 * @return 0 for success or 1 for failure
	 */
	public int createAppointment(Appointment app) {
		String sql = "INSERT INTO appointments(\"patient\", \"doctor\", \"room\", \"date\", \"time\")\n"
					 + "VALUES(?, ?, ?, ?, ?);";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setInt(1, app.getPatient_id());
			prepStmnt.setInt(2, app.getDoctor_id());
			prepStmnt.setInt(3, app.getRoom_id());
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
			System.out.println("create appointment: " + e.getMessage());
		}
		return 1;
	}



	/**
	 *
	 * @param id the id of returned appointment
	 *
	 * @return an appointment if exists null if not
	 */
	public Appointment retrieveAppointment(int id) {
		Appointment appointment = null;
		String sql = "SELECT * FROM appointments WHERE id = ?;";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			ResultSet res;

			prepStmnt = conn.prepareStatement(sql);
			prepStmnt.setInt(1, id);
			prepStmnt.execute();

			res = prepStmnt.getResultSet();

			if (res.next()) { //if res is not empty
				appointment = new Appointment(
						res.getInt("id"),
						res.getInt("patient_id"),
						res.getInt("doctor_id"),
						res.getInt("room_id"),
						res.getString("date"),
						res.getString("time"));
			}
		} catch (SQLException e) {
			System.out.println("retrieve appointment: " + e.getMessage());
		}
		return appointment;
	}
	
	/**
	 *
	 * @param patient_id
	 * @param doctor_id
	 * @param room_id
	 * @return a list of appointments
	 */
	public List<Appointment> retrieveAppointments(int patient_id,
												  int doctor_id,
												  int room_id) {
		List<Appointment> apps = new LinkedList<>();
		String sql = "SELECT * FROM appointments ";
		boolean empty = true;

		if (patient_id != -1) {
			sql += "WHERE \"patient_id\" = " + patient_id;
			empty = false;
		}

		if (doctor_id != -1) {
			if(!empty) { // if the query already includes a WHERE clause
				sql += " AND "; // append AND before this test
			} else {
				sql += " WHERE "; // else append WHERE before this test
			}
			sql += "\"doctor_id\" = " + doctor_id;
			empty = false;
		}

		if (room_id != -1) {
			if(!empty) {
				sql += " AND ";
			} else {
				sql += " WHERE ";
			}
			sql += "\"room_id\" = " + room_id;
		}
		sql += ";";

		System.out.print(sql);

		try (Connection conn = getConnection()) {
			Statement stmnt;
			ResultSet res;

			stmnt = conn.createStatement();
			stmnt.execute(sql);

			res = stmnt.getResultSet();

			if (res == null) { //if no appointments exist return null
				return null;
			}

			while (res.next()) { //if res is not empty
				apps.add(new Appointment(
						res.getInt("id"),
						res.getInt("patient_id"),
						res.getInt("doctor_id"),
						res.getInt("room_id"),
						res.getString("date"),
						res.getString("time")));
			}
		} catch (SQLException e) {
			System.out.println("retrieve all appointments: " + e.getMessage());
		}
		return apps;
	}
	/**
	 *
	 * @param id  the id of appointment to updateAppointment
	 * @param app the Appointment object
	 *
	 * @return 0 success 
	 *	      -1 failure
	 */
	public int updateAppointment(int id, Appointment app) {
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

			prepStmnt.setInt(1, app.getPatient_id());
			prepStmnt.setInt(2, app.getDoctor_id());
			prepStmnt.setInt(3, app.getRoom_id());
			prepStmnt.setString(4, app.getDate());
			prepStmnt.setString(5, app.getTime());
			prepStmnt.setInt(6, id);

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("update!" + e.getMessage());
		}
		return -1; //if reached, an exception was thrown
	}

	/**
	 *
	 * @param id the id of the appointment to deleteAppointment
	 *
	 * @return 0 for success, -1 for failure
	 */
	public int deleteAppointment(int id) {
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
			System.out.println("delete appointment: " + e.getMessage());
		}
		return -1;
	}

	public int createPatient(Patient patient) {
		String sql = "INSERT INTO patients(\"fullname\", \"address\", \"phone_num\")"
					 + "VALUES(?, ?, ?);";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, patient.getFullname());
			prepStmnt.setString(2, patient.getAddress());
			prepStmnt.setString(3, patient.getPhone_num());

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("create patient: " + e.getMessage());
		}
		return 1;
	}

	public Patient retrievePatient(int id) {
		Patient patient = null;
		String sql = "SELECT * FROM patients WHERE id = ?;";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			ResultSet res;

			prepStmnt = conn.prepareStatement(sql);
			prepStmnt.setInt(1, id);
			prepStmnt.execute();

			res = prepStmnt.getResultSet();

			if (res.next()) { //if res is not empty
				patient = new Patient(
						res.getInt("id"),
						res.getString("fullname"),
						res.getString("address"),
						res.getString("phone_num"));
			}
		} catch (SQLException e) {
			System.out.println("retrieve patient: " + e.getMessage());
		}
		return patient;
	}
	
	public List<Patient> retrieveAllPatients() {
		List<Patient> patients = new LinkedList<>();
		String sql = "SELECT * FROM patients;";

		try (Connection conn = getConnection()) {
			Statement stmnt;
			ResultSet res;

			stmnt = conn.createStatement();
			stmnt.execute(sql);

			res = stmnt.getResultSet();

			if (res == null) {
				return null;
			}

			while (res.next()) { //if res is not empty
				patients.add(new Patient(
						res.getInt("id"),
						res.getString("fullname"),
						res.getString("address"),
						res.getString("phone_num")));
			}
		} catch (SQLException e) {
			System.out.println("retrieve all patients: " + e.getMessage());
		}
		return patients;
	}

	public int updatePatient(int id, Patient patient) {
		String sql = "UPDATE patients set "
					 + "\"fullname\" = ?,"
					 + "\"address\" = ?,"
					 + "\"phone_num\" = ?,"
					 + "WHERE id = ?;";
		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, patient.getFullname());
			prepStmnt.setString(2, patient.getAddress());
			prepStmnt.setString(3, patient.getPhone_num());
			prepStmnt.setInt(4, id);

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("update patient: " + e.getMessage());
		}
		return -1; //if reached, an exception was thrown
	}

	public int deletePatient(int id) {
		String sql = "DELETE FROM patients WHERE id = " + id + ";";
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
			System.out.println("delete patient: " + e.getMessage());
		}
		return -1;
	}

	public int createDoctor(Doctor doc) {
		String sql = "INSERT INTO doctors(\"spec_id\", \"fullname\")"
					 + "VALUES(?, ?);";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setInt(1, doc.getSpec_id());
			prepStmnt.setString(2, doc.getFullname());

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("create doctor: " + e.getMessage());
		}
		return 1;
	}

	public Doctor retrieveDoctor(int id) {
		Doctor doc = null;
		String sql = "SELECT * FROM doctors WHERE id = ?;";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			ResultSet resultSet;

			prepStmnt = conn.prepareStatement(sql);
			prepStmnt.setInt(1, id);
			prepStmnt.execute();

			resultSet = prepStmnt.getResultSet();

			if (resultSet.next()) { //if res is not empty
				doc = new Doctor(resultSet.getInt(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("retrieve doctor: " + e.getMessage());
		}
		return doc;
	}

	public List<Doctor> retrieveDoctors(int spec_id) {
		List<Doctor> docs = new LinkedList<>();
		String sql = "SELECT * FROM doctors ";

		if(spec_id != -1) {
			sql += "WHERE \"spec_id\" = " + spec_id;
		}

		sql += ";";
		
		try (Connection conn = getConnection()) {
			Statement stmnt;
			ResultSet res;

			stmnt = conn.createStatement();
			stmnt.execute(sql);

			res = stmnt.getResultSet();

			if (res == null) { //if no doctors exist return null
				return null;
			}

			while (res.next()) { //if res is not empty
				docs.add(new Doctor(res.getInt("id"), res.getInt("spec_id"), res.getString("fullname")));
			}
		} catch (SQLException e) {
			System.out.println("retrieve all doctors: " + e.getMessage());
		}
		return docs;
	}

	public int updateDoctor(int id, Doctor doc) {
		String sql = "UPDATE doctors set "
					 + "\"spec_id\" = ?,"
					 + "\"fullname\" = ?,"
					 + "WHERE id = ?;";
		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setInt(1, doc.getSpec_id());
			prepStmnt.setString(2, doc.getFullname());
			prepStmnt.setInt(3, id);

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("update doctor: " + e.getMessage());
		}
		return -1; //if reached, an exception was thrown
	}

	public int deleteDoctor(int id) {
		String sql = "DELETE FROM doctors WHERE id = " + id + ";";
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
			System.out.println("update doctor: " + e.getMessage());
		}
		return -1;
	}

	public int createRoom(Room room) {
		String sql = "INSERT INTO room(\"description\")"
					 + "VALUES(?);";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, room.getDescription());

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("create room: " + e.getMessage());
		}
		return 1;
	}

	public Room retrieveRoom(int id) {
		Room room = null;
		String sql = "SELECT * FROM rooms WHERE id = ?;";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			ResultSet resultSet;

			prepStmnt = conn.prepareStatement(sql);
			prepStmnt.setInt(1, id);
			prepStmnt.execute();

			resultSet = prepStmnt.getResultSet();

			if (resultSet.next()) { //if res is not empty
				room = new Room(resultSet.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("retrieve room: " + e.getMessage());
		}
		return room;
	}
	
	public List<Room> retrieveAllRooms() {
		List<Room> rooms = new LinkedList<>();
		String sql = "SELECT * FROM rooms;";

		try (Connection conn = getConnection()) {
			Statement stmnt;
			ResultSet res;

			stmnt = conn.createStatement();
			stmnt.execute(sql);

			res = stmnt.getResultSet();

			if (res == null) {
				return null;
			}

			while (res.next()) { //if res is not empty
				rooms.add(new Room(res.getInt("id"), res.getString("description")));
			}
		} catch (SQLException e) {
			System.out.println("retrieve all doctors: " + e.getMessage());
		}
		return rooms;
	}

	public int updateRoom(int id, Room room) {
		String sql = "UPDATE rooms set "
					 + "\"description\" = ?,"
					 + "WHERE id = ?;";
		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, room.getDescription());
			prepStmnt.setInt(2, id);

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("update room: " + e.getMessage());
		}
		return -1; //if reached, an exception was thrown
	}

	public int deleteRoom(int id) {
		String sql = "DELETE FROM rooms WHERE id = " + id + ";";
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
			System.out.println("update room: " + e.getMessage());
		}
		return -1;
	}

	public int createSpeciality(Speciality spec) {
		String sql = "INSERT INTO specialities(\"description\")"
					 + "VALUES(?);";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, spec.getDescription());

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("create spec: " + e.getMessage());
		}
		return 1;
	}

	public Speciality retrieveSpeciality(int id) {
		Speciality spec = null;
		String sql = "SELECT * FROM specialities WHERE id = ?;";

		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			ResultSet resultSet;

			prepStmnt = conn.prepareStatement(sql);
			prepStmnt.setInt(1, id);
			prepStmnt.execute();

			resultSet = prepStmnt.getResultSet();

			if (resultSet.next()) { //if res is not empty
				spec = new Speciality(resultSet.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("retrieve doctor: " + e.getMessage());
		}
		return spec;
	}

	public List<Speciality> retrieveAllSpecialities() {
		List<Speciality> specs = new LinkedList<>();
		String sql = "SELECT * FROM specialities;";

		try (Connection conn = getConnection()) {
			Statement stmnt;
			ResultSet res;

			stmnt = conn.createStatement();
			stmnt.execute(sql);

			res = stmnt.getResultSet();

			if (res == null) {
				return null;
			}

			while (res.next()) { //if res is not empty
				specs.add(new Speciality(res.getInt("id"), res.getString("description")));
			}
		} catch (SQLException e) {
			System.out.println("retrieve all doctors: " + e.getMessage());
		}
		return specs;
	}
	public int updateSpeciallity(int id, Speciality spec) {
		String sql = "UPDATE specialities set "
					 + "\"description\" = ?,"
					 + "WHERE id = ?;";
		try (Connection conn = getConnection()) {
			PreparedStatement prepStmnt;
			int updateCount;

			prepStmnt = conn.prepareStatement(sql);

			prepStmnt.setString(1, spec.getDescription());
			prepStmnt.setInt(2, id);

			prepStmnt.execute();

			updateCount = prepStmnt.getUpdateCount();

			if (updateCount == 1) {
				return 0;
			}

		} catch (SQLException e) {
			System.out.println("update spec: " + e.getMessage());
		}
		return -1; //if reached, an exception was thrown
	}

	public int deleteSpeciality(int id) {
		String sql = "DELETE FROM specialities WHERE id = " + id + ";";
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
			System.out.println("delete speciality: " + e.getMessage());
		}
		return -1;
	}
}
