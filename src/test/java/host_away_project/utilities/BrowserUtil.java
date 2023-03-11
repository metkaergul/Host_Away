package host_away_project.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class BrowserUtil {


    //this method is used for handling the synchronization issues with dynamic way it is waiting for specified element to be visible
    //The reason I made this method static is that we can easily call the method without creating object
    public static void waitForVisibility(WebElement element){

        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);

        wait.until(ExpectedConditions.visibilityOf(element));
    }


    //This methods name is self-explanatory which is for scrolling the page until seeing the target element
    public static void scrollUntilTarget(WebElement element){

        Actions actions=new Actions(Driver.getDriver());

        actions.moveToElement(element).perform();


    }

    //This method is for scrolling down to the bottom of the page when the page has not continuously scrolled by default
    public static void scrollToTheBottom(){

        // scroll down to the bottom of the page
        while (true) {
            // get the current height of the page
            long lastHeight = (long) ((JavascriptExecutor) Driver.getDriver()).executeScript("return document.body.scrollHeight");

            // press the End key to scroll to the bottom of the page
            Actions actions = new Actions(Driver.getDriver());
            actions.sendKeys(Keys.END).build().perform();

            // wait for the page to load new content
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // get the new height of the page
            long newHeight = (long) ((JavascriptExecutor) Driver.getDriver()).executeScript("return document.body.scrollHeight");

            // if the height of the page has not changed, break out of the loop
            if (newHeight == lastHeight) {
                break;
            }
        }

    }

    //this is for sleeping statically it waits no matter whats, within a given time
    public static void sleep(double second)  {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



}
