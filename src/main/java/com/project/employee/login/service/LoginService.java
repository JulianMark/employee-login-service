package com.project.employee.login.service;

import com.project.employee.login.service.http.EmployeeRequest;
import com.project.employee.login.service.http.EmployeeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import static com.project.employee.login.service.exception.RequestIllegalArgumentException.requestValidate;

@RestController
@Api(value = "Employee login WS", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final Function<EmployeeRequest, ResponseEntity<EmployeeResponse>> loginBuilder;

    @Autowired
    public LoginService(Function<EmployeeRequest, ResponseEntity<EmployeeResponse>> loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping(
            value = "employee/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Obtener empleado por login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtienen los datos del empleado", response = EmployeeResponse.class),
            @ApiResponse(code = 204, message = "El usuario o password son incorrectos", response = EmployeeResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = EmployeeResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = EmployeeResponse.class)
    })
    public ResponseEntity<EmployeeResponse> loginEmployee(
            @RequestBody EmployeeRequest employeeRequest) {
        try {
            requestValidate(employeeRequest);
            return loginBuilder.apply(employeeRequest);
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("Los parametros ingresados son invalidos ", iae);
            return ResponseEntity.badRequest().body(new EmployeeResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error al intentar loguear el usuario {}", employeeRequest.getNickname(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmployeeResponse(ex.getMessage()));
        }
    }

}
