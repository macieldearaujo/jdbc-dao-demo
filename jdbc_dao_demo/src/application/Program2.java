package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String args[]) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1 - department insert ===");
		//Department newDepartment1 = new Department(null, "Administrativo");
		//departmentDao.insert(newDepartment1);
		//System.out.println("Done! New id = " + newDepartment1.getId());
		
		System.out.println("=== TEST 2 - department findById ===");
		Department dep = departmentDao.findById(2);
		System.out.println(dep.getId() + ", " + dep.getName());
		
		System.out.println("=== TEST 3 - department update ===");
		dep = departmentDao.findById(9);
		dep.setName("Maria");
		departmentDao.update(dep);
		System.out.println("Done! Update completed");
		
		System.out.println("=== TEST 4 - department delete ===");
		//departmentDao.deleteById(10);
		//System.out.println("Done! Delete completed.");
		
		System.out.println("=== TEST 5 - department findAll ===");
		List<Department> list = departmentDao.findAll();
		for(Department d : list) {
			System.out.println(d.getId() + ", " + d.getName());
		}
	}
}
