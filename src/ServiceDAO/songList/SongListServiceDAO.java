package ServiceDAO.songList;

import POJO.SongList;

import java.util.ArrayList;

/**
 * Created by user on 2019/12/12.
 */
public interface SongListServiceDAO {
    public ArrayList<SongList> select(SongList sList);
    public ArrayList<SongList> selectRan(SongList sList);
    public int insert(SongList sList);
    public int delete(SongList sList);
    public int update(SongList sList);
    public int count(SongList sList);
}
