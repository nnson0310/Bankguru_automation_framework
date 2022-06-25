package commons;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Define all constant variables which will be used in project context
 * through getters methods (Singleton Pattern applying)
 * @author Son
 */
@Getter
@Setter
public class GlobalConstants {

    private static GlobalConstants globalConstants;

    private GlobalConstants() {

    }

    public static synchronized GlobalConstants getGlobalConstants() {
        if (globalConstants == null) {
            return new GlobalConstants();
        }
        return globalConstants;
    }

    private final int shortTimeout = 10;
    private final int longTimeout = 20;
    private final String projectPath = System.getProperty("user.dir");
    private final String osName = System.getProperty("os.name");
    private final String pathToMainResources = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" +  File.separator;
    private final String reportNGScreenshot =  pathToMainResources + "reportNGScreenshot" + File.separator;
    private final String downloadFilePath = pathToMainResources + "downloadFiles";
    private final String uploadFilePath = pathToMainResources + "uploadFiles";
    private final String dataTestPath = pathToMainResources + "testData";
    private final String browserLogFilePath = projectPath + File.separator + "browserLogs" + File.separator;
    private final String browserExtensionPath = projectPath + File.separator + "browserExtensions" + File.separator;

    // Cloud testing info (saucelabs)
    private final String cloudUsername = "oauth-tomanyeuem123-029fd";
    private final String cloudPassword = "f9cddf50-6249-40da-b489-c5ca465ab9c8";
    private final String cloudUrl = "https://" + cloudUsername + ":" + cloudPassword + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";

}
