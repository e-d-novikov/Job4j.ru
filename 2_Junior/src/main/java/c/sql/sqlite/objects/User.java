package c.sql.sqlite.objects;
/**
 * Class User.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class User {

    private List<Field> values;

    public User() {
    }

    public User(List<Field> values) {
        this.values = values;
    }

    public List<Field> getValues() {
        return values;
    }

    public void setValues(List<Field> values) {
        this.values = values;
    }
}
