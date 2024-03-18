package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public List<Seller> findByDepartment(Integer id) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Seller> list = new ArrayList<>();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"SELECT s.*, d.Name AS DepartmentName "
					+ "FROM seller s "
					+ "JOIN department d ON d.Id = s.DepartmentId "
					+ "WHERE s.DepartmentId = ? "
					+ "ORDER BY s.name");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {				
				Department dep = instantiateDepartment(rs);
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
		// TODO Auto-generated method stub
		return null;
	}

}
