package DAO;

import POJO.Singer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/11/28.
 */
public interface SingerDAO {
    public ArrayList<Singer> select(Singer singer) throws SQLException;
    public ArrayList<Singer> selectRan(Singer singer) throws SQLException;
    public int insert(Singer singer) throws SQLException;
    public int delete(Singer singer) throws SQLException;
    public int update(Singer singer) throws SQLException;
    public int count(Singer singer) throws SQLException;
}
