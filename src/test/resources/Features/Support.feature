Feature: Navigate to Support page

  Background:
    #Given user launched the browser and navigate to the URL
    And user should scroll down to the page footer
    When user should Click on Support link under Help and Support category

  @regression
  Scenario Outline: search in support page
    And user should type "<SearchedTopic>" in the search bar
    And user should Click on the magnify to start the search
    Then user should click on the link “ How to Protect Your Personal Information from Hackers and Online Threats”
    And user click on Related Articles link
    And user click on Online Security link
    Then user should see page title on the top of the page "Online Security"
        Examples:
       |SearchedTopic|
       |how I protect my personal information|


 # @checkBrokenLinks
 # Scenario Outline: search for broken links in Support page
 #   And user should type "<SearchedTopic>" in the search bar
 #   And user should Click on the magnify to start the search
 #   Then user should click on the link “ How to Protect Your Personal Information from Hackers and Online Threats”
 #   Then user Verify all links of the opened page are working and that there is no broken links

 #       Examples:
 #       |SearchedTopic|
 #       |how I protect my personal information|