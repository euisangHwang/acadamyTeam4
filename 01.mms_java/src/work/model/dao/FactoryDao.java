package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FactoryDao {

	//파일 IO로 프로퍼티를 동적으로 만들 수 있음.
	//Resource.Bundle = > 외부파일을 읽어서 자동으로 (키,벨류)로 읽을 수 있게 정리해주는 class
	
	ResourceBundle resource;
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	static public FactoryDao instance = new FactoryDao();
	
	private FactoryDao () {
		
		//Resource초기화
		// 패스 구분자 : /  => \는 특수문자 시작 / \\ == /
		resource = ResourceBundle.getBundle("conf/dbserver");
		
		driver = resource.getString("oracle.Driver");
		url = resource.getString("oracle.url");
		user = resource.getString("oracle.user");
		password = resource.getString("oracle.password");
		
		System.out.println(driver);
		System.out.println(url);
		System.out.println(user);
		System.out.println(password);
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
 			
			e.printStackTrace(); //개발시 디버깅 목적으로 사용 => logg4j
		}
		
	}
	
	static public FactoryDao getInstance() {
		
		return instance;
	}
	
	public Connection getConnection () {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {}
		
		return conn;
	}
	
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		
		try {
			if (rs != null) {rs.close();};
			if (stmt != null) {stmt.close();};
			if (conn != null) {conn.close();};
		} catch (SQLException e) {}
	}
	
	public void close(Statement stmt, Connection conn) {
		
		close(null, stmt, conn);
	}
}
