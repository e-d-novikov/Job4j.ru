package b.oop.a.tracker.abstracts;

import b.oop.a.tracker.interfaces.UserAction;
/**
 * Class BaseAction.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1.0
 * @since 14/06/2018
 */
public abstract class BaseAction implements UserAction {

    private final int key;

    private final String name;

    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}