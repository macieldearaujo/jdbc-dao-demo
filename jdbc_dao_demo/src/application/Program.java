package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Department dep = new Department(1, "books");
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00, dep);
		
		System.out.println(dep + "\n" + seller);
		
	}

}