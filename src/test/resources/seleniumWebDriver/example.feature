Feature: An example using Selenium WebDriver

  Scenario Outline: Finding some cheese
    # NOTE: Chrome is only supported up to version 114, hence commented out here
    Given I open the <browser> browser
    Given I am on the DuckDuckGo search page
    When I search for "Cheese!"
    Then the page title should start with "Cheese?"

    Examples:
      | browser   |
#      | "Chrome"  |
      | "Firefox" |
      | "Edge"    |
