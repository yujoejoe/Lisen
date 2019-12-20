package POJO.songList;

import POJO.Base;

/**
 * Created by user on 2019/12/16.
 */
public class Song extends Base {
    private int id;
    private int hits;
    private int status;
    private String date;
    private String song;
    private String style;
    private String album;
    private String singer;
    private String format;
    private String duration;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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
        return "Song{" +
                "id=" + id +
                ", hits=" + hits +
                ", date='" + date + '\'' +
                ", song='" + song + '\'' +
                ", style='" + style + '\'' +
                ", album='" + album + '\'' +
                ", singer='" + singer + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
