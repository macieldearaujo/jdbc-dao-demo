package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn = null;
	
	
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT s.*, d.Name AS DepartmentName "
					+ "FROM seller s "
					+ "JOIN department d ON s.DepartmentId = d.Id "
					+ "WHERE s.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while(rs.next()) {
					Integer columnId = rs.getInt("Id");
					String columnName = rs.getString("Name");
					String columnEmail = rs.getString("Email");
					Date columnBirthDate =  rs.getDate("BirthDate");
					Double columnBaseSalary = rs.getDouble("BaseSalary");
					Integer columnDepartmentId = rs.getInt("DepartmentId");
					String columnDepartmentName = rs.getString("DepartmentName");
					
					return new Seller(columnId, columnName, columnEmail, columnBirthDate, columnBaseSalary, new Department(columnDepartmentId, columnDepartmentName));
			}
		} catch(SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
