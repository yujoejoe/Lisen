package POJO;

/**
 *
 */
public class AlbumS extends Base {
    String song;
    String singer;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String img;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    String duration;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
    @Override
    public String toString() {
        return "album ["
                + "song:" + song
                + " ,singer:" + singer
                + " ,duration:" + duration
                + "]";
    }


}
