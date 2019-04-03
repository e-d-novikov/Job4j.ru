package c.sql.sqlite;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Hashtable;
/**
 * Class SAXLocalNameCount.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class SAXLocalNameCount extends DefaultHandler {

    private static Hashtable tags;

    public SAXLocalNameCount()  {

    }

    public BigInteger parse(File file) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new SAXLocalNameCount());
        xmlReader.parse(String.valueOf(file));
        xmlReader.setErrorHandler(new MyErrorHandler(System.err));
        BigInteger n = BigInteger.valueOf((Integer) tags.get("entry"));
        return ((n.add(new BigInteger("1"))).multiply(n).divide(new BigInteger("2")));
    }

    public void startDocument() {
        tags = new Hashtable();
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        String key = localName;
        Object value = tags.get(key);
        if (value == null) {
            tags.put(key, new Integer(1));
        } else {
            int count = ((Integer) value).intValue();
            count++;
            tags.put(key, new Integer(count));
        }
    }

    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    private static class MyErrorHandler implements ErrorHandler {
        private PrintStream out;

        MyErrorHandler(PrintStream out) {
            this.out = out;
        }

        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();

            if (systemId == null) {
                systemId = "null";
            }

            String info = "URI=" + systemId + " Line="
                    + spe.getLineNumber() + ": " + spe.getMessage();

            return info;
        }

        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }

        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }


}
