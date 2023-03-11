package host_away_project.host_awayTest;

import host_away_project.pages.AllListing_Page;
import host_away_project.pages.Filters_PopUp;
import host_away_project.utilities.BrowserUtil;
import host_away_project.utilities.Driver;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Check_AllList extends Filters_PopUp {

    AllListing_Page allListing_page = new AllListing_Page();

    @Test
    @Description("Checking whether All listings page has the same amount of listings as the 'All' label")
    public void allListVerification() {
        //We need to land on to the given url
        Driver.getDriver().get("https://kamil-demo.alpinizm.uz/all-listings");

        // scroll down to the bottom of the page by using scrollToTheBottom which is located within BrowserUtil utility class
        BrowserUtil.scrollToTheBottom();


        // get the number of list  elements on the page and do assertion
        int listSize = allListing_page.allList.size();
        int numberInAllLabel = Integer.parseInt(allListing_page.numberInAll.getText().substring(1, 3));

        Assert.assertEquals(listSize, numberInAllLabel);


    }


    //the reason why I put @After test annotation within the Check_AllList class is that I wanted to use my driver instance
    //throughout my test suite that is created within the host_away.xml file
    @AfterTest
    public void tearDown() {
        Driver.closeDriver();
    }


}
