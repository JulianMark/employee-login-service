package com.project.employee.login.service.exception;

import com.project.employee.login.service.http.EmployeeRequest;

import java.util.Optional;

public class RequestIllegalArgumentException {

    public static void requestValidate(EmployeeRequest request) {

        Optional.of(request)
                .map(nickname -> request.getNickname())
                .map(nickname -> nickname.isEmpty() ? null : nickname)
                .map(password -> request.getPassword())
                .map(password -> password.isEmpty() ? null : password)
                .orElseThrow(() -> new IllegalArgumentException("el request o algunas de sus propiedades no puede ser nula"));
    }
}
