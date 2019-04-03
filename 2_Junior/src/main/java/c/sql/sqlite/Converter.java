package c.sql.sqlite;

import c.sql.sqlite.objects.Config;
import c.sql.sqlite.objects.User;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.sql.SQLException;
/**
 * Класс Converter .
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class Converter {

    private static StoreSQL storeSQL;
    private static StoreXML storeXML;
    private static ConvertXSQT convert;
    private static SAXLocalNameCount sax;

    public static BigInteger convert(File first, File second, File scheme)  throws Exception, SQLException, ClassNotFoundException, JAXBException, TransformerException, FileNotFoundException {
        storeSQL = new StoreSQL(new Config("2_Junior/src/main/java/c/sql/sqlite/db/fields.db"), 100);
        User user = storeSQL.getData();
        storeSQL.closeConnection();
        storeXML = new StoreXML(first);
        storeXML.save(user);
        convert = new ConvertXSQT();
        convert.convert(first, second, scheme);
        sax = new SAXLocalNameCount();
        return sax.parse(second);
    }
}
