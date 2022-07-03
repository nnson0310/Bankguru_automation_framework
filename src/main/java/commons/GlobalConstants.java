package commons;

import envfactory.CloudCredentials;
import lombok.Getter;
import lombok.Setter;
import org.aeonbits.owner.ConfigFactory;

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
    private final CloudCredentials cloudCredentials = ConfigFactory.create(CloudCredentials.class);;

    private GlobalConstants() {
    }

    public synchronized static GlobalConstants getGlobalConstants() {
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
    private final String cloudUsername = cloudCredentials.cloudUsername();
    private final String cloudPassword = cloudCredentials.cloudPassword();
    private final String cloudUrl = "https://" + cloudUsername + ":" + cloudPassword + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";

}
