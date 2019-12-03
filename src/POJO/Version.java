package POJO;

/**
 * Created by user on 2019/12/3.
 */
public class Version {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return '\'' + name + '\'';
    }
}
