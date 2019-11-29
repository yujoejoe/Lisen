package DAO.album;

import POJO.Album;

import java.sql.SQLException;
import java.util.ArrayList;
public interface AlbumDAO {
    public ArrayList<Album> select(Album album) throws SQLException;

}
