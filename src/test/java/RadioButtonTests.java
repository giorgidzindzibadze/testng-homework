import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RadioButtonTests extends ConfigTests {

    @BeforeMethod
    public void config() {
        // Start with a new WebDriver instance
        reportsFolder = "src/main/resources/RadioButtonFailedTests";//change N2
        WebDriverManager.chromedriver().setup();
//        baseUrl = "https://demoqa.com/";
    }
    @Test(priority = 1,retryAnalyzer = CustomRetryAnalyzer.class,groups = {"FrontEnd"})
    public void testSelectYesOption(){
//        open("https://demoqa.com/radio-button");
        open("https://demoqa.com/radio-button");

        SelenideElement yes = $("label[for='yesRadio']");
        yes.click();

        // Invoke failed TestNG soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.fail("This test should fail.");
        softAssert.assertAll();
    }
    @Test(priority = 2,groups = {"BackEnd"}, retryAnalyzer = CustomRetryAnalyzer.class)
    public void testNoOptionAvailability() {
//        open("radio-button");
        open("https://demoqa.com/radio-button");
        SelenideElement no = $(By.id("noRadio"));
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(no.isEnabled(),"'No' shuuld be unavailable");


        softAssert.fail("This test should fail.");
//        softAssert.assertAll();
    }
    @AfterMethod
    public void AfterMethod(){
        Selenide.closeWebDriver();
    }
}
