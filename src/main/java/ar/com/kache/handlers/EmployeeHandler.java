package ar.com.kache.handlers;

import ar.com.kache.collectors.ListCollector;
import ar.com.kache.model.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * The Handler for SAX Events.
 */
public class EmployeeHandler extends FeedHandler {

    private Employee.EmployeeBuilder employeeBuilder = null;
    private String content = null;

    public EmployeeHandler() {
        super(new ListCollector<Employee>());
    }

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            //Create a new Employee object when the start tag is found
            case "employee":
                employeeBuilder = new Employee.EmployeeBuilder(attributes.getValue("id"));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch (qName) {
            //Add the employee to list once end tag is found
            case "employee":
                getCollector().add(employeeBuilder.build());
                break;
            //For all other end tags the employee has to be updated.
            case "firstName":
                employeeBuilder.firstName(content);
                break;
            case "lastName":
                employeeBuilder.lastName(content);
                break;
            case "location":
                employeeBuilder.location(content);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

}
