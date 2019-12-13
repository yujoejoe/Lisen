package DAO.indexData.timeDown;

import POJO.indexData.timeDown;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDAO {
    public ArrayList<timeDown> select(timeDown song) throws SQLException;
    public int count(timeDown song) throws SQLException;

}
