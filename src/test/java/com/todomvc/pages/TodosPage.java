package com.todomvc.pages;

import com.todomvc.utilities.BrowserUtils;
import com.todomvc.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class TodosPage {

    public TodosPage() {
        PageFactory.initElements(Driver.get(), this);
    }


    @FindBy(css = ".header>h1")
    public WebElement header;

    @FindBy(className = "new-todo")
    public  WebElement textBox;

    @FindBy(css = ".view>label")
    public List<WebElement> todoListElement;

    @FindBy(css = ".todo-count>strong")
    public WebElement leftItemsCount;

    @FindBy(css = ".view>input")
    public List<WebElement> checkBoxList;

    @FindBy(className = "clear-completed")
    public WebElement clearCompletedButton;

    /*
     * For getting all, active and completed buttons WebElement
     * We need to use "All", "Active" or "Completed" as parameter
     */
    public WebElement getButton(String string){
        return Driver.get().findElement(By.xpath("//a[text()='" + string + "']"));
    }


    /*
     * This method provide us to check the left items count
     */
    public void checkLeftItemsCount(){
        List<WebElement> leftItemsList = new ArrayList<>();
        for (WebElement element : checkBoxList) {
            if(!element.isSelected()){
                leftItemsList.add(element);
            }
        }
        int actualCount = leftItemsList.size();
        int expectedCount = Integer.parseInt(leftItemsCount.getText());
        Assert.assertEquals( "Verify left item size", expectedCount,actualCount);
    }


    /*
     * This method provide us to add items as list and checks the left items count
     * with using checkLeftItemsCount() method
     */
    public void addInputList(List<String> inputList){
        for (String input : inputList) {
            textBox.sendKeys(input + Keys.ENTER);
            checkLeftItemsCount();
        }
    }


    /*
     * This method provide us to get the checkbox WebElement
     * with using text of item as parameter
     */
    public WebElement getElementCheckBox(String string){
        return Driver.get().findElement(By.xpath("//label[text()='" + string + "']/../input"));
    }


    /*
     * This method provide us to delete items as list
     * with using text of items list
     * Controls the item is in the list before delete
     * Controls the item is not in the list after delete
     */
    public void clickDeleteButton(List<String> deleteList){
        WebElement element;
        for (String str : deleteList) {
            checkLeftItemsCount();
            element = Driver.get().findElement(By.xpath("//label[text()='" + str + "']/../button"));
            Assert.assertTrue("Verify list contains" + str ,BrowserUtils.getStringOfWebElement(todoListElement).contains(str));
            BrowserUtils.clickWithJS(element);
            Assert.assertFalse("Verify list not contains" + str ,BrowserUtils.getStringOfWebElement(todoListElement).contains(str));
        }
    }


    /*
     * This method provide to select items
     * with using String list
     */
    public void selectItems(List<String> strings){
        for (String string : strings) {
            WebElement element = getElementCheckBox(string);
            element.click();
            Assert.assertTrue("Verify checkbox selected", element.isSelected());
        }
    }


    /*
     * This method provide to check items which is not selected
     * with using String list
     */
    public void checkNotSelectedItems(List<String> strings){
        for (String string : strings) {
            WebElement element = getElementCheckBox(string);
            Assert.assertFalse("Verify checkbox not selected", element.isSelected());
        }
    }


    /*
     * This method provides to check the all items are not selected
     */
    public void controlNotSelected(){
        for (WebElement element : checkBoxList) {
            Assert.assertFalse(element.isSelected());
        }
    }


    /*
     * This method provides to check the all items are selected
     */
    public void controlSelected(){
        for (WebElement element : checkBoxList) {
            Assert.assertTrue(element.isSelected());
        }
    }


    /*
     * This method provides to add as many items to the list as user want
     */
    public void addItemsWithCount(int count){
        for (int i=1; i<=count; i++) {
            textBox.sendKeys(String.valueOf(i) + Keys.ENTER);
        }
    }


}
