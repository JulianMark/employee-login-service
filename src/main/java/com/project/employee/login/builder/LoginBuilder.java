package com.project.employee.login.builder;

import com.project.employee.login.mapper.EmployeeMapper;
import com.project.employee.login.service.http.EmployeeRequest;
import com.project.employee.login.service.http.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class LoginBuilder implements Function<EmployeeRequest, ResponseEntity<EmployeeResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginBuilder.class);
    private final EmployeeMapper employeeMapper;

    @Autowired
    public LoginBuilder(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public ResponseEntity<EmployeeResponse> apply(EmployeeRequest request) {

        return Optional.ofNullable(employeeMapper.loginEmployee(request))
                .map(employeeResponse -> {
                   LOGGER.info("Se logueo correctamente el empleado {}", request.getNickname());
                   return ResponseEntity.ok(employeeResponse);
                }).orElseGet(() -> {
                    LOGGER.warn("No se pudo loguear al empleado {}", request.getNickname());
                    return ResponseEntity.noContent().build();
                });
    }
}
