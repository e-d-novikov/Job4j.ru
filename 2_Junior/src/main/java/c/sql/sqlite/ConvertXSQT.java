package c.sql.sqlite;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
/**
 * Class ConvertXSQT.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class ConvertXSQT {

    public void convert(File source, File dest, File scheme) throws TransformerException, FileNotFoundException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream(scheme)));
        transformer.transform(new StreamSource(new FileInputStream(source)), new StreamResult(new FileOutputStream(dest)));
    }
}
