package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SellerDao s1 = DaoFactory.createSellerDao();
		
		System.out.println("\n=== TEST 1: seller findById ===");
		Seller s2 = s1.findById(3);
		System.out.println(s2);

		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = s1.findByDepartment(department);
		
		for(Seller seller : list) {
			System.out.println(seller);
		}
		
		System.out.println("\n=== TEST 3: seller findAll ===");
		list = s1.findAll();
		for(Seller seller : list) {
			System.out.println(seller);
		}
		
		System.out.println("\n=== TEST 4: seller insert ===");
		Seller newSeller = new Seller(null, "Nando Moura", "nando@gmail.com", new Date(), 4000.00, department);
		s1.insert(newSeller);
		System.out.println("Insert! New id: " + newSeller.getId());
	}

}