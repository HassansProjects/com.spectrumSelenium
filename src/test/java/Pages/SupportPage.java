package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupportPage extends CommonMethods {

    public SupportPage()
    {
        //call selenium page factory
        PageFactory.initElements(driver,this);
        //Initializing the webElements of the current page
    }

    @FindBy(xpath = "//a[normalize-space()='Support']")
    public WebElement SupportBtn;

    @FindBy(xpath = " //input[@aria-label='Enter your search term, or article']")
    public WebElement InsideSearchBar;

    @FindBy (xpath = " //kite-icon[@aria-label='View all results']//span[@class='kite-icon__inner ng-star-inserted']//*[name()='svg']")
    public WebElement MagnifierIcon;

    @FindBy(xpath = "//a[contains(text(),'How to Protect Your Personal Information from Hack')]")
    public WebElement FirstResult;

    @FindBy(xpath = "//a[contains(text(),'Related Articles')]")
    public WebElement RelatedArticlesLink;

    @FindBy(xpath = "//h3[contains(text(),'Online Security')]")
    public WebElement OnlineSecurityLink;

    @FindBy(xpath = "//h1[contains(text(),'Online Security')]")
    public WebElement VerifyPageTitleOnlineSecurity;

}
