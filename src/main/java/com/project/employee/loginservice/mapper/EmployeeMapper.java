package com.project.employee.loginservice.mapper;

import com.project.employee.loginservice.service.http.EmployeeRequest;
import com.project.employee.loginservice.service.http.EmployeeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeMapper {

    EmployeeResponse loginEmployee(@Param("employeeLogin") EmployeeRequest employeeRequest);
}
