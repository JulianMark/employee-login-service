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

        @ApiModelProperty(notes="ID del empleado exito = <0")
        private Integer id;
        @ApiModelProperty(notes="Nombre del empleado exito = JOHN")
        private String name;
        @ApiModelProperty(notes="Apellido del empleado exito = CONNOR")
        private String lastName;
        @ApiModelProperty(notes="Tipo empleado exito = 1")
        private Integer idType;
        @ApiModelProperty(notes="Campaña actual exito = 1")
        private Integer idCampaign;
        @ApiModelProperty(notes="Mensaje de error, en caso de que falle el WS")
        private String errorMessage;

        public EmployeeResponse(String errorMessage) {
                this.errorMessage = errorMessage;
        }
}
