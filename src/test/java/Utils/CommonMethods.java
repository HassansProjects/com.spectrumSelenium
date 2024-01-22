package Utils;

import Steps.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer {
        public static WebDriver driver;


    public static void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.PROPERTY_FILE_PATH);
        String browserType = ConfigReader.getPropertyValue("browserType");
        switch (browserType) {
            case "Chrome":



                ChromeOptions cp=new ChromeOptions();
                cp.setHeadless(true);

                driver = new ChromeDriver();  // (cp) needed for Jenkins job
                break;



            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Internet Explorer":
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));
        initializePageObjects();
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my Test Case");
        Log.info("My test case is executing right now");
        Log.warning("My test case might have some issues");
    }



    public static void closeBrowser() {
        Log.info("This test case is about to get completed");
        Log.endTestCase("This  Test Case is finished");
        driver.close();
    }
    //------------------- CLICK METHOD ---------------------------------------------------

    public static void doClick(WebElement element) {
        element.click();
    }

    //--------------------- CLEAR TEXT BOX AND SEND TEXT METHOD ----------------------
    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
    public static void clearSentTextForRequiredTextBox(WebElement element,String text){
        element.sendKeys(text);
        element.clear();
    }

    //-------------------- DROP DOWN & SELECT TEXT BY VALUE METHOD -----------------------------------------
    public static Select clickDropDown(WebElement element) {
        Select select = new Select(element);
        return select;
    }
    //Select by Value
    public static void selectByValue(WebElement element,String value){

        clickDropDown(element).selectByValue(value);
    }

    // select by visible text method
    public static void selectByVisibleText(WebElement element, String visibleText) {
        clickDropDown(element).selectByVisibleText(visibleText);
    }
    //-------------------------- SCREENSHOT METHOD --------------------------------------

    public static byte[] takeScreenshot(String picName) {

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] picBytes = screenshot.getScreenshotAs(OutputType.BYTES);
        File sourcePath = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourcePath, new File(Constants.SCREENSHOT_FILE_PATH + picName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return picBytes;
    }

    //-------------------------- TIME STAMP FOR LOG -----------------------------------------------
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
    //---------------------------HowerOver Method--------------------
    public static void howerOver (WebElement mainMenu ){
        Actions actions=new Actions(driver);
        actions.moveToElement(mainMenu).perform();
        doClick(mainMenu);
    }
    //******************************* move mouse vertically *****************
    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    public static void jsScroll300() {
        getJSExecutor().executeScript("window.scrollBy(0,300)");
    }

    public static void jsScroll500() {
        getJSExecutor().executeScript("window.scrollBy(0,500)");
    }

    public static void jsScroll1000() {
        getJSExecutor().executeScript("window.scrollBy(0,1200)");
    }

    public static void jsScrollToElement(WebElement SupportBtn){
        getJSExecutor().executeScript("arguments[0].scrollIntoView(true);",SupportBtn);

    }


    //***************************************** Thread.sleep  ********************************

    public static void sleep(long milliseconds) {
        ;
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

//******************************************  FindBrokenLinksMethod  *****************************

    public static void findAndVerifyBrokenLinks() {

        List<WebElement> links = driver.findElements(By.tagName("a"));
        int totalLinks = links.size();


        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                verifyLinkStatus(url);
            }
        }
        System.out.println("Total Links Found: " + totalLinks);

    }

    // Method to verify the HTTP response code of a link
    private static void verifyLinkStatus(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set timeout as needed

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 400) {
                System.out.println("Broken Link Found: " + url + " - Response Code: " + responseCode);
            } else {
                System.out.println("Link is OK: " + url);
            }
        } catch (Exception e) {
            System.out.println("Exception while checking the link: " + e.getMessage());
        }

    }

//*********************************************************************

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }


    //----------------------------getWait--------------------------------------------------

    // public static WebDriverWait getWait() {
    //  WebDriverWait wait = new WebDriverWait(driver,Constants.EXPLICIT_WAIT);
    //  return wait;
    //}

//*****************************

    // public static void waitForClickability(WebElement element) {
    //     getWait().until(ExpectedConditions.elementToBeClickable(element));
    // }

    /**
     * this method makes click on webElement
     *
     * @param element - webElement which we need to click
     */
    // public static void click(WebElement element) {
    //      waitForClickability(element);
    //     element.click();
    // }


    //--------------------------Calender  method -------------------
    //This method takes 4 parameters
    //1st WebElement this is to activate your Calendar! Store a locator for the caldera you want activate by clicking.
    //2nd Enter the year!
    //3rd month  in this format>> "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    //4th Enter a day between 1-31.
    /*public void selectDateFromCalendar(WebElement activateCalender,String year, String month, String day) {
        doClick(activateCalender);
        selectByValue(employeeWorkExperiencePage.selectYear, year);//Select year
        selectByVisibleText(employeeWorkExperiencePage.selectMonth, month);//Select month
        String daysXpath = Constants.ENTER + day +Constants.BETWEEN_1_TO_30 ; //Enter the day you want to click.
        WebElement dayXpath = driver.findElement(By.xpath(daysXpath));
        dayXpath.click();
    }

     */
}
