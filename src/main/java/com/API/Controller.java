package com.API;

import org.springframework.web.bind.annotation.*;
import com.API.Creation.Company;

import java.util.*;

@RestController
@RequestMapping("/api/companies")
public class Controller {

    private Map<Integer, Company> companyMap = new HashMap<>();

    @GetMapping
    public Collection<Company> getAllCompanies() {
        return companyMap.values();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyMap.get(id);
    }

    @PostMapping
    public String createCompany(@RequestBody Company company) {
        if (companyMap.containsKey(company.getId())) {
            return "Company with id " + company.getId() + " already exists!";
        }
        companyMap.put(company.getId(), company);
        return "Company created with id: " + company.getId();
    }

    @PutMapping("/{id}")
    public String updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
        if (!companyMap.containsKey(id))
            return "Company not found!";
        Company company = companyMap.get(id);
        company.setName(updatedCompany.getName());
        company.setLocation(updatedCompany.getLocation());
        return "Company updated with id: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable int id) {
        if (companyMap.remove(id) != null)
            return "Deleted company with id: " + id;
        return "Company not found!";
    }
}
