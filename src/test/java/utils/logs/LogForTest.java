package utils.logs;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.util.ArrayList;

import static base.tests.BasicTest.testStepCount;

public class LogForTest {

    public static final Logger LOGGER = Logger.getLogger(LogForTest.class);
    private static final String INFO_LOG = "INFO: %s";
    private static ArrayList<String> infoLog;

    public static void resetLogLists() {
        infoLog = new ArrayList<>();
    }

    public static String info(String message) {
        LOGGER.info(String.format(INFO_LOG, testStepCount + ") " + message));
        Reporter.log(String.format(INFO_LOG, testStepCount + ") " + message));
        infoLog.add(testStepCount + ") " + message + "\n");
        testStepCount++;
        return String.format(INFO_LOG, testStepCount + ") " + message);
    }
}
