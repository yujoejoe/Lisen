package DAO.mv;

import POJO.MV;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/3.
 */
public interface mvDAO {
    public ArrayList<MV> select(MV mv) throws SQLException;
    public ArrayList<MV> selectRan(MV mv) throws SQLException;
    public int insert(MV mv) throws SQLException;
    public int delete(MV mv) throws SQLException;
    public int update(MV mv) throws SQLException;
    public int count(MV mv) throws SQLException;
}
