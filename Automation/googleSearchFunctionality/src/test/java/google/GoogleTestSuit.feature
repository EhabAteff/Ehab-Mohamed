Feature: Google Search UI Functionality

  @Run
  Scenario Outline: Test the navigation to URL using Chrome
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    Then  Search textbox appears

    Examples:
      | website |
      | Google  |

  @Run
  Scenario Outline: Searching valid Data using google search engine
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   User search for valid "<validData>"
    Then  Page url changes
    And  "<validData>" Appears in the search results

    Examples:
      | website | validData                                       |
      | Google  | Youtube                                         |
      | Google  | this is a test on selenium for instabug by ihab |

  @Run
  Scenario Outline: Searching valid Feeling Lucky Data
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   User use feeling lucky search with "<validData>"
    Then  Page url changes

    Examples:
      | website | validData |
      | Google  | 2         |
      | Google  | 3         |
      | Google  |           |

  @Run
  Scenario Outline: Searching invalid Data using google search engine
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   Search for invalid "<invalidData>"
    Then  No action is taken if "<invalidData>" is empty
    And   should not match any results if "<invalidData>" has a value

    Examples:
      | website | invalidData                      |
      | Google  | qowpekfnfnajskdnunfqwneqmjlkqmeq |
      | Google  |                                  |

  @Run
  Scenario Outline: Searching invalid Feeling Lucky Data
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   User use invalid feeling lucky search with "<invalidData>"
    Then  Page url changes
    And   should not match any results if "<invalidData>" has a value

    Examples:
      | website | invalidData                      |
      | Google  | qowpekfnfnajskdnunfqwneqmjlkqmeq |

  @Run
  Scenario Outline: Testing Main Interface UI
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   User switches page language to English
    Then  Voice search button is displayed
    And   Magnifier icon is displayed

    Examples:
      | website |
      | Google  |

  @Run
  Scenario Outline: Testing Suggestions list Display
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   User enters "<validData>" in search box
    Then  Search Suggestions list Displays

    Examples:
      | website | validData |
      | Google  | instab    |
      | Google  | نور       |

  @Run
  Scenario Outline: Deleting Latest Search Record Suggestion
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    And   User search for valid "<validData>"
    And   Navigate to "<website>" search engine
    And   User enters "<validData>" in search box
    Then  Search Suggestions list Displays
    And   "<validData>" should Display as the first suggestion
    When  User clicks the remove button
    Then  "<validData>" should not Display in the first suggestion

    Examples:
      | website | validData |
      | Google  | instabug   |

  @Run
  Scenario Outline: Test Google Language Url
    Given User has access to chrome browser
    When  Navigate to "<website>" search engine
    Then  Hyperlink is Displayed
    When  User Switch Google to "<Language>"
    Then  Google is Displayed in "<Language>"
    And   Search features does not change

    Examples:
      | website | Language |
      | Google  | English  |
      | Google  | العربية  |
