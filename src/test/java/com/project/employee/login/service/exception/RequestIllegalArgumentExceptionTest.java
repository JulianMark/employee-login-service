package com.project.employee.login.service.exception;

import com.project.employee.login.service.http.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static com.project.employee.login.service.exception.RequestIllegalArgumentException.requestValidate;

class RequestIllegalArgumentExceptionTest {

    @ParameterizedTest
    @ArgumentsSource(ParametersArgumentsSource.class)
    @DisplayName("When any of the parameters is null or empty  should throws IllegalArgumentException")
    public void requestValidate_InvalidParam_ThrowsIllegalArgumentException(EmployeeRequest request) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> requestValidate(request));
    }

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
}