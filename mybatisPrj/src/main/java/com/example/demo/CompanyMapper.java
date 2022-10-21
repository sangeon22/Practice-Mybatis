package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyMapper {
	
	@Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.name}, #{company.address})")
	int insert(@Param("company") Company company);
	
	
	// Reuslts, Result 어노테이션을 통해 자바 내의 Company 엔티티의 객체와 Mysql객체를 매핑해준다.
	@Select("SELECT * FROM company")
	@Results({
		@Result(property = "name", column = "company_name"),
		@Result(property = "address", column = "company_address")
	})
	List<Company> getAll();
	
	
}
