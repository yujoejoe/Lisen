package POJO;

/**
 * Created by user on 2019/12/12.
 */
public class SongList extends Base{
    private String author;
    private String style;
    private String theme;
    private String name;
    private String date;
    private String img;
    private int play;
    private int id;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public SongList(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

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

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }


    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", style='" + style + '\'' +
                ", theme='" + theme + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", play=" + play +
                '}';
    }
}
