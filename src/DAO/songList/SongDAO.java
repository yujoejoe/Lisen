package DAO.songList;

import POJO.songList.Song;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/16.
 */
public interface SongDAO {
    public ArrayList<Song> select(Song song) throws SQLException;
    public ArrayList<Song> selectList(Song song) throws SQLException;
    public int insert(Song song) throws SQLException;
    public int delete(Song song) throws SQLException;
    public int update(Song song) throws SQLException;
    public int count(Song song) throws SQLException;
}
