package POJO.indexData;

import POJO.Base;

/**
 * Created by user on 2019/12/12.
 */
public class SongHits extends Base {

    String song;
    String hits;
    String img;
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

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getHits() {
        return hits;
    }
    public void setHits(String hits) {
        this.hits = hits;
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
                + " ,hits:" + hits
                + " ,duration:" + duration
                + "]";
    }
}
