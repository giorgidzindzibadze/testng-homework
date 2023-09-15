import com.codeborne.selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class FactoryTest {
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
    @Factory
    public Object[] createTestInstances() {
        return new Object[]{
                new DataProviderTestInstance("giorgi", "dzindzibadze", "label[for='gender-radio-1']", "5991055551"),
                new DataProviderTestInstance("mariam", "dgebuadze", "label[for='gender-radio-2']", "5981063522"),
                new DataProviderTestInstance("maka", "carcidze", "label[for='gender-radio-3']", "5714053693")
        };
    }

    public class DataProviderTestInstance {
        private String firstName;
        private String lastName;
        private String gender;
        private String mobileNumber;

        public DataProviderTestInstance(String firstName, String lastName, String gender, String mobileNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.mobileNumber = mobileNumber;
        }

        @Test
        public void loginTest() {
            open("https://demoqa.com/automation-practice-form");

            $("#firstName").sendKeys(firstName);
            $("#lastName").sendKeys(lastName);
            $(gender).click();
            $("#userNumber").sendKeys(mobileNumber);
            executeJavaScript("window.scrollBy(0, 500);");
            executeJavaScript("document.querySelector('footer').remove();");
            executeJavaScript("document.querySelector('#fixedban').remove();");
            $("#submit").click();

            // validate
            SelenideElement table = $("table.table.table-dark.table-striped.table-bordered.table-hover");
            ElementsCollection tdElements = table.$$("td");
            SelenideElement name = tdElements.get(1);
            String studentName = name.getText();
            assert studentName.contains(firstName);
        }
    }
}
