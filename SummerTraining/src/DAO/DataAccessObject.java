package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import beans.Client;
import oracle.jdbc.pool.OracleDataSource;

public class DataAccessObject {
	private static DataAccessObject dao = new DataAccessObject();
	private static ServletContext context;
	
	private OracleDataSource dataSource;
	
	private DataAccessObject() {
		// TODO Auto-generated constructor stub
		try {
			dataSource = new OracleDataSource();
			if (context == null)
				dataSource.setURL("jdbc:oracle:thin:saikat1/saikat1@//localhost:1521/XE");
			else
				dataSource.setURL(context.getInitParameter("conString"));
//			Connection con = dataSource.getConnection();
//			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataAccessObject getInstance() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public static DataAccessObject getInstance(ServletContext sc) {
		// TODO Auto-generated method stub
		context = sc;
		return dao;
	}

	public boolean insert(Client c) {
		// TODO Auto-generated method stub
		try {
			Connection con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			String sql = "insert into customer values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			//ps.setInt(1, c.getClientId());
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPassword());
			
			if (ps.executeUpdate() > 0) {
				con.commit();
				con.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public Client getClient(String uid, String pwd) {
		// TODO Auto-generated method stub
		try {
			Connection con = dataSource.getConnection();
			
			String sql = "select * from customer where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Client c = new Client();
				//c.setClientId(rs.getInt("id"));
				c.setFirstName(rs.getString("FName"));
				c.setLastName(rs.getString("LName"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	public InputStream getStream(String cid) {
		// TODO Auto-generated method stub
		try {
			Connection con = dataSource.getConnection();
			
			String sql = "select * from customer where email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				//return rs.getBinaryStream("img");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
