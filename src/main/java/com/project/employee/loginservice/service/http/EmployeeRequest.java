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
public class EmployeeRequest {

    @ApiModelProperty(notes="Nickname del empleado necesario para login", required = true, example = "NICKNAME")
    private String nickname;

    @ApiModelProperty(notes="Password del empleado necesaria para el login", required = true, example = "123")
    private String password;
}
