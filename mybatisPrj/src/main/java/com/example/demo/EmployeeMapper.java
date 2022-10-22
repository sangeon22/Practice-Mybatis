package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
	// Insert될 때, Id는 MySQL 내에서 AutoIncrement되는데 이 값을 Insert할 때 받아 오려면 @options 활용
		// 자바 객체 id 프로퍼티에 설정이 되는 형태로 반환
		@Insert("INSERT INTO employee(company_id, employee_name, employee_address) VALUES(#{employee.companyId}, #{employee.name}, #{employee.address})")
		@Options(useGeneratedKeys = true, keyProperty = "id")
		int insert(@Param("employee") Employee employee);
		
		
		// Reuslts, Result 어노테이션을 통해 자바 내의 Company 엔티티의 객체와 Mysql객체를 매핑해준다.
		@Select("SELECT * FROM employee")
		@Results(id = "EmployeeMap", value={
			@Result(property = "name", column = "employee_name"),
			@Result(property = "address", column = "employee_address")
		})
		List<Employee> getAll();
		
		
		// 또 매핑관계를 @Reuslts를 통해 하는 것이 아니라 26줄의 id = "CompanyMap", value로 id로 만든다.
		// 그 후, 아래 @ResultMap을 통해 재사용
		@Select("SELECT * FROM employee WHERE id=#{id}")
		@ResultMap("EmployeeMap")
		Employee getById(@Param("id") int id);
}
