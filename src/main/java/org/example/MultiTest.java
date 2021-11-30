package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MultiTest
{
    static WebDriver driver;
    // method for reusability purpose of clickable elements
    public static void clickOnElement(By by){
        driver.findElement(by).click();
    }
    // method for reusability purpose for typing text
    public static void typeText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
        public static String currentTimeStamp()
    {
        Date date =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
        return sdf.format(date);
    }
    //method for waiting time frame where needed
    public static void waitForClickable(By by,int timeInSeconds)
    {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));

    }
    public static void waitForVisible(By by,int timeInSeconds)
    {
        org.openqa.selenium.support.ui.WebDriverWait wait = new WebDriverWait(driver,timeInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    @AfterMethod
        public void closeBrowser()
    {
        driver.close();
    }
    @BeforeMethod
        public void openBrowser()
    {
        System.out.println(currentTimeStamp());
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //type URL
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void verifyUserShouldBeAbleToRegisterSuccessfully()
    {
        //click on Register button
        clickOnElement(By.linkText("Register"));
        waitForClickable(By.name("FirstName"),20);
        //type first name and last name with reusability
        typeText(By.name("FirstName"),"Minnie");
        typeText(By.name("LastName"),"Mouse");
        typeText(By.id("Email"),"xyzabc123+"+currentTimeStamp()+"@gmail.com");
        //type password
        typeText(By.id("Password"),"alpha123");
        //confirm password
        typeText(By.id("ConfirmPassword"),"alpha123");
        clickOnElement(By.id("register-button"));
        //print registration completed
        String actualRegisterSuccessMessage=getTextFromElement(By.xpath("//div[@class='result']"));
        String expectedRegisterSuccessMessage="Your registration completed";
        //verifying the actual requirement and check if test case is pass or fail
        Assert.assertTrue(actualRegisterSuccessMessage.equals(expectedRegisterSuccessMessage),"Your registration completed");
    }
    @Test
    public void verifyUserShouldBeAbleToAddNewCommentOnNewsSuccessfully()
    {
        //click on News
        clickOnElement(By.linkText("News"));
        //click on details
        clickOnElement(By.linkText("DETAILS"));
        //type title under new comment
        typeText(By.id("AddNewComment_CommentTitle"),"New");
        //type comment
        typeText(By.id("AddNewComment_CommentText"),"Comment");
        //click on New comment
        clickOnElement(By.xpath("//div[@class='buttons']/button"));
        String actualResult = getTextFromElement(By.xpath("//div[@class='result']"));
        String expectedResult = "News comment is successfully added.";
        //verifying the actual requirement and check if test case is pass or fail
        Assert.assertTrue(actualResult.equals(expectedResult),"News comment is successfully added.");
    }
    @Test
    public void verifyUserShouldBeAbleToNavigateToDesktopPageSuccessfully()
    {
        //click on Computers Category
        clickOnElement(By.linkText("Computers"));
        //click on Desktop
        waitForClickable(By.linkText("Desktops"),20);
        clickOnElement(By.linkText("Desktops"));
        //checking for keyword desktops as per requirement
       Assert.assertTrue(driver.getCurrentUrl().contains("desktops"));
    }
    @Test
    public void verifyUserShouldBeAbleToReferAProductToFriend()
    {
        //user login to access the facility to refer a product to friend
        clickOnElement(By.linkText("Register"));
       waitForClickable(By.name("FirstName"),20);
        //type first name and last name with reusability
       typeText(By.name("FirstName"),"Minnie");
       typeText(By.name("LastName"),"Mouse");
        typeText(By.id("Email"),"xyzabc123+"+currentTimeStamp()+"@gmail.com");
        //type password
        typeText(By.id("Password"),"alpha123");
        //confirm password
       typeText(By.id("ConfirmPassword"),"alpha123");
       clickOnElement(By.id("register-button"));
        //click on Computers
        clickOnElement(By.linkText("Computers"));
        //click on Desktops
        clickOnElement(By.linkText("Desktops"));
        //click on Build your on computer
        clickOnElement(By.linkText("Build your own computer"));
        //select refer a friend
        clickOnElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]"));
        //enter friend's email address
        typeText(By.id("FriendEmail"),"tomnjerry1+"+currentTimeStamp()+"@gmail.com");
        //type personalised message
        typeText(By.id("PersonalMessage"),"This product is worth having a look, I hope you will like it.");
        //click on send email
        clickOnElement(By.xpath("//div[@class='buttons']/button"));
        String actualResult = getTextFromElement(By.xpath("//div[@class='result']"));
        String expResult = "Your message has been sent.";
        //verify the expected and actual result
        Assert.assertTrue(actualResult.equals(expResult),"Your message has been sent.");

    }

}
























    
























