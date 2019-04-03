package c.collections.lite.c.sorting;
/**
 * User.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 1
 */
public class User implements Comparable<User> {

     public int age;
     public String name;

     User(String name, int age) {
         this.age = age;
         this.name = name;
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }
}

