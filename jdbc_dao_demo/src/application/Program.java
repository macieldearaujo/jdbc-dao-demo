package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SellerDao s1 = DaoFactory.createSellerDao();
		
		Seller s2 = s1.findById(3);
		//System.out.println(s2);

		List<Seller> list = s1.findByDepartment(2);
		
		for(Seller seller : list) {
			System.out.println(seller);
		}
	}

}