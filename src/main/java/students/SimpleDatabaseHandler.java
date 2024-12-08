package students;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// Assumes you have the students table in the database
// (see the students table we used in SQL examples)
public class SimpleDatabaseHandler {
	/**
	 * URI to use when connecting to database. Should be in the format:
	 * jdbc:mysql://hostname/database
	 */
	private String uri = "";
	private Properties config; // maps host, user, password

	public SimpleDatabaseHandler() {
		initURI();
	}

	private Properties loadConfig(String configPath) throws FileNotFoundException, IOException {
		Properties config = new Properties();
		config.load(new FileReader(configPath));

		return config;
	}

	private void initURI() {
		try {
			this.config = loadConfig("database.properties");

			this.uri = String.format("jdbc:mysql://%s/%s", this.config.getProperty("hostname"),
					this.config.getProperty("database"));
			this.uri = this.uri + "?serverTimezone=UTC";
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}

	/** Connect to the database and send a simple query about students.
	 */
	public List<Student> getStudentInfo(int limit, int offset) {
		List<Student> list = new ArrayList<>();
		PreparedStatement sql;
			try (Connection dbConnection = DriverManager.getConnection(uri, config.getProperty("username"), config.getProperty("password"))) {
				sql = dbConnection.prepareStatement("select * from students limit ? offset ?");
				sql.setInt(1, limit);
				sql.setInt(2, offset);
				ResultSet results = sql.executeQuery();
				while (results.next()) {
					String id = results.getString("id");
					String name = results.getString("name");
					String gpa = results.getString("gpa");
					Student st = new Student(id, name, Double.parseDouble(gpa));
					list.add(st);
					System.out.println(id + " " + name + " " + gpa);
				}
			} catch (SQLException e) {
				System.err.println("Unable to connect to the database.");
				System.err.println(e.getMessage());
			}
			return list;
		}
}
