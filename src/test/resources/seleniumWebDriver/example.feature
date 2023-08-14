Feature: An example using Selenium WebDriver

  Scenario: Finding some cheese
    Given I am on the DuckDuckGo search page
    When I search for "Cheese!"
    Then the page title should start with "Cheese?"
