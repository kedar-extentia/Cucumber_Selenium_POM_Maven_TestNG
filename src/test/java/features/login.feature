# Login feature
# Tags: optional

Feature: Login to site

  @smoke
  Scenario Outline: Verify user is able to login successfully
    Given I launch URL
#    When I Login Gmail "kedar.extentia@gmail.com" and "mypassword"
#    When I login OrangeHRM
    And I login OrangeHRM with <username> and <password>
#    And I logout
    Examples:
      | username      | password      |
      | opensourcecms | opensourcecms |