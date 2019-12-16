package POJO.Type;

import POJO.Base;

/**
 * Created by user on 2019/12/3.
 */
public class Version extends Base {
    private int id;
    private String name;

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
        return "\""+ name + "\"";
    }
}
