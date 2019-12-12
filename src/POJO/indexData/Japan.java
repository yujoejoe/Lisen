package POJO.indexData;

import POJO.Base;

/**
 * Created by user on 2019/12/12.
 */
public class Japan extends Base {

    String name;
    String date;
    String img;
    String singer;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }


    @Override
    public String toString() {
        return " ["
                + "name:" + name
                + " ,singer:" + singer
                + " ,img:" + img
                + " ,date:" + date
                + "]";
    }
}
