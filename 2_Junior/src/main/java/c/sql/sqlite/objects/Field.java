package c.sql.sqlite.objects;
/**
 * Class Field.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Field {

    private int value;

    public Field() {
    }

    public Field(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
