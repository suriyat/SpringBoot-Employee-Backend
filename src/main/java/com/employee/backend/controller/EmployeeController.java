package com.employee.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.backend.exception.CustomEexception;
import com.employee.backend.model.EmployeeDetails;
import com.employee.backend.repository.EmployeeDetailsRepository;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("api/v1/")
@Log4j
public class EmployeeController {
	
	public static Logger log = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeDetailsRepository repo;
	
	//find all or get all employees
	 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllEmployess")
	public List<EmployeeDetails> getAllEmployees(){
		return repo.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/createEmployee")
	public ResponseEntity<EmployeeDetails> createEmployee(@RequestBody EmployeeDetails employee) {
		
			EmployeeDetails emp = repo.save(employee);
			return ResponseEntity.ok(emp);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findEmployee/{id}")
	public ResponseEntity<EmployeeDetails> findEmployee(@PathVariable Integer id) {
		
		EmployeeDetails employee = repo.findById(id)
				.orElseThrow(() -> new CustomEexception("Employee not found"));
		return ResponseEntity.ok(employee);	
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<EmployeeDetails> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDetails emp ) {
		
		EmployeeDetails employee = repo.findById(id)
				.orElseThrow(() -> new CustomEexception("Employee not found"));
		
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmailId(emp.getEmailId());
		
		EmployeeDetails updatedEmployee = repo.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);	
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<EmployeeDetails> deleteEmp(@PathVariable Integer id){
		EmployeeDetails employee = repo.findById(id)
				.orElseThrow(() -> new CustomEexception("Employee not found"));
		repo.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", true);
		return ResponseEntity.ok(employee);
		
		
	}
		
}
