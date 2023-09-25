@wip
Feature: Test the login functionality of Gmail

  Scenario Outline: Log in to Gmail
    # NOTE: Chrome is only supported up to version 114, hence commented out here
    Given I open the <browser> browser
    When I navigate to Gmail
    Then I see the input for user name on google authentication page
    When I input the user name of the Gmail
    When I click Next button on the Gmail user name input page
    # NOTE: the following will fail due to security
    Then I see the input for password on google authentication page
    When I input the password of the Gmail
    When I click Next button on the Gmail password input page

    Examples:
      | browser   |
      | "Chrome"  |
      | "Firefox" |
      | "Edge"    |