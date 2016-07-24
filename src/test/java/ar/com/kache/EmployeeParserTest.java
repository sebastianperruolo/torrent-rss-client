package ar.com.kache;

import ar.com.kache.collectors.ListCollector;
import ar.com.kache.handlers.EmployeeHandler;
import org.junit.Test;

/**
 * Created by sperruolo on 7/24/16.
 */
public class EmployeeParserTest {

    @Test
    public void testParse() throws Exception {
        EmployeeHandler handler = new EmployeeHandler();
        // some test logic
        FeedSAXParser.parse(ClassLoader.getSystemResourceAsStream("xml/employee.xml"), handler);

        //Printing the list of employees obtained from XML
        ((ListCollector)handler.getCollector()).getList().stream().forEach(o -> System.out.println(o));
    }
}
