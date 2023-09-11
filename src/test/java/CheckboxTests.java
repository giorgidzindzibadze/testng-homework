import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;


public class CheckboxTests extends ConfigTests {


    @BeforeMethod
    public void config() {
        // Start with a new WebDriver instance
        WebDriverManager.chromedriver().setup();
        reportsFolder = "src/main/resources/CheckboxFailedTests";
        baseUrl = "http://the-internet.herokuapp.com/";
    }

    @Test
    public void testUncheckCheckbox() {
        open("/checkboxes");

        ElementsCollection checkedCheckboxes = $$("input:checked[type='checkbox']");
        checkedCheckboxes.stream().forEach(book -> {book.click();});

        SoftAssert softAssert = new SoftAssert();
        softAssert.fail("This test should fail.");


//        softAssert.assertAll();
    }

    @Test
    public void testCheckCheckbox() {
        open("/checkboxes");

        ElementsCollection uncheckedCheckboxes = $$("input:not(:checked)[type='checkbox']");
        uncheckedCheckboxes.stream().forEach(book ->{book.click();});

        // Invoke failed TestNG soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.fail("This test should fail.");
        softAssert.assertAll();
    }
    @AfterMethod
    public void AfterMethod(){
        Selenide.closeWebDriver();
    }
}

