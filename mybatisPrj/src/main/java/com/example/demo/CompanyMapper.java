package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyMapper {
	
	// Insert될 때, Id는 MySQL 내에서 AutoIncrement되는데 이 값을 Insert할 때 받아 오려면 @options 활용
	// 자바 객체 id 프로퍼티에 설정이 되는 형태로 반환
	@Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.name}, #{company.address})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(@Param("company") Company company);
	
	
	// Reuslts, Result 어노테이션을 통해 자바 내의 Company 엔티티의 객체와 Mysql객체를 매핑해준다.
	@Select("SELECT * FROM company")
	@Results(id = "CompanyMap", value={
		@Result(property = "name", column = "company_name"),
		@Result(property = "address", column = "company_address"),
		
		//column에서 직접 매핑하는 것이 아니라, id를 파라미터로 해서 @Many를 통해 API를 호출하고 전달된 id를 employeeList에 매핑
		@Result(property = "employeeList", column = "id", many=@Many(select="com.example.demo.EmployeeMapper.getByCompanyId"))
		
	})
	List<Company> getAll();
	
	
	// 또 매핑관계를 @Reuslts를 통해 하는 것이 아니라 26줄의 id = "CompanyMap", value로 id로 만든다.
	// 그 후, 아래 @ResultMap을 통해 재사용
	@Select("SELECT * FROM company WHERE id=#{id}")
	@ResultMap("CompanyMap")
	Company getById(@Param("id") int id);
	
	
}
