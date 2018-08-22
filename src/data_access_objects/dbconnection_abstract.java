package data_access_objects;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Properties;


public class dbconnection_abstract {

	public  Connection connection = null;
	public  ResultSet resultSet ;
	public PreparedStatement prepStmt;
	
	protected void myconnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
        // Step 1: register JDBC Driver
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		// step 2: instantiate a file reader for database properties
		FileReader fileR = new FileReader("db.properties");
		// will hold properties of fileR 
		Properties props = new Properties();
		// load properties
		props.load(fileR);
		// step 3: connect to database
		connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));        
    }
	
}
