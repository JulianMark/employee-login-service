package com.project.employee.loginservice.service;


import com.project.employee.loginservice.mapper.EmployeeMapper;
import com.project.employee.loginservice.service.http.EmployeeRequest;
import com.project.employee.loginservice.service.http.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.not;

@DisplayName("Employee Login Service")
class LoginServiceTest {

    private static final EmployeeRequest VALID_EMPLOYEE_REQUEST = new EmployeeRequest("TEST","123456");

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private LoginService sut;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Obtain employee from login")
    class ObtainEmployee {

        @Nested
        @DisplayName("Should return 400 (Bad Request)")
        class ObtainEmployeeBadRequestTest {

            @Test
            @DisplayName("When employee request is null")
            public void loginEmployee_RequestIsNull_ReturnsBadRequest(){
                ResponseEntity<EmployeeResponse> employeeResponseEntity = sut.loginEmployee(null);
                assertThat("Status Code Response",
                        employeeResponseEntity.getStatusCode(),
                        is(HttpStatus.BAD_REQUEST));
            }

            @Test
            @DisplayName("When request nickname is null or empty")
            public void loginEmployee_NicknameIsNullOrEmpty_ReturnsBadRequest(){
                EmployeeRequest employeeRequest = new EmployeeRequest(null,"123");
                ResponseEntity<EmployeeResponse> employeeResponseEntity = sut.loginEmployee(employeeRequest);
                assertThat("Status Code Response",
                        employeeResponseEntity.getStatusCode(),
                        is(HttpStatus.BAD_REQUEST));
            }

            @Test
            @DisplayName("When request password is null or empty")
            public void loginEmployee_PasswordIsNullOrEmpty_ReturnsBadRequest(){
                EmployeeRequest employeeRequest = new EmployeeRequest("JMARK",null);
                ResponseEntity<EmployeeResponse> employeeResponseEntity = sut.loginEmployee(employeeRequest);
                assertThat("Status Code Response",
                        employeeResponseEntity.getStatusCode(),
                        is(HttpStatus.BAD_REQUEST));
            }
        }

        @Nested
        @DisplayName("Should return 500 (Internal Server Error)")
        class ObtainEmployeeInternalServerErrorTest {

            @Test
            @DisplayName("When EmployeeMapper throws Exception")
            public void loginEmployee_EmployeeMapperThrowException_ReturnsInternalServerError(){
                when(employeeMapper.loginEmployee(any())).thenThrow(new RuntimeException ("something bad happened"));
                ResponseEntity<EmployeeResponse> employeeResponseEntity = sut.loginEmployee(VALID_EMPLOYEE_REQUEST);
                assertThat("Status Code Response",
                        employeeResponseEntity.getStatusCode(),
                        is(HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }

        @Nested
        @DisplayName("Should return 204 (No Content)")
        class ObtainEmployeeNoContentTest {

            @Test
            @DisplayName("When EmployeeMapper throws Exception")
            public void loginEmployee_EmployeeResponseIsNull_ReturnsNoContent() {
                when(employeeMapper.loginEmployee(any())).thenReturn(null);
                ResponseEntity<EmployeeResponse> employeeResponseEntity = sut.loginEmployee(VALID_EMPLOYEE_REQUEST);
                assertThat("Status Code Response",
                        employeeResponseEntity.getStatusCode(),
                        is(HttpStatus.NO_CONTENT));
            }
        }

        @Nested
        @DisplayName("Should return 200 (OK)")
        class ObtainEmployeeStatusOKTest {

            @Test
            @DisplayName("When No Exception is Caught")
            public void loginEmployee_NoExceptionCaught_ReturnsOk(){
                when(employeeMapper.loginEmployee(any()))
                        .thenReturn(new EmployeeResponse(1,"NAME","LASTNAME",1,null));
                ResponseEntity<EmployeeResponse> employeeResponseEntity = sut.loginEmployee(VALID_EMPLOYEE_REQUEST);

                assertThat(employeeResponseEntity.getStatusCode(), is (HttpStatus.OK));
                assertThat(employeeResponseEntity.getBody(),is(not(nullValue())));
            }
        }
    }
}