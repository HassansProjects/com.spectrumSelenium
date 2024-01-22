package Utils;

import org.apache.log4j.Logger;

public class Log {

    public static Logger log=Logger.getLogger(Log.class.getName());
    public static void startTestCase(String TestCaseName){
        log.info("###############################################");
        log.info("#############"+TestCaseName+"####################");

    }
    public static void endTestCase(String TestCaseName){
        log.info("###############################################");
        log.info("#############"+TestCaseName+"####################");
    }
    public static void info(String message){
        log.info(message);
    }
    public static void warning(String warning){
        log.info(warning);
    }
}
