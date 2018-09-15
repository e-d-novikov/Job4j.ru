package a.collections.pro.e.map;
/**
 * Класс HashMapClass.
 * Описывает структуру HashMap.
 * В массив добавляется односвязный список SimplyConnectedList.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class HashMapClass<K, V> {
    /**
     * Контейнер.
     */
    private SimplyConnectedList[] array;
    /**
     * Количество объектов в коллекции.
     */
    private int position;
    /**
     * Вместимость коллекции.
     */
    private int capacity;
    /**
     * Количество знаков для определения позиции в массиве.
     */
    private int amountNumbers;
    /**
     * Конструктор.
     */
    HashMapClass() {
        capacity = 10;
        amountNumbers = 1;
        this.array = new SimplyConnectedList[capacity];
        position = 0;
    }
    /**
     * Метод увеличивает вместимость в 10 раз.
     */
    private void reSize() {
        if (position == capacity) {
            capacity = capacity * 10;
            amountNumbers++;
            position = 0;
            SimplyConnectedList[] newArray = array;
            this.array = new SimplyConnectedList[capacity];
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i] != null && newArray[i].getSize() != 0) {
                    int index = newArray[i].getSize();
                    for (int j = 0; j < index; j++) {
                        insert((K) newArray[i].getKey(j), (V) newArray[i].getData(j));
                    }
                }
            }
        }
    }
    /**
     * Метод вычисляет индекс ячейки в массиве.
     * Свертка до amountNumbers числа.
     * @param hash - хещ-код объекта;
     * @return - индекс массива.
     */
    private int position(int hash) {
        String string = Integer.toString(hash);
        char[] array = string.toCharArray();
        char[] resul = new char[amountNumbers];
        int res = 0;
        int indexChar = 0;
        for (int i = 0; i < array.length; i++) {
            resul[indexChar] = array[i];
            indexChar++;
            if (indexChar == amountNumbers) {
                res = res + Integer.parseInt(String.copyValueOf(resul));
                resul = new char[amountNumbers];
                indexChar = 0;
            }
        }
        int result = res % capacity;
        return result;
    }
    /**
     * Метод добавляет объект в коллекцию.
     * @param key - ключ;
     * @param data - объект.
     */
    public void insert(K key, V data) {
        reSize();
        int hash = Math.abs(key.hashCode());
        int pos = position(hash);
        if (array[pos] != null) {
            array[pos].add(key, data);
            position++;
        } else {
            array[pos] = new SimplyConnectedList();
            array[pos].add(key, data);
            position++;
        }
    }
    /**
     * Метод возвращает объект из коллекции.
     * @param key - ключ;
     * @return - объект.
     */
    public V get(K key) {
        V result = null;
        int pos = position(Math.abs(key.hashCode()));
        if (!array[pos].equals(null)) {
            result = (V) array[pos].get(key);
        }
        return result;
    }
    /**
     * Метод удаляет элемент из коллекции.
     * @param key - ключ.
     */
    public void delete(K key) {
        int pos = position(Math.abs(key.hashCode()));
        if (array[pos].getSize() > 1) {
            array[pos].delete(key);
            position--;
        } else {
            array[pos] = null;
            position--;
        }
    }
    /**
     * Метод количество элементов коллекции.
     * @return - количество элементов.
     */
    public int size() {
        return position;
    }
    /**
     * Класс реализует простой связанный список.
     * @param <K> - ключ;
     * @param <V> - объект.
     */
    private static class SimplyConnectedList<K, V> {
        /**
         * Размер.
         */
        private int size;
        /**
         * Начало списка.
         */
        private Node<K, V> first;
        /**
         * Метод добавляет элемент в список.
         */
        private void add(K key, V data) {
            Node<K, V> newLink = new Node<>(key, data);
            newLink.next = this.first;
            this.first = newLink;
            this.size++;
        }
        /**
         * Удаляет элемент с заданным ключом.
         * @param key - ключ.
         */
        private void delete(K key) {
            if (first.key == key) {
                first = first.next;
            } else {
                Node<K, V> current = first;
                Node<K, V> previous;
                for (int i = 0; i < size; i++) {
                    previous = current;
                    current = current.next;
                    if (current.key == key) {
                        previous.next = current.next;
                        //first = current;
                        size--;
                        break;
                    }
                    if (current.next == null) {
                        break;
                    }
                }
            }
        }
        /**
         * Метод возвращает элемент с заданным ключом.
         * @param key - ключ.
         */
        private V get(K key) {
            V result = null;
            if (first.key.equals(key)) {
                result = first.data;
            } else {
                Node<K, V> current = first;
                for (int i = 0; i < size; i++) {
                    current = current.next;
                    if (current.key.equals(key)) {
                        result = current.data;
                        break;
                    }
                    if (current.next == null) {
                        break;
                    }
                }
            }
            return result;
        }
        /**
         * Метод возвращает ключ по индексу.
         * @param index - индекс;
         * @return - ключ.
         */
        private K getKey(int index) {
            K result = null;
            if (index == 0) {
                result = first.key;
            } else {
                Node<K, V> current = first;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                result = current.key;
            }
            return result;
        }
        /**
         * Метод возвращает объект по индексу.
         * @param index - индекс;
         * @return - объект.
         */
        private V getData(int index) {
            V result = null;
            if (index == 0) {
                result = first.data;
            } else {
                Node<K, V> current = first;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                result = current.data;
            }
            return result;
        }
        /**
         * Метод возвращает количество элементов списка.
         * @return - количество элементов.
         */
        private int getSize() {
            return this.size;
        }
    }
    /**
     * Ячейка списка.
     * @param <K> - ключ.
     * @param <V> - объект.
     */
    private static class Node<K, V> {
        /**
         * Ключ.
         */
        K key;
        /**
         * Объект.
         */
        V data;
        /**
         * Ссылка на следующий объект.
         */
        Node<K, V> next;
        /**
         * Конструктор.
         * @param key - ключ;
         * @param data - объект.
         */
        Node(K key, V data) {
            this.key = key;
            this.data = data;
        }
    }
}
