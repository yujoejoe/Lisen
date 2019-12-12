package POJO;

public class Collection extends Base {
    String song;
    String singer;
    String duration;
    String songListImg;
    String songListName;
    String albumListImg;
    String albumListName;
    String mvImg;
    String mvName;

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

    public String getSongListImg() {
        return songListImg;
    }

    public void setSongListImg(String songListImg) {
        this.songListImg = songListImg;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public String getAlbumListImg() {
        return albumListImg;
    }

    public void setAlbumListImg(String albumListImg) {
        this.albumListImg = albumListImg;
    }

    public String getAlbumListName() {
        return albumListName;
    }

    public void setAlbumListName(String albumListName) {
        this.albumListName = albumListName;
    }

    public String getMvImg() {
        return mvImg;
    }

    public void setMvImg(String mvImg) {
        this.mvImg = mvImg;
    }

    public String getMvName() {
        return mvName;
    }

    public void setMvName(String mvName) {
        this.mvName = mvName;
    }

    @Override
    public String toString() {
        return "single ["
                + "timeDown:" + song
                + " ,singer:" + singer
                + " ,duration:" + duration
                + " ,songListImg:" + songListImg
                + " ,songListName:" + songListName
                + " ,albumListImg:" + albumListImg
                + " ,albumListName:" + albumListName
                + " ,mvImg:" + mvImg
                + " ,mvName:" + mvName
                + "]";


    }
}
