package host_away_project.pages;

import host_away_project.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Main_Page extends Base_Page{



    @FindBy(xpath = "//button[@type='button']/span[.='Filter']")
    public WebElement filterButton;

}
