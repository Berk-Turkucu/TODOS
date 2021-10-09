package com.todomvc.step_definitions;

import com.todomvc.pages.TodosPage;
import com.todomvc.utilities.BrowserUtils;
import com.todomvc.utilities.ConfigurationReader;
import com.todomvc.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import java.util.List;

public class TodosStepDefs {

    TodosPage todosPage = new TodosPage();

    @Given("user should be on the homepage and headers should be {string}")
    public void userShouldBeOnTheHomepageAndHeadersShouldBe(String header) {
        Driver.get().get(ConfigurationReader.get("url"));
        Assert.assertEquals("Verify todos header" ,header, todosPage.header.getText());
    }

    @When("user writes {string} to the text box and press enter")
    public void userWritesToTheTextBoxAndPressEnter(String input) {
        todosPage.textBox.sendKeys(input + Keys.ENTER);
    }

    @Then("user should see {string} in the list")
    public void userShouldSeeInTheList(String item) {
        Assert.assertTrue(BrowserUtils.getStringOfWebElement(todosPage.todoListElement).contains(item));
    }

    @When("user adds the input and user should see the item in the list")
    public void userAddsTheInputAndUserShouldSeeTheItemInTheList(List<String> inputList) {
        todosPage.addInputList(inputList);
        Assert.assertEquals("Verify input list", inputList, BrowserUtils.getStringOfWebElement(todosPage.todoListElement));
    }

    @Then("user should select the the following items, and user should delete this items")
    public void user_should_select_the_the_following_items_and_user_should_delete_this_items(List<String> selectedList) {
        todosPage.selectItems(selectedList);
        todosPage.clickDeleteButton(selectedList);
    }

    @Then("following options need to be not selected and user should delete this items")
    public void following_options_need_to_be_not_selected_and_user_should_delete_this_items(List<String> notSelectedList) {
        todosPage.checkNotSelectedItems(notSelectedList);
        todosPage.clickDeleteButton(notSelectedList);
    }

    @Then("user should select the the following items")
    public void user_should_select_the_the_following_items(List<String> selectedList) {
        todosPage.selectItems(selectedList);
    }


    @When("user clicks the {string} button and user should see only unselected items")
    public void userClicksTheButtonAndUserShouldSeeOnlyUnselectedItems(String string) {
        todosPage.getButton(string).click();
        todosPage.controlNotSelected();
        todosPage.checkLeftItemsCount();
    }

    @And("user clicks the {string} button and user should see only selected items")
    public void userClicksTheButtonAndUserShouldSeeOnlySelectedItems(String string) {
        todosPage.getButton(string).click();
        todosPage.controlSelected();
    }

    @And("user clicks the Clear completed button")
    public void userClicksTheClearCompletedButton() {
        todosPage.clearCompletedButton.click();
    }

    @When("user should add {int} items to the list")
    public void user_should_add_items_to_the_list(Integer num) {
        todosPage.addItemsWithCount(num);
    }

    @Then("user should see {int} items on the list")
    public void user_should_see_items_on_the_list(int num) {
        Assert.assertEquals("Verify list size", num, todosPage.todoListElement.size());
    }
}
