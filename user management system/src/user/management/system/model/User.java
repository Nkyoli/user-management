package user.management.system.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {
	private static int lastId;
	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String birthDate;
	private String phoneNumber;
	private String address;
	private String password;
	
	public User() {
		id = lastId + 1;
	}
	
	public User(int id, String userName, String firstName, String lastName, String email, String address,
			String phoneNumber, String birthDate, String password) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.password = password;	
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void getLastUserIdFromDB(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("SELECT max(id) FROM user");
			ResultSet rs = stmt.getResultSet();
			if(rs.next()) lastId = rs.getInt(1);
			else lastId = 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
	}

	public static List<User> getAllUsers(Connection conn) {
		List<User> users = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(getSelectStatement());
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				User user = new User(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("email"),
						rs.getString("address"),
						rs.getString("phone_number"),
						rs.getString("birth_date"),
						rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public static List<String> getAllUserName(Connection conn) {
		List<String> userNames = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("SELECT username FROM user");
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) userNames.add(rs.getString("username"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return userNames;
	}
	
	public static String getUserPassword(String userName, Connection conn) {
		String password = "";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("SELECT password FROM user where username = " + "\"" + userName + "\"");
			ResultSet rs = stmt.getResultSet();
			if(rs.next()) password = rs.getString(1);
		}catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return password;
	}
	
	public static String getUserFirstName(String userName, Connection conn) {
		String firstName = "";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("SELECT first_name FROM user where username = " + "\"" + userName + "\"");
			ResultSet rs = stmt.getResultSet();
			if(rs.next()) firstName = rs.getString(1);
		}catch (SQLException e) {
			System.out.println(e.getMessage());;
		}
		return firstName;
	}
	
	public static String getSelectStatement() {
		return "SELECT id, username, first_name, last_name, email, address, phone_number, birth_date, password FROM user";
	}
	
	public String getUpdateStatement(int id) {
		return "UPDATE user SET username = " + "\"" + userName + "\", "
				+ " first_name = " + "\"" + firstName + "\", "
				+ " last_name = " + "\"" + lastName + "\", "
				+ " email = " + "\"" + email + "\", "
				+ " address = " + "\"" + address + "\", "
				+ " phone_number = " + "\"" + phoneNumber + "\", "
				+ " birth_date = " + "\"" + birthDate + "\", "
				+ " WHERE id = " + id;
	}

	public String getInsertStatement() {
		return "insert into user (id, username, first_name, last_name, email, address, phone_number, birth_date, password) values ("
				+ id + ", "
				+ "\"" + userName + "\", "
				+ "\"" + firstName + "\", "
				+ "\"" + lastName + "\", "
				+ "\"" + email + "\", "
				+ "\"" + address + "\", "
				+ "\"" + phoneNumber + "\", "
				+ "\"" + birthDate + "\", "
				+ "\"" + password + "\")";
	}
	
	
}
