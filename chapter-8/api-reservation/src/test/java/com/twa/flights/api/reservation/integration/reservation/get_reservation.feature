Feature: Get Reservation by Id endpoint
  Background:
    * url AppUrl
    * def get_response_ok = read('./response/get_response.json')

  Scenario: Obtain the information about one reservation
    Given path '/1'
    When method GET
    Then status 200
    And match response == get_response_ok