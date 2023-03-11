package host_away_project.pages;

import host_away_project.utilities.BrowserUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class Filters_PopUp extends Main_Page {



    //Amenities checkboxes in the filters
    @FindBy(xpath = "//span[@class='sc-htmcrh emRwHY']")
    public List<WebElement> amenitiesCheckBoxes;

    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> inputCheckBoxes;

    //__________________________________________________

    //These are the buttons that is located under filters section
    @FindBy(xpath = "//b[.='Clear all']")
    public WebElement clearAll;

    @FindBy(xpath = "//button[@type='button']/span[.='Apply']")
    public WebElement applyButton;


    //Rooms and beds section in the filters for finding the min and max values and doing assertion

    @FindBy(xpath = " //div[.='Beds']/../div/div/span")
    public WebElement BedsNumber;

    @FindBy(xpath = " //div[.='Bedrooms']/../div/div/span")
    public WebElement BedroomsNumber;

    @FindBy(xpath = " //div[.='Bathrooms']/../div/div/span")
    public WebElement BathroomsNumber;
   
    @FindBy(xpath ="//div[.= 'Beds']/../div/div/button[@class='sc-fWWYYk sc-gzcbmu bZTTYU fKwyEY']")
    public WebElement BedsPlusButton;

    @FindBy(xpath = "//div[.= 'Beds']/../div/div/button[@class='sc-fWWYYk sc-fIxmyt bZTTYU cnkbFD']")
    public WebElement BedsMinusButton;

    @FindBy(xpath ="//div[.= 'Bedrooms']/../div/div/button[@class='sc-fWWYYk sc-gzcbmu bZTTYU fKwyEY']")
    public WebElement BedroomsPlusButton;

    @FindBy(xpath = "//div[.= 'Bedrooms']/../div/div/button[@class='sc-fWWYYk sc-fIxmyt bZTTYU cnkbFD']")
    public WebElement BedroomsMinusButton;

    @FindBy(xpath ="//div[.= 'Bathrooms']/../div/div/button[@class='sc-fWWYYk sc-gzcbmu bZTTYU fKwyEY']")
    public WebElement BathroomsPlusButton;

    @FindBy(xpath = "//div[.= 'Bathrooms']/../div/div/button[@class='sc-fWWYYk sc-fIxmyt bZTTYU cnkbFD']")
    public WebElement BathroomsMinusButton;



    //Price entries fields in the filters
    @FindBy(xpath = "//input[@placeholder= 'From']")
    public WebElement priceFrom;

    @FindBy(xpath = "//input[@placeholder= 'To']")
    public WebElement priceTo;

    @FindBy(xpath ="//div[@class='sc-jgPyTC bStXEc']//following-sibling::p")
    public WebElement warningMessageInPriceField;

    @FindBy(xpath = "//div[@class='sc-bXexck iKXphs']//preceding-sibling::div[.='Check-in']")
    public WebElement checkIn;

    @FindBy(xpath = "//div[@class='sc-bXexck iKXphs']//preceding-sibling::div[.='Check-out']")
    public WebElement checkOut;

    @FindBy(xpath = "//div[@class='sc-WZYut bJAXZb CalendarDay']")
    public List<WebElement>possibleCalendarDays;

    @FindBy(xpath = "//button[@class='sc-dWBRfb iWqacH']")
    public WebElement closeFilterPopUp;



    //when filters section pops up it needs to be scrolled down to reach out the amenities and the other stuff so that I located amenities
    //header to use it in my waitForVisibility method that is located in the BrowserUtil
    @FindBy(xpath = "//div[.='Amenities']")
    public WebElement amenitiesDiv;


    //these methods are used for finding the maximum and minimum amount of  beds,bedrooms,bathrooms  dynamically
    //I could be able to make it dynamic with the help of the repetitive nature of webElement structure within the DOM
    public int BedMaxValues() {
        int amountOfGivenEntry = 0;

        while (BedsPlusButton.isEnabled()) {
            BedsPlusButton.click();
            amountOfGivenEntry++;
        }

        return amountOfGivenEntry;
    }

    public int BedMinValues() {
        int minValue;
        while (BedsMinusButton.isEnabled()) {

            BedsMinusButton.click();

        }
        minValue=Integer.parseInt(BedsNumber.getText());

        return minValue;
    }

    public int BedroomMaxValues() {
        int amountOfGivenEntry = 0;

        while (BedroomsPlusButton.isEnabled()) {
            BedroomsPlusButton.click();
            amountOfGivenEntry++;
        }

        return amountOfGivenEntry;
    }

    public int BedroomMinValues() {

        int minValue;
        while (BedroomsMinusButton.isEnabled()) {

            BedroomsMinusButton.click();
        }


        minValue=Integer.parseInt(BedroomsNumber.getText());

        return minValue;
    }

    public int BathroomMaxValues() {
        int amountOfGivenEntry = 0;

        while (BathroomsPlusButton.isEnabled()) {
            BathroomsPlusButton.click();
            amountOfGivenEntry++;
        }

        return amountOfGivenEntry;
    }

    public int BathroomMinValues() {

        int minValue;
        while (BathroomsMinusButton.isEnabled()) {
            BathroomsMinusButton.click();
        }
        minValue=Integer.parseInt(BathroomsNumber.getText());

        return minValue;

    }



    //these methods are for checking each and every checkBoxes is enabled before clicking them one by one
    //if any of those checkboxes is not disabled then method gives warning that shows which checkbox is disabled
    public void  areCheckBoxesEnabled(List<WebElement> amenitiesCheckBoxes) {

        for (WebElement each : amenitiesCheckBoxes) {
            if (each.isEnabled() == false) {
                System.out.println(each.getText()+" is disabled");
            }
        }





    }

    //these methods are for checking each and every checkbox is functioning as it is expected by clicking  them one by one
    //if any of those checkboxes is not functioning as expected then method gives warning  shows which checkboxes are problematic
//    public void areCheckBoxesClickable(List<WebElement> amenitiesCheckBoxes,List<WebElement>inputCheckBoxes) {
//
//        int j = 0;
//        for (int i= 0;i<amenitiesCheckBoxes.size();i++,j++) {
//
//            if (inputCheckBoxes.get(j).getAttribute("checked").equals(null)) {
//
//                amenitiesCheckBoxes.get(i).click();
//
//                Assert.assertEquals(inputCheckBoxes.get(j).getAttribute("checked"), "true");
//                amenitiesCheckBoxes.get(i).click();
//                Assert.assertEquals(inputCheckBoxes.get(j).getAttribute("checked"), null);
//
//            } else if (amenitiesCheckBoxes.get(j).getAttribute("checked").equals("true")) {
//                amenitiesCheckBoxes.get(i).click();
//                Assert.assertEquals(inputCheckBoxes.get(j).getAttribute("checked"), null);
//                amenitiesCheckBoxes.get(i).click();
//                Assert.assertEquals(inputCheckBoxes.get(j).getAttribute("checked"), "true");
//
//            } else {
//
//                System.out.println(amenitiesCheckBoxes.get(i).getText() + " is not working properly");
//
//            }
//
//
//        }
//
//
//    }


    //these methods are for checking each and every checkbox is functioning as it is expected by clicking  them one by one
    //I prefer to use static sleep method in between clicking checkboxes so that user can see the checkBoxes clicked and removed  visually
    public void areCheckBoxesClickable(List<WebElement> amenitiesCheckBoxes,List<WebElement>inputCheckBoxes){

        int i= 0;

        while(i<amenitiesCheckBoxes.size()) {

            amenitiesCheckBoxes.get(i).click();
            BrowserUtil.sleep(1);
            Assert.assertEquals(inputCheckBoxes.get(i).getAttribute("checked"),"true");
            amenitiesCheckBoxes.get(i).click();
            BrowserUtil.sleep(1);
            Assert.assertEquals(inputCheckBoxes.get(i).getAttribute("checked"),null);
            i++;
        }






    }



}
