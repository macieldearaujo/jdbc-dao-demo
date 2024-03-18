package application;

import java.sql.Connection;
import java.util.List;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SellerDao s1 = DaoFactory.createSellerDao();
		
		Seller s2 = s1.findById(3);
		//System.out.println(s2);
		
		SellerDaoJDBC s3 = new SellerDaoJDBC(DB.getConnection());
		List<Seller> list = s3.findByDepartment(2);
		
		for(Seller seller : list) {
			System.out.println(seller);
		}
	}

}