package c.sql.sqlite.objects;
/**
 * Class Config.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class Config {

    private String nameDataBaseFile;

    public Config(String nameDataBaseFile) {
        this.nameDataBaseFile = nameDataBaseFile;
    }

    public String getNameDataBaseFile() {
        return nameDataBaseFile;
    }
}
