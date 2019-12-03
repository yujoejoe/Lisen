package DAO.album;

import POJO.AlbumS;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AlbumSDAO {
    public ArrayList<AlbumS> select(AlbumS albums) throws SQLException;
    public int count(AlbumS albums) throws SQLException;

}
