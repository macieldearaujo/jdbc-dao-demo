package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SellerDao s1 = DaoFactory.createSellerDao();
		
		//Seller s2 = s1.findById(3);
		//System.out.println(s2);

		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		
		Department department = new Department(2, null);
		List<Seller> list = s1.findByDepartment(department);
		
		for(Seller seller : list) {
			System.out.println(seller);
		}
	}

}