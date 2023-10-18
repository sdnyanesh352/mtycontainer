package com.universal.containx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universal.containx.model.Company;
import com.universal.containx.repository.CompanyRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@GetMapping("/companies")
	public List<Company> getCompanies() {
		 return companyRepository.findAll();
	}
	@PostMapping("/companies")
	public void addCompanies(@RequestBody Company company) {
		  companyRepository.save(company);
		
		
	}

}
