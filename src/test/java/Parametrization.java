import com.codeborne.selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Parametrization{
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
//        baseUrl = "http://the-internet.herokuapp.com";
        reopenBrowserOnFail = true;
        downloadsFolder = "src/main/resources/Pictures";
        fastSetValue = true;
        assertionMode = AssertionMode.SOFT;
        fileDownload = FileDownloadMode.HTTPGET;
        downloadsFolder = "src/main/resources/images";}
    @Test
    @Parameters({"firstName", "lastName", "gender", "mobileNumber"})
    public void fillRegistrationForm(String firstName,String lastName,String gender,String mobileNumber) {
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
