package application;

import java.sql.Connection;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = DB.getConnection();
		
		SellerDao s1 = DaoFactory.createSellerDao();
		
		Seller s2 = s1.findById(3);
		System.out.println(s2);
	}

}