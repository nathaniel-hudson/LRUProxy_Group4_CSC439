import java.io.File;

/**
 * Created by Nathaniel on 10/20/16.
 */
public class TestHelperClass {

    /**
     * This method will be given a path and then replace its slashes relatively appropriate to whatever operating system
     * it is being run on.
     *
     * @param path
     * @return
     */
    public static String makeOSRelativePath(String path) {
        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = (os.indexOf("win") >= 0);

        if (isWindows)
            return path.replace("/", File.separator);
        else
            return path.replace("\\", File.separator);
    }

}
