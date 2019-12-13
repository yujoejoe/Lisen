package POJO.indexData;

import POJO.Base;

/**
 * Created by user on 2019/12/12.
 */
public class Japan extends Base {

    String song;
    String date;
    String img;
    String singer;
    String duration;


    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
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
        return " ["
                + "song:" + song
                + " ,singer:" + singer
                + " ,img:" + img
                + " ,date:" + date
                + " ,duration:" + duration
                + "]";
    }
}
