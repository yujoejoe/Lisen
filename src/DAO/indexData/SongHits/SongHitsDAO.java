package DAO.indexData.SongHits;

import POJO.indexData.SongHits;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongHitsDAO {
    public ArrayList<SongHits> select(SongHits SongHits) throws SQLException;
    public int count(SongHits SongHits) throws SQLException;

}
