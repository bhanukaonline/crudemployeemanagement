package net.javaguides.usermanagement.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

import net.javaguides.usermanagement.model.User;

public class UserDAO {
  private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
  private String jdbcUsername = "root";
  private String jdbcPassword = "";
  
  private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (NIC, name, Department, date, Designation) VALUES"
      +"(?,?, ?);";
  
  private static final String SELECT_USER_BY_NIC ="select NIC,name,Department, date, Designation from users where NIC =?";
  private static final String SELECT_ALL_USERS ="select * from users";
  private static final String DELETE_USERS_SQL  = "delete from users where NIC =?;";
  private static final String UPDATE_USERS_SQL=" update users set name =? , Department =?, date=?, Designation =? where NIC =?;";
private static final int NIC = 0;

  protected Connection getConnection() {
	  
	  Connection connection = null;
	  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  connection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);  
	  }
	  catch (SQLException e) {
		  //1000 Auto-generated catch block
		  e.printStackTrace();
	  }
	  catch (ClassNotFoundException e) {
		  // 1000 Auto-generated catch block
		  e.printStackTrace();
	  }
	  return connection;
	  
  }
  
  
  // Create or insert user
  
  public void insertUser(User user) throws SQLException {
	  try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
		  preparedStatement.setString(1, user.getName());
		  preparedStatement.setString(1, user.getDepartment());
		  preparedStatement.setString(1, user.getDate());
		  preparedStatement.setString(1, user.getDesignation());
		  preparedStatement.executeUpdate();
		  
	  }
	  catch (Exception e) {
		  e.printStackTrace();
		  
	  }
			  
  }
  //Update users
  public boolean updateUser(User user) throws SQLException {
  boolean rowUpdated;
  try(Connection connection = getConnection();
		  PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
	  statement.setString(1,user.getName());
	  statement.setString(2,user.getDepartment());
	  statement.setString(3,user.getDesignation());
	  statement.setString(4,user.getDate());
	  statement.setInt(1,user.getNIC());
	  rowUpdated = statement.executeUpdate()> 0;
  }
  return rowUpdated;
  }
  //select user by id
  public User selectUser(int NIC) {
	  
	  User user = null;
	  //Step 1: Establishing a Connection
	  try(Connection connection = getConnection();
			  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NIC);){
		 
		preparedStatement.setInt(1, NIC);
		  //char[] preparedStatement;
		System.out.println(preparedStatement);
		   //Step 3: Execute the query or update query
		  ResultSet rs = preparedStatement.executeQuery();
		  
		  //Step 4: Process the ResultSet object.
		  while (rs.next()) {
			  String name = rs.getString("name");
			  String Department = rs.getString("Department");
			  String date = rs.getString("date");
			  String Designation = rs.getString("Designation");
			  user = new User(NIC, name, Department, Designation, date);
			  
			  
		  }
		  
	  }catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return user;
			
  }
  //select users
  
  public List<User> selectAllUser() {
	 List<User> users = new ArrayList<>();
	  //Step 1: Establishing a Connection
	  try(Connection connection =getConnection();
			// Step 2: Create a Statement using connection object
			  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NIC);){
		
		preparedStatement.setInt(1, NIC);
		  //PreparedStatement preparedStatement;
		System.out.println(preparedStatement);
		   //Step 3: Execute the query or update query
		  ResultSet rs = preparedStatement.executeQuery();
		  
		  //Step 4: Process the ResultSet object.
		  while (rs.next()) {
			  int NIC = rs.getInt("NIC");
			  String name = rs.getString("name");
			  String Department = rs.getString("Department");
			  String date = rs.getString("date");
			  String Designation = rs.getString("Designation");
			  Object user;
			users.add(new User(NIC, name, Department, Designation, date));
			  
			  
		  }
		  
	  }catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return users;
			
  }
  // delete users
  
  public boolean deleteUser(int NIC) throws SQLException {
	  boolean rowDeleted;
	  
	try(Connection connection = getConnection();
			  PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
		  statement.setInt(1,NIC);
		  rowDeleted = statement.executeUpdate()> 0;
	  
	  }
	  return rowDeleted;
  }
}
