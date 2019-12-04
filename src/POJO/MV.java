package POJO;

/**
 * Created by user on 2019/12/3.
 */
public class MV extends Base{
    private int id;
    private int play;
    private String img;
    private String url;
    private String area;
    private String date;
    private String title;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String singer;
    private String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "{" +
                "\"play\": " + play +
                ", \"img\": \"" + img + '\"' +
                ", \"url\": \"" + url + '\"' +
                ", \"area\": \"" + area + '\"' +
                ", \"date\": \"" + date + '\"' +
                ", \"title\": \"" + title + '\"' +
                ", \"singer\": \"" + singer + '\"' +
                ", \"version\": \"" + version + '\"' +
                '}';
    }
}
