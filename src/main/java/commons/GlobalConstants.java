package commons;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Define all constant variables which will be used in project context
 * through getters methods (Singleton Pattern applying)
 */
@Getter
@Setter
public class GlobalConstants {

    private static GlobalConstants globalConstants;

    public static synchronized GlobalConstants getGlobalConstants() {
        if (globalConstants == null) {
            return new GlobalConstants();
        }
        return globalConstants;
    }

    private GlobalConstants() {

    }

    private final int shortTimeout = 10;
    private final int longTimeout = 20;
    private final String projectPath = System.getProperty("user.dir");
    private final String reportNGScreenshot = projectPath + File.separator + "reportNGScreenshot";

}
