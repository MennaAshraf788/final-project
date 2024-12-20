package Tests;
import Pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterTest extends TestBase {

    RegisterPage registerPage;  // Declare RegisterPage globally


    @BeforeMethod
    public void Preconditions() {
        // Initialize the RegisterPage object
        registerPage = new RegisterPage(driver);
        // Set the path to the chromedriver if required
        driver.navigate().to("https://awesomeqa.com/ui/index.php?route=account/register");  // Open registration page
    }

    @Test
    public void testValidRegistration() {

              // Fill in registration details
        registerPage.enterFirstName("Doaa");
        registerPage.enterLastName("Fahmy");
        registerPage.enterEmail("mira0576@abaot.com");
        registerPage.enterPhone("01010022783");
        registerPage.enterPassword("Asd@101094");
        registerPage.confirmPassword("Asd@101094");
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();

        // Assert registration success by checking if the URL changes to the expected success page
        String expectedUrl = "https://awesomeqa.com/ui/index.php?route=account/success";


    }

    @Test

    public void testRegistrationWithExistingEmail() {
        // Fill in registration details with an existing email
        registerPage.enterFirstName("Doaa");
        registerPage.enterLastName("Fahmy");
        registerPage.enterEmail("mired40576@abaot.com");
        registerPage.enterPhone("01010022783");
        registerPage.enterPassword("Asd@101094");
        registerPage.confirmPassword("Asd@101094");
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();
        //String expectedErrorMessage = "Your Account Has Been Created!";
        WebElement actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        // Assert that an error message is displayed for the existing email
        // Note: You need to locate the specific error message element to assert it
        Assert.assertTrue(actualErrorMessage.getText().contains("\"Your Account Has Been Created\""),"Success");
    }
    @Test
    public void testRegistrationWithExistingEmail1() {
        // Leave all fields blank and click continue
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();
    }
    @Test
    public void testRegistrationWithExistingEmail2() {
        // Leave Email field blank and click continue
        registerPage.enterFirstName("Doaa");
        registerPage.enterLastName("Fahmy");
        registerPage.enterEmail("");
        registerPage.enterPhone("01010022783");
        registerPage.enterPassword("Asd@101094");
        registerPage.confirmPassword("Asd@101094");
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();
        String expectedErrorMessage = "E-Mail Address does not appear to be valid!";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"account\"]/div[4]/div/div")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "E-Mail Address does not appear to be valid!");
}
    @Test
    public void testRegistrationWithExistingEmail3() {
        // Fill in registration details with a weak password
        registerPage.enterFirstName("Doaa");
        registerPage.enterLastName("Fahmy");
        registerPage.enterEmail("xoten98324@abaot.com");
        registerPage.enterPhone("01010022783");
        registerPage.enterPassword("1");
        registerPage.confirmPassword("1");
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();
        // Assert that an error message is displayed for the weak password
        String expectedErrorMessage = "Password must be between 4 and 20 characters!";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[2]/div[1]/div/div")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Password must be between 4 and 20 characters!");
    }
    @Test
    public void testRegistrationWithExistingEmail4() {
        // Fill in registration details with a strong password
        registerPage.enterFirstName("Doaa");
        registerPage.enterLastName("Fahmy");
        registerPage.enterEmail("xoten98324@abaot.com");
        registerPage.enterPhone("01010022783");
        registerPage.enterPassword("123456123");
        registerPage.confirmPassword("123456123");
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();
// Assert registration success by checking if the URL changes to the expected success page
        String expectedUrl = "https://awesomeqa.com/ui/index.php?route=account/success";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Registration successful with a strong password");
    }
    @Test
    public void testRegistrationWithExistingEmail5() {
        // Fill in registration details with an invalid email format
        registerPage.enterFirstName("Doaa");
        registerPage.enterLastName("Fahmy");
        registerPage.enterEmail("bosote9640advitize.com");
        registerPage.enterPhone("01010022783");
        registerPage.enterPassword("123456123");
        registerPage.confirmPassword("123456123");
        registerPage.agreeToPrivacyPolicy();
        registerPage.clickContinue();
// Assert that an error message is displayed for the invalid email format
        String expectedErrorMessage = "Warning: E-Mail Address does not appear to be valid!";
    }

}

