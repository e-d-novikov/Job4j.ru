package a.collections.pro.b.generic;
/**
 * Class AbstractStore.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {
    /**
     * Container.
     */
    private SimpleArray<E> container = new SimpleArray();
    /**
     * The method add model in container.
     * @param model - model.
     */
    @Override
    public void add(E model) {
        container.add(model);
    }
    /**
     * The method replace model in container.
     * @param id - id;
     * @param model - new model;
     * @return - true, if replace successfully, else false;
     */
    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        for (int i = 0; i < container.getSize(); i++) {
            if (container.get(i).getId() == id) {
                container.set(i, model);
                result = true;
            }
        }
        return result;
    }
    /**
     * The method remove model in container.
     * @param id - id;
     * @return - true, if remove successfully, else false;
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < container.getSize(); i++) {
            if (container.get(i).getId() == id) {
                container.delete(i);
                result = true;
            }
        }
        return result;
    }
    /**
     * The method find model by ID.
     * @param id - id.
     * @return - model.
     */
    @Override
    public E findById(String id) {
        E result = null;
        for (int i = 0; i < container.getSize(); i++) {
            if (container.get(i).getId() == id) {
                result = container.get(i);
            }
        }
        return result;
    }
}
