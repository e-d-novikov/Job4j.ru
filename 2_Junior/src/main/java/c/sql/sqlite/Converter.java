package c.sql.sqlite;

import c.sql.sqlite.objects.Config;
import c.sql.sqlite.objects.User;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
/**
 * Класс Converter .
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class Converter {

    private static StoreSQL storeSQL;
    private static StoreXML storeXML;
    private static ConvertXSQT convert;
    private static SAXLocalNameCount sax;
    private static File first = new File("b.junior/src/main/java/c/sql/sqlite/xml/XML.xml");
    private static File second = new File("b.junior/src/main/java/c/sql/sqlite/xml/XSQT.xml");
    private static File scheme = new File("b.junior/src/main/java/c/sql/sqlite/xml/Scheme.xml");

    public static void main(String[] args) throws Exception, SQLException, ClassNotFoundException, JAXBException, TransformerException, FileNotFoundException {
        storeSQL = new StoreSQL(new Config("b.junior/src/main/java/c/sql/sqlite/db/fields.db"), 1000000);
        User user = storeSQL.getData();
        storeSQL.closeConnection();
        storeXML = new StoreXML(first);
        storeXML.save(user);
        convert = new ConvertXSQT();
        convert.convert(first, second, scheme);
        sax = new SAXLocalNameCount();
        sax.parse();
    }
}
