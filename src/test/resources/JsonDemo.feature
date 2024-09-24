Feature: Dynamic JSON handling

  @tag1
  Scenario: Write and verify data in JSON
    Given try this step
    When I add the following data to JSON:
      | username  | newUser           |
      | password  | newPassword       |
      | email     | newUser@example.com |
      | role      | admin             |
    And I save the data to JSON file "userData.json"
    And I read the JSON file "userData.json"
    Then the value of "username" should be "newUser"
    Then the value of "role" should be "admin"
