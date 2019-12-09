package com.project.employee.loginservice.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

        @ApiModelProperty(notes="Resultado de id de empleado exito = <0")
        private Integer id;
        private String name;
        private String lastName;
        private String nickname;
        private Integer idType;
        private Integer donations;
        private float hoursWorked;
        @ApiModelProperty(notes="Mensaje de error, en caso de que falle el WS")
        private String errorMessage;

        public EmployeeResponse(String errorMessage) {
                this.errorMessage = errorMessage;
        }
}
