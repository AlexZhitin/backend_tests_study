package Log4j_packageA;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Demo {

    private static Logger log = LogManager.getLogger(Demo.class.getName());

    @Test

    public void Test() {

        if (2 > 0) {
            log.info("I am debugging");
            log.error("Error");
            log.fatal("Fatal");
        }


    }
}
