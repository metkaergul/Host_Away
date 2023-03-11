package host_away_project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AllListing_Page extends Main_Page {


    @FindBy(xpath = "//span[@class='sc-eGJWMs lkeyLH']/span")
    public WebElement numberInAll;

    @FindBy(xpath = "//div[@class='sc-gSYDnn wmfak']")
    public List<WebElement>allList;


    @FindBy(xpath = " (//div[@class='sc-gSYDnn wmfak'])[66]")
    public WebElement lastElementOfTheList;





}
