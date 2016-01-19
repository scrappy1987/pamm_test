Feature: Manage project
  As a user
  I want to view projects
  So that I know what projects are in progress

  Scenario: I can manage projects when there are no existing projects
    Given I am on the dashboard
    And There are no projects
    And I choose sample
    When I choose manage project
    Then There are no projects for me to manage

   Scenario: I can manage projects when there are existing projects
     Given I am on the dashboard
     And There are three projects
     And I choose sample
     When I choose manage project
     Then There are three projects for me to manage

   Scenario: I can add a project when there are no currently existing projects
     Given I am on the dashboard
     And There are no projects
     And I choose sample
     When I choose add new project
     And I enter a title
     And I enter a summary
     And I enter information
     And I save the project
     When I close the confirmation dialogue
     Then There is now one project to manage