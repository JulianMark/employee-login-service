package com.project.employee.loginservice.Utils;

public class PropertyValidator {

    public static void validateProperty (String property, String propertyName) {
        if (property == null){
            throw new IllegalArgumentException(propertyName+" no puede ser nulo");
        }
        if (property.isEmpty()){
            throw new IllegalArgumentException(propertyName+" no puede estar vacio");
        }
    }
}
