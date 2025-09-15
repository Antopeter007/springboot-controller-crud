package com.API.Creation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class Controller {
	Map<Integer, Company> companyMap = new HashMap<>();

	@GetMapping
	public Collection<Company> getAllCompanies() {
		return companyMap.values();
	}

	@GetMapping("/{id}")
	public Company getCompanyById(@PathVariable int id) {
		return companyMap.get(id);
	}

	@PostMapping("/add")
	public String createCompany(@RequestBody Company company) {
		if (companyMap.containsKey(company.getId())) {
			return "Company with id " + company.getId() + " already exists!";
		}
		companyMap.put(company.getId(), company);
		return "Company created with id: " + company.getId();
	}

	@DeleteMapping("/{id}")
	public String deleteCompany(@PathVariable int id) {
		if (companyMap.remove(id) != null)
			return "Deleted company with id: " + id;
		return "Company not found!";
	}
}
