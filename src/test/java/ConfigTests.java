import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.downloadsFolder;

public class ConfigTests {
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

}
