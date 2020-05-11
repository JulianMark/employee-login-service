package com.project.employee.login.service;

import com.project.employee.login.service.http.EmployeeRequest;
import com.project.employee.login.service.http.EmployeeResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Employee Login Service")
class LoginServiceTest {

    private static final EmployeeRequest VALID_EMPLOYEE_REQUEST = new EmployeeRequest("TEST", "123456");

    static class ParametersArgumentsSource implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(new EmployeeRequest()),
                    Arguments.of(new EmployeeRequest(null, "123")),
                    Arguments.of(new EmployeeRequest("", "123")),
                    Arguments.of(new EmployeeRequest("JOHN", null)),
                    Arguments.of(new EmployeeRequest("JOHN", ""))
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParametersArgumentsSource.class)
    @DisplayName("When any of the request parameters is null or empty should return 400 (Bad Request)")
    public void loginEmployee_RequestParamAreNullOrEmpty_ReturnsBadRequest(EmployeeRequest request) {

        LoginService sut = new LoginService(null);

        ResponseEntity<EmployeeResponse> responseEntity = sut.loginEmployee(request);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When EmployeeMapper throws Exception should return 500 (Internal Server Error)")
    public void loginEmployee_EmployeeMapperThrowException_ReturnsInternalServerError() {

        Function<EmployeeRequest, ResponseEntity<EmployeeResponse>> builder = param
                -> ResponseEntity.status(500).build();
        LoginService sut = new LoginService(builder);

        ResponseEntity<EmployeeResponse> responseEntity = sut.loginEmployee(VALID_EMPLOYEE_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("When EmployeeResponse is null should return 204 (No Content)")
    public void loginEmployee_EmployeeResponseIsNull_ReturnsNoContent() {

        Function<EmployeeRequest, ResponseEntity<EmployeeResponse>> builder = param
                -> ResponseEntity.noContent().build();
        LoginService sut = new LoginService(builder);

        ResponseEntity<EmployeeResponse> responseEntity = sut.loginEmployee(VALID_EMPLOYEE_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When No Exception is caught should return 200 (OK)")
    public void loginEmployee_NoExceptionCaught_ReturnsOk() {

        EmployeeResponse responseExpected = new EmployeeResponse(1, "JOHN", "DOE", 1, 1, "");
        Function<EmployeeRequest, ResponseEntity<EmployeeResponse>> builder = param -> ResponseEntity.ok(responseExpected);
        LoginService sut = new LoginService(builder);

        ResponseEntity<EmployeeResponse> responseEntity = sut.loginEmployee(VALID_EMPLOYEE_REQUEST);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().toString(), is(responseExpected.toString()));
    }
}
