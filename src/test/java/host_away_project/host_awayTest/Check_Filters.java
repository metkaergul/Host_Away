package host_away_project.host_awayTest;

import host_away_project.pages.Filters_PopUp;
import host_away_project.utilities.BrowserUtil;
import host_away_project.utilities.Driver;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class Check_Filters extends Filters_PopUp {


    Filters_PopUp filters_popUp = new Filters_PopUp();

    @BeforeTest
    @Description("This method is for avoiding repetitive steps in each execution")
    public void setUpBackground() {

        Driver.getDriver().get("https://kamil-demo.alpinizm.uz/");

        BrowserUtil.waitForVisibility(searchButton);

        filters_popUp.searchButton.click();

        filters_popUp.filterButton.click();

    }

    @Test
    @Description("Checking min and max values of the bed , bedrooms and bathrooms")
    public void minAndMaxValueTest() {

        //These code blocks are to find the max value of bed ,bedrooms and bathrooms and validate if those results are matching with
        //the expected results
        BrowserUtil.scrollUntilTarget(filters_popUp.amenitiesDiv);

        //max values of the bed ,bedrooms and bathrooms
        int maxExpectedBed = 10;
        int maxActualBed = BedMaxValues();
        Assert.assertEquals(maxExpectedBed, maxActualBed);

        int maxExpectedBedroom = 10;
        int maxActualBedroom = BedroomMaxValues();
        Assert.assertEquals(maxExpectedBedroom, maxActualBedroom);

        int maxExpectedBathroom = 10;
        int maxActualBathroom = BathroomMaxValues();
        Assert.assertEquals(maxExpectedBathroom, maxActualBathroom);

        //min values of the bed ,bedrooms and bathrooms
        int minExpectedBed = 0;
        int minActualBed = BedMinValues();
        Assert.assertEquals(minExpectedBed, minActualBed);

        int minExpectedBedroom = 0;
        int minActualBedroom = BedroomMinValues();
        Assert.assertEquals(minExpectedBedroom, minActualBedroom);

        int minExpectedBathroom = 0;
        int minActualBathroom = BathroomMinValues();
        Assert.assertEquals(minExpectedBathroom, minActualBathroom);


    }

    @Test
    @Description("Checking each and every checkbox is functioning as it is expected by clicking  them one by one")
    public void checkBoxValidation() {


        //this method  are for checking each and every checkBoxes is enabled BEFORE clicking them one by one
        //if any of those checkboxes is not disabled then method gives warning that shows which checkbox is disabled
        areCheckBoxesEnabled(amenitiesCheckBoxes);


        //this method are for checking each and every checkbox is functioning as it is expected by clicking  them one by one
        // But first  we need to scroll down until all checkboxes become visible otherwise we are getting "NoSuchElementException"
        BrowserUtil.scrollUntilTarget(amenitiesCheckBoxes.get(amenitiesCheckBoxes.size() - 1));

        areCheckBoxesClickable(filters_popUp.amenitiesCheckBoxes, filters_popUp.inputCheckBoxes);


    }

    @Test
    @Description("Checking buttons in the filters without clicking apply")
    public void buttonCheck() {

        Assert.assertTrue(filters_popUp.applyButton.isEnabled());

        Assert.assertTrue(filters_popUp.clearAll.isEnabled());

    }

    @Test
    @Description("Checking entry fields is functioning as expected")
    public void entryFieldsCheck() {

        //When we landed on the Filters pop-up, by default priceEntryFields are disabled because dates are not selected beforehand
        //And we need to make sure that entry fields "disabled" attribute should be "true"
        String isPriceFromDisabled = filters_popUp.priceFrom.getAttribute("disabled").trim();
        Assert.assertEquals(isPriceFromDisabled, "true");

        String isPriceToDisabled = filters_popUp.priceTo.getAttribute("disabled").trim();
        Assert.assertEquals(isPriceToDisabled, "true");

        //Without selecting date beforehand ,there should be a warning message which says "To filter by price, please select dates"
        String expectedWarningMessage = "To filter by price, please select dates";
        String actualWarningMessage = filters_popUp.warningMessageInPriceField.getText();
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);

        //________Following code block represent a scenario in which entry fields should not be disabled any more______//


        //We need to test whether price entry fields still disabled or not  after selecting date beforehand
        filters_popUp.closeFilterPopUp.click();

        //we are selecting any available date from check in
        filters_popUp.checkIn.click();
        filters_popUp.possibleCalendarDays.get(1).click();
        //we are selecting available  date from check out(it must not be the same day as check in date)
        filters_popUp.checkOut.click();
        filters_popUp.possibleCalendarDays.get(12).click();

        //after selecting dates we need to click filter button again
        filters_popUp.filterButton.click();

        //after selecting date from check in and out , price entry fields are not disabled any more within the filters section
        String isPriceFromDisabledAfterSpecifyingDate = filters_popUp.priceFrom.getAttribute("disabled");
        String isPriceToDisabledAfterSpecifyingDate = filters_popUp.priceTo.getAttribute("disabled");

        Assert.assertEquals(isPriceFromDisabledAfterSpecifyingDate, null);

        Assert.assertEquals(isPriceToDisabledAfterSpecifyingDate, null);

        //I sent keys into the fields as a visual example
        filters_popUp.priceFrom.sendKeys("123");
        filters_popUp.priceTo.sendKeys("345");

    }

    @Test
    @Description("Validate if 'clear all' is behaving as required ")
    public void clearAllTest() {

        //I picked up Bedrooms and checkBox for validating clearAll function
        //First I clicked bedrooms plus button all the way up until edge number ,and I Clicked all checkboxes
        while (BedroomsPlusButton.isEnabled()) {
            BedroomsPlusButton.click();
        }
        //for making sure that after clicking bedrooms plus button all the way through the edge value I am printing the bedrooms value(IT SHOULD BE 10)
        int bedroomsNumberAfterClickingUntilMax = Integer.parseInt(BedroomsNumber.getText());
        System.out.println(bedroomsNumberAfterClickingUntilMax);

        //for making sure that all the checkboxes are clicked  I am asserting that each and every checkboxes' "checked" attribute must be true
        for (WebElement each : amenitiesCheckBoxes) {
            each.click();

        }
        for (WebElement each : inputCheckBoxes) {

            Assert.assertEquals(each.getAttribute("checked"), "true");
        }

        //then  the "clear all" is clicked
        filters_popUp.clearAll.click();


        //after clear all  is clicked ,previously clicked checkboxes should be removed(which has null checked attribute value)
        for (WebElement each : inputCheckBoxes) {
            Assert.assertEquals(each.getAttribute("checked"), null);

        }

        //after clear all is clicked ,bedrooms number should be 0 which is minimum and default value at the same time
        int expectedBedroomNumberAfterClearAll = 0;
        int actualNumberAfterClearAll = Integer.parseInt(BedroomsNumber.getText());
        Assert.assertEquals(expectedBedroomNumberAfterClearAll, actualNumberAfterClearAll);


    }

}
