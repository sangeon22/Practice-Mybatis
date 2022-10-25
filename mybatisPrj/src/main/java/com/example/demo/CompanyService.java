package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Company> getAll(){
		List<Company> companyList = companyMapper.getAll();
		if (companyList != null && companyList.size()>0) {
			for (Company company : companyList) {
				company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
			}
		}
		
		return companyList;
	}
	
	@Transactional
	public Company add(Company company) {
		companyMapper.insert(company);
		// 기존 시스템(legacy System)에 Company를 추가하다 예외가 발생 시,
		// insert한 company까지 롤백
		if (true) {
			throw new RuntimeException("Legacy Exception");
		}
		return company;
	}
}
