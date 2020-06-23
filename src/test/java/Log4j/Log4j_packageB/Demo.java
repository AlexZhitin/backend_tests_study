package Log4j.Log4j_packageB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Demo {

    /*
    Had to add resourcesLog4j2 to build path (add a new content root). Steps are the following:

    To add a new content root:

    Go to File | Project Structure, or press Ctrl+Shift+Alt+S.
    Select Modules under the Project Settings section.
    Select the necessary module, and then open the Sources tab in the right-hand part of the dialog.
    Click Add Content Root.
    Specify the folder that you want to add as a new content root, and click OK.*/

    @Test

    public void Test() {
        System.setProperty("log4j.configurationFile","D:\\QA\\Selenium_projects\\backend_tests_study\\src\\main\\resources\\log4j2.xml");
        Logger log = LogManager.getLogger(Log4j.Log4j_packageB.Demo.class.getName());
        if (2 > 0) {
            log.debug("I am debugging");
            log.info("I am info");
            log.error("Error");
            log.fatal("Fatal");
        }
    }
}
