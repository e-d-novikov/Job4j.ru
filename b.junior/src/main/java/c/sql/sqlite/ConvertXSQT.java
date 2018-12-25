package c.sql.sqlite;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConvertXSQT {

    /*private String xsl = "<?xml version=\"1.0\"?>\n" +
            "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
            "<xsl:template match=\"/\">\n" +
            "<entries>" +
            "   <xsl:for-each select=\"user/values\">\n" +
            "       <entry>" +
            "           <xsl:attribute name=\"href\">" +
            "               <xsl:value-of select=\"value\"/>" +
            "           </xsl:attribute>" +
            "       </entry>\n" +
            "   </xsl:for-each>\n" +
            " </entries>\n" +
            "</xsl:template>\n" +
            "</xsl:stylesheet>\n";
    private File file;

    public ConvertXSQT(File target) {
        this.file = target;
    }*/

    public void convert(File source, File dest, File scheme) throws TransformerException, FileNotFoundException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream(scheme)));
        transformer.transform(new StreamSource(new FileInputStream(source)), new StreamResult(new FileOutputStream(dest)));
    }
}
