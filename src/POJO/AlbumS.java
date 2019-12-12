package POJO;

/**
 *
 */
public class AlbumS extends Base {
    String song;
    String singer;

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
                + "timeDown:" + song
                + " ,singer:" + singer
                + "]";
    }


}
