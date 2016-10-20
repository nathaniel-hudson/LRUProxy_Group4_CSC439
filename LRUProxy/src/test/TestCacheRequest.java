import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Nathaniel on 10/6/16.
 */
@RunWith(value = Parameterized.class)
public class TestCacheRequest {

    /**
     * Class variables.
     */
    private static CacheRequest cacheRequest;
    private static String       directory;
    private String              expected;

    private final static String TEST_DIRECTORY = "./LRUProxy/src/test/test_data/";

    /**
     * Set up the directory and the CacheRequest object required to properly test the class. We don't want to
     * continually create a new CacheRequest object (which would only let us read in the first line each time we test it
     * using parameterized methods). So, we will use the @BeforeClass marker, in lieu of the @Before marker.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {

        // Determine which way slashes go for directories.
        String os = System.getProperty("os.name").toLowerCase();
        boolean windows = (os.indexOf("win") >= 0);

        // Set the directory.
        directory = TestHelperClass.makeOSRelativePath(TEST_DIRECTORY);
        if (windows && !directory.endsWith("\\")) {
            directory = directory + "\\";
        }
        if (!windows && !directory.endsWith("/")) {
            directory = directory + "/";
        }

        // Write the string from input to some crazy dummy output file.
        cacheRequest = new CacheRequest(directory);
    }

    /**
     * Set the passed in expected value to the class value, this will be used parameterized testing.
     */
    public TestCacheRequest(String expected) {
        this.expected = expected;
    }

    /**
     * This method simply provides the values needed for testing.
     *
     * @return set of expected values required for parameterized testing.
     */
    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters() {
        return Arrays.asList(new String[][] {
                { "This is an example..." },
                { "of input you might receive..." },
                { "from an input file..." },
                { "use this to test..." }
        });
    }

    /**
     * Method uses parameterized methods in order to test the read method of the CacheRequest class.
     *
     * @throws Exception
     */
    @Test
    public void read() throws Exception {
        System.out.println("Directory: " + directory);
        System.out.println("Expected: \"" + expected + "\"");
        assertEquals(expected, cacheRequest.read());
    }

}