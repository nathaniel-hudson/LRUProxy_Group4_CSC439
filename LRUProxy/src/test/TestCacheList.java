import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the CacheList.java class with parameterized JUnit testing.
 */
public class TestCacheList {

    private static LRUCacheList list;
    private static CacheList emptylist;
    private static int size = 5;

    /**
     * Initializes the CacheLists and adds things to the non-empty one
     */
    @BeforeClass
    public static void beforeClass() {
        list = new LRUCacheList("./", size);
        list.addNewObject("www.google.com", false);
        list.addNewObject("www.yahoo.com", false);
        list.addNewObject("www.microsoft.com", false);
        list.addNewObject("www.apple.com", false);
        list.addNewObject("www.reddit.com", false);
        list.addNewObject("www.trello.com", false);

        emptylist = new LRUCacheList("./", 1);
    }

    /**
     * Tests adding a new object to empty list using addNewObject method of CacheList class.
     * This should return an empty string.
     */
    @Test
    public void addNewObjectTestMiss(){
        System.out.println("Testing if add miss works");
        assertEquals("", emptylist.addNewObject("www.yahoo.com", false));
    }

    /**
     * Tests adding a new object to a full list using addNewObject method of CacheList class.
     * This should return a string of the object that was removed ("www.yahoo.com" in this case).
     */
    @Test
    public void addNewObjectRemoval(){
        System.out.println("Testing if adding with removal works");
        assertEquals("www.yahoo.com", emptylist.addNewObject("www.google.com", false));
    }

    /**
     * Tests getting the Cache size.
     */
    @Test
    public void getCacheSizeTest(){
        System.out.println("Testing if getting size works");
        assertEquals(size, list.getCacheSize());
    }

    /**
     * Tests getting the head of the CacheList.
     */
    @Test
    public void getHeadTest(){
        System.out.println("Testing if get head works");
        assertEquals("www.google.com", emptylist.getHead());
    }

    /**
     * Tests the get function of the CacheList.
     */
    @Test
    public void getTest(){
        System.out.println("Testing if get works");
        assertEquals("www.microsoft.com", list.get(3));
    }
}
