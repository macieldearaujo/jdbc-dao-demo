package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			
		}
	}
	
	private static Properties loadProperties() {
		String path = "db.properties";
		try(FileInputStream fs = new FileInputStream(path)) {
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
