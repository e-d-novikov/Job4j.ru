package c.sql.sqlite.objects;

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
