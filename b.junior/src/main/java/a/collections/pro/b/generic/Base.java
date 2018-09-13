package a.collections.pro.b.generic;
/**
 * @author Egor Novikov (e.novikov@yahoo.com)
 */
public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

