package com.project.employee.loginservice.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static com.project.employee.loginservice.Utils.PropertyValidator.validateProperty;

@DisplayName("Property Validator Service")
class PropertyValidatorTest {


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("When Property is null or empty")
    void validateProperty_PropertyIsNullOrEmpty_ReturnsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateProperty(null,null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateProperty("",""));
    }



}