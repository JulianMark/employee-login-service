package com.project.employee.login.builder;

import com.project.employee.login.mapper.EmployeeMapper;
import com.project.employee.login.service.http.EmployeeRequest;
import com.project.employee.login.service.http.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginBuilderTest {

    private EmployeeRequest VALID_CREDENTIALS = new EmployeeRequest("JMARK", "123");
    private EmployeeRequest INVALID_CREDENTIALS = new EmployeeRequest("ds", "123");

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private LoginBuilder sut;

    @BeforeEach
    public void setUp () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When mapper not returns employee should return status code 204 (No Content)")
    public void apply_MapperNotReturnsEmployee_ReturnsNoContent () {

        when(employeeMapper.loginEmployee(INVALID_CREDENTIALS)).thenReturn(null);

        ResponseEntity<EmployeeResponse> responseEntity = sut.apply(INVALID_CREDENTIALS);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    @DisplayName("When mapper returns employee should return status code 200 (OK)")
    public void apply_MapperReturnsEmployee_ReturnsOk () {

        EmployeeResponse expected = new EmployeeResponse(1,"JULIAN","MARK", 1, 1,null);

        when(employeeMapper.loginEmployee(VALID_CREDENTIALS)).thenReturn(expected);

        ResponseEntity<EmployeeResponse> responseEntity = sut.apply(VALID_CREDENTIALS);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().toString(), is(expected.toString()));
    }
}