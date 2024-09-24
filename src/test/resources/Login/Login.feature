Feature: Register Owner
  Owner Registration with postive and negative scenarios

  Background:
    Given go to the website


  @tag0
  Scenario: Owner Already Exist
    When enter username "standard_user" and password "secret_sauce"
    Then validate home page appeared

  @tag0
  Scenario Outline: Owner Already Exist
    When enter username <username> and password <password>
    Then validate home page appeared

    Examples:
      | username | password |
      | "locked_out_user"    | "secret_sauce"|
      | "user2 "   | "secret_sauce"|


  Scenario: Owner Already Exist
    Given user data is loaded
    Given go to the website
    When user logs in using the data "username" and password "password"


#  Scenario: Login with specific data
#    Given user data is loaded from CSV "csvdata.csv"
#    Given go to the website
#    And user logs in using data where "username" is "testUser" and gets "password"


