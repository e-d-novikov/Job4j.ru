package c.sql.sqlite.objects;

public class Config {

    private String nameDataBaseFile;

    public Config(String nameDataBaseFile) {
        this.nameDataBaseFile = nameDataBaseFile;
    }

    public String getNameDataBaseFile() {
        return nameDataBaseFile;
    }
}
