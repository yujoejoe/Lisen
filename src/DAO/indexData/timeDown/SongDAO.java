package DAO.indexData.timeDown;

import POJO.indexData.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDAO {
    public ArrayList<Song> select(Song song) throws SQLException;
    public int count(Song song) throws SQLException;

}
