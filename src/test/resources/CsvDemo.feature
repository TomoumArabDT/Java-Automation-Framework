Feature: Dynamic CSV handling

  @tag2
  Scenario: Write and verify data in CSV
    When I add the following data to CSV:
      | username  | newUser           |
      | password  | newPassword       |
      | email     | newUser@example.com |
      | role      | admin             |
    And I save the data to CSV file "userData.csv"
    And I read the CSV file "userData.csv"
    Then the value in row 0 and column "username" should be "newUser"
    Then the value in row 0 and column "role" should be "admin"
