package DAO.songList;

import POJO.SongList;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/12.
 */
public interface SongListDAO {
    public ArrayList<SongList> select(SongList sList) throws SQLException;
    public ArrayList<SongList> selectRan(SongList sList) throws SQLException;
    public int insert(SongList sList) throws SQLException;
    public int delete(SongList sList) throws SQLException;
    public int update(SongList sList) throws SQLException;
    public int count(SongList sList) throws SQLException;
}
