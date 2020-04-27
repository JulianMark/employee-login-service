package com.project.employee.login.service.exception;

public class EmployeeResponseException extends RuntimeException {

    public EmployeeResponseException(String nickname) {
        super(String.format("No se pudo loguear el usuario: %s", nickname));
    }
}
