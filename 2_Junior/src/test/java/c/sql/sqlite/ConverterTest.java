package c.sql.sqlite;

import org.junit.Test;
import java.io.File;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConverterTest {

    String dir = System.getProperty("user.dir");
    File first = new File(dir + "/src/main/java/c/sql/sqlite/xml/XML.xml");
    File second = new File(dir + "/src/main/java/c/sql/sqlite/xml/XSQT.xml");
    File scheme = new File(dir + "/src/main/java/c/sql/sqlite/xml/Scheme.xml");


    @Test
    public void hasNextNextSequentialInvocation() throws Exception {
        BigInteger result = new BigInteger("5050");
        assertThat(Converter.convert(first, second, scheme), is(result));
    }
}
