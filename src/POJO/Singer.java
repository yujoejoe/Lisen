package POJO;

/**
 * Created by user on 2019/11/28.
 */
public class Singer extends Base{
    String name;
    String alias;
    String py;
    String sex;
    String birthday;
    String area;
    String style;
    int songNum;
    String fans;
    String img;

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    String birthPlace;


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

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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
        return "singer ["
                + "name:" + name
                + " ,alias:" + alias
                + " ,py:" + py
                + " ,sex:" + sex
                + " ,birthday:" + birthday
                + " ,area:" + area
                + " ,style:" + style
                + " ,songNum:" + songNum
                + " ,fans:" + fans
                + " , img:" + img
                + " , birthPlace:" + birthPlace
                + "]";

    }
}
