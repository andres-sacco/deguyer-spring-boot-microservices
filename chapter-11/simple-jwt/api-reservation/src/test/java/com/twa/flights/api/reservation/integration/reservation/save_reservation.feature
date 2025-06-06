Feature: Get Reservation by Id endpoint
  Background:
    * url AppUrl
    * def save_request_ok = read('./request/save_request.json')
    * def save_response_ok = read('./response/save_response.json')

  Scenario: Obtain the information about one reservation
    Given path '/'
    And request save_request_ok
    When method POST
    Then status 201
    And match response == save_response_ok