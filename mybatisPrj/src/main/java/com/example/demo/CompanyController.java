package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("")
	public Company post(@RequestBody Company company) {
		companyMapper.insert(company);
		return company;
	}
	
	@GetMapping("")
	public List<Company> getAll(){
//		기존 companyMapper와 달리 companyService에서 구현한 로직인 getAll을 통해 각 회사에 소속된 사원까지 조회
//		return companyMapper.getAll();
		return companyService.getAll();
	}
	
	@GetMapping("/{id}")
	public Company getById(@PathVariable("id") int id){
		return companyMapper.getById(id);
	}
}
