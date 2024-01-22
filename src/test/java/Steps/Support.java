package Steps;

import Pages.SupportPage;
import Utils.CommonMethods;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class Support extends CommonMethods {


    @Given("user launched the browser and navigate to the URL")
    public void user_launched_the_browser_and_navigate_to_the_url() {
    openBrowserAndLaunchApplication();
    }

    @Given("user should scroll down to the page footer")
    public void user_should_scroll_down_to_the_page_footer() {
    //    sleep(3000);
    jsScrollToElement(supportPage.SupportBtn);
    }


    @When("user should Click on Support link under Help and Support category")
    public void user_should_click_on_support_link_under_help_and_support_category() {
    doClick(supportPage.SupportBtn);
    }


    @When("user should type {string} in the search bar")
    public void user_should_type_in_the_search_bar(String SearchedTopic){
    sendText(supportPage.InsideSearchBar,SearchedTopic);
    }


    @When("user should Click on the magnify to start the search")
    public void user_should_click_on_the_magnify_to_start_the_search() {
    doClick(supportPage.MagnifierIcon);
    }

    @Then("user should click on the link “ How to Protect Your Personal Information from Hackers and Online Threats”")
    public void user_should_click_on_the_link_how_to_protect_your_personal_information_from_hackers_and_online_threats() {
    doClick(supportPage.FirstResult);
    }


    @Then("user click on Related Articles link")
    public void user_click_on_related_articles_link_a_contains_text() {
    doClick(supportPage.RelatedArticlesLink);
    }


    @Then("user click on Online Security link")
    public void user_click_on_online_security_link_h3_contains_text() {
    doClick(supportPage.OnlineSecurityLink);
    }

    @Then("user should see page title on the top of the page {string}")
    public void user_should_see_page_title_on_the_top_of_the_page(String expectedText) {
        WebElement OnlineSecurityTitle = supportPage.VerifyPageTitleOnlineSecurity;
        System.out.println("Expected Page Title:" + " " + expectedText);
        String actualText = OnlineSecurityTitle.getText();
        System.out.println("Actual Page Title:" +" " + actualText);
        Assert.assertEquals(expectedText,actualText);

    }


   @Then("user Verify all links of the opened page are working and that there is no broken links")
    public void user_verify_all_links_of_the_opened_page_are_working_and_that_there_is_no_broken_links() {
    findAndVerifyBrokenLinks();


   }


}
