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

    private static CacheList list;
    private static CacheList emptylist;
    private static int size = 5;

    @BeforeClass
    public static void beforeClass() {
        list = new CacheList("./", size);
        list.addNewObject("www.google.com", false);
        list.addNewObject("www.yahoo.com", false);
        list.addNewObject("www.microsoft.com", false);
        list.addNewObject("www.apple.com", false);
        list.addNewObject("www.reddit.com", false);
        list.addNewObject("www.trello.com", false);

        emptylist = new CacheList("./", 1);
    }

    @Test
    public void addNewObjectTestMiss(){
        System.out.println("Testing if add miss works");
        assertEquals("", emptylist.addNewObject("www.yahoo.com", false));
    }

    @Test
    public void addNewObjectRemoval(){
        System.out.println("Testing if adding with removal works");
        assertEquals("www.yahoo.com", emptylist.addNewObject("www.google.com", false));
    }

    @Test
    public void getCacheSizeTest(){
        System.out.println("Testing if getting size works");
        assertEquals(size, list.getCacheSize());
    }

    @Test
    public void getHeadTest(){
        System.out.println("Testing if get head works");
        assertEquals("www.google.com", emptylist.getHead());
    }

    @Test
    public void getTest(){
        System.out.println("Testing if get works");
        assertEquals("www.microsoft.com", list.get(3));
    }
}
