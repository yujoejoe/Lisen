package ServiceDAO.indexData.timeDown;

import POJO.indexData.Song;

import java.util.ArrayList;

public interface SongServiceDAO {
    public ArrayList<Song> select(Song song);
    public ArrayList<Song> selectRan(Song song);
    public int insert(Song song);
    public int delete(Song song);
    public int update(Song song);
    public int count(Song song);


}
