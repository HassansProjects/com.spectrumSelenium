package Steps;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {

    // precondition opening a browser
    @Before
    public void PreCon(){
        openBrowserAndLaunchApplication();
    }




    // post condition for screenshot folders(failed & passed) and closing browser
    @After
    public void PostCon(Scenario scenario){
        byte[]pic;
        if(scenario.isFailed()){
            pic=takeScreenshot("Failed/"+scenario.getName());
        }else{
            pic=takeScreenshot("Passed/"+scenario.getName());
        }
        scenario.attach(pic,"image/png", scenario.getName());

         closeBrowser();
    }
}
