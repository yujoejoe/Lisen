package DAO.album;

import POJO.Album;
import POJO.Singer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public interface AlbumDAO {
    public ArrayList<Album> select(Album album) throws SQLException;
    public int count(Album album) throws SQLException;
}
