package ServiceDAO.songList;

import POJO.songList.Song;

import java.util.ArrayList;

/**
 * Created by user on 2019/12/16.
 */
public interface SongServiceDAO {
    public ArrayList<Song> select(Song song);
    public ArrayList<Song> selectList(Song song);
    public int insert(Song song);
    public int delete(Song song);
    public int update(Song song);
    public int count(Song song);
}
