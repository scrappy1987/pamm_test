Feature: Displays a list of all cars in the site
  As a Site administrator
  I want to be able to see a list of all cars parked in the building
  So that I can report them in case of a fire

  Scenario: Display a list of all Cars in the site
    Given a list of vehicles parked in the site is known
    When a request is made to retrieve all parked vehicles
    Then a list with all parked vehicles is retrieved
