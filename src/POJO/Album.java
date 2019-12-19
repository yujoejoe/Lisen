package POJO;

/**
 *
 */
public class Album extends Base{

    String name;
    String singer;
//    String song;
    String date;
    String img;
    String company;
    String language;
    String genre;



  /*  public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }*/

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
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

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }



    @Override
    public String toString() {
        return "album ["
                + "name:" + name
                + " ,singer:" + singer
                + " ,date:" + date
                + " ,img:" + img
                + " ,company:" + company
                + " ,language:" + language
                + " ,genre:" + genre
                + "]";
    }
}
