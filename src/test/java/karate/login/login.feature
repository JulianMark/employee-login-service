Feature: Login

  Background:
    * url baseUrl
    * def activeBase = "employee/login"

    Scenario Outline: Login employee with status <statusCode>

      Given path activeBase
      And request <requestBody>
      When method POST
      Then status <statusCode>
      And match response == <expected>

      Examples:
        | requestBody                              | statusCode | expected |
        | { "nickname": "JMARK", "password": 123 } | 200        | { "id": 1, "name": "JULIAN", "lastName": "MARK", "idType": 1, "idCampaign": 3, "errorMessage": null } |
        | { "nickname": "DS", "password": 123 }    | 204        | '' |
        | { "nickname": null, "password": 123 }    | 400        | {"id": null, "name": null, "lastName": null, "idType": null, "idCampaign": null, "errorMessage": "el request o algunas de sus propiedades no puede ser nula" }