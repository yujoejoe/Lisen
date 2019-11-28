package POJO;

/**
 * Created by user on 2019/11/28.
 */
public class Singer extends Base{
    String name;
    String alias;
    char py;
    String sex;
    String birthday;
    int areaId;
    int styleId;
    int songNum;
    String fans;

    String img;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public char getPy() {
        return py;
    }

    public void setPy(char py) {
        this.py = py;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return "Singer ["
                + "name:" + name
                + " ,alias:" + alias
                + " ,py:" + py
                + " ,sex:" + sex
                + " ,birthday:" + birthday
                + " ,areaId:" + areaId
                + " ,styleId:" + styleId
                + " ,songNum:" + songNum
                + " ,fans:" + fans
                + " , img:" + img
                + "]";

    }
}
