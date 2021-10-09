package com.todomvc.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    /**
     *
     * @param listOfWebElements which element's texts are requested
     * @return List of String, this method basically get the web element text and assign them into list
     */
    public static List<String> getStringOfWebElement(List<WebElement> listOfWebElements){
        List<String> listOfString = new ArrayList<>();
        for (WebElement element : listOfWebElements) {
            listOfString.add(element.getText());
        }
        return listOfString;
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
    }

}
