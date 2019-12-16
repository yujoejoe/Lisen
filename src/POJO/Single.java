package POJO;

public class Single extends Base {
    String song;
    String album;
    String duration;
    String singer;




    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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
        return "single ["
                + "song:" + song
                + " ,album:" + album
                + " ,singer:" + singer
                + " ,duration:" + duration
                + "]";
    }
}
