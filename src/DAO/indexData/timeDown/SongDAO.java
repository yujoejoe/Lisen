package DAO.indexData.timeDown;

import POJO.indexData.TimeDown;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDAO {
    public ArrayList<TimeDown> select(TimeDown song) throws SQLException;
    public int count(TimeDown song) throws SQLException;

}
