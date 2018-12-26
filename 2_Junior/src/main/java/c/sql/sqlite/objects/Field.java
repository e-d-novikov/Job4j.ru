package c.sql.sqlite.objects;

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
