package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement ps = null;
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO seller(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)"
					, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new java.sql.Date(seller.getBirthDate().getTime())); // ********
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());
			
			int arrowsAffected = ps.executeUpdate();
			if(arrowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
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
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}
		} catch(SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}
		return null;
	}
	
	public Department instantiateDepartment(ResultSet rs) {
		try {
			Department dep = new Department();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setName(rs.getString("DepartmentName"));
			return dep;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public Seller instantiateSeller(ResultSet rs, Department dep) {
		try {
			Seller seller = new Seller();
			seller.setId(rs.getInt("Id"));
			seller.setName(rs.getString("Name"));
			seller.setEmail(rs.getString("Email"));
			seller.setBirthDate(rs.getDate("BirthDate"));
			seller.setBaseSalary(rs.getDouble("BaseSalary"));
			seller.setDepartment(dep);
			return seller;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public List<Seller> findByDepartment(Department d) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"SELECT s.*, d.Name AS DepartmentName "
					+ "FROM seller s "
					+ "JOIN department d ON d.Id = s.DepartmentId "
					+ "WHERE s.DepartmentId = ? "
					+ "ORDER BY s.name");
			ps.setInt(1, d.getId());
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("SELECT s.*, d.Name AS DepartmentName "
					+ "FROM seller s "
					+ "JOIN department d ON s.DepartmentId = d.Id "
					+ "ORDER BY s.Name");
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
