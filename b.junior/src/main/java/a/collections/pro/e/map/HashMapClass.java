package a.collections.pro.e.map;

import a.collections.pro.c.list.SimplyConnectedList;
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
        capacity = 100;
        amountNumbers = 2;
        this.array = new SimplyConnectedList[capacity];
        position = 0;
    }
    /**
     * Метод увеличивает вместимость в 10 раз.
     */
    private void reSize() {
        if (position == array.length) {
            capacity = capacity * 10;
            amountNumbers++;
            SimplyConnectedList[] newArray =  new SimplyConnectedList[capacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            this.array = newArray;
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
        int hash = key.hashCode();
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
        int pos = position(key.hashCode());
        if (array[pos] != null) {
            result = (V) array[pos].get(key);
        }
        return result;
    }
    /**
     * Метод удаляет элемент из коллекции.
     * @param key - ключ.
     */
    public void delete(K key) {
        int pos = position(key.hashCode());
        if (array[pos].getSize() > 1) {
            array[pos].delete(key);
        } else {
            array[pos] = null;
        }
    }
}
