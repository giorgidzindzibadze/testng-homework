import com.codeborne.selenide.*;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class DataProviderTest{
    @BeforeSuite
    public  void Suite(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        timeout = 10000; // default is 4000, change N1
        holdBrowserOpen = false;
    }
    @BeforeTest
    public void SelenideTests() {
        screenshots = true; //change2
        reopenBrowserOnFail = true;
        downloadsFolder = "src/main/resources/Pictures";
        fastSetValue = true;
        assertionMode = AssertionMode.SOFT;
        fileDownload = FileDownloadMode.HTTPGET;
        downloadsFolder = "src/main/resources/images";}

    @DataProvider (name = "data")
    public Object[][] getInformation(){

        Object [][] dates={{"giorgi","dzindzibadze","label[for='gender-radio-1']","5991055551"},
                {"mariam", "dgebuadze", "label[for='gender-radio-2']", "5981063522"},
                {"maka", "carcidze", "label[for='gender-radio-3']", "5714053693"}


        };
        return dates;
    }

    @Test(dataProvider = "data")
    public void loginTest(String firstName, String lastName, String gender, String mobileNumber){


        open("https://demoqa.com/automation-practice-form");

        $("#firstName").sendKeys(firstName);
        $("#lastName").sendKeys((lastName));
        $(gender).click();
        $("#userNumber").sendKeys(mobileNumber);
        executeJavaScript("window.scrollBy(0, 500);");
        executeJavaScript("document.querySelector('footer').remove();");
        executeJavaScript("document.querySelector('#fixedban').remove();");
        $("#submit").click();

//                          validate
        SelenideElement table = $("table.table.table-dark.table-striped.table-bordered.table-hover");
        ElementsCollection tdElements = table.$$("td");
        SelenideElement name = tdElements.get(1);
        String studentName = name.getText();
        assert studentName.contains(firstName);



    }
}
