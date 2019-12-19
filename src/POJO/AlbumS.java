package POJO;

/**
 *
 */
public class AlbumS extends Base {
    String song;
    String singer;
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

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
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
