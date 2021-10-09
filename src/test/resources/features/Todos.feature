@done
Feature: TODOS application features

  Background:
    Given user should be on the homepage and headers should be "todos"


  # Adding items one by one to the list by using Scenario Outline
  Scenario Outline: User should able to add items to the list one by one
    When user writes "<itemName>" to the text box and press enter
    Then user should see "<itemName>" in the list
    Examples:
      | itemName              |
      | 1 kg banana           |
      | 2 kg apple            |
      | 3 l milk              |
      | 2 kg orange           |
      | -+*/\"!'^+$%&/()[]={} |


  # Adding items one by one to the list by using Scenario
  Scenario: User should able to add items to the list
    When user writes "dog" to the text box and press enter
    Then user should see "dog" in the list
    When user writes "cat" to the text box and press enter
    Then user should see "cat" in the list
    When user writes "bird" to the text box and press enter
    Then user should see "bird" in the list
    When user writes "duck" to the text box and press enter
    Then user should see "duck" in the list


  # Adding items to the list by using Scenario and list
  Scenario: User should able to add items to the list by using list
    When user adds the input and user should see the item in the list
      | Norway  |
      | Sweden  |
      | Denmark |
      | Finland |


  # Control the check mark
  Scenario: Control selected items and delete function(Also checks, left items size, in adding and deleting item steps)
    When user adds the input and user should see the item in the list
      | One   |
      | Two   |
      | Three |
      | Four  |
      | Five  |
      | Six   |
    Then user should select the the following items, and user should delete this items
      | One   |
      | Three |
      | Five  |
    Then following options need to be not selected and user should delete this items
      | Two  |
      | Four |
      | Six  |

  # Control Buttons
  Scenario: Active, Completed, Clear Completed, All Buttons
    When user adds the input and user should see the item in the list
      | One   |
      | Two   |
      | Three |
      | Four  |
    Then user should select the the following items
      | Two  |
      | Four |
    And user clicks the "Active" button and user should see only unselected items
    And user clicks the "Completed" button and user should see only selected items
    And user clicks the Clear completed button
    And user clicks the "All" button and user should see only unselected items


  Scenario: User should be able to add as many items to the list as they want
    When user should add 100 items to the list
    Then user should see 100 items on the list




















