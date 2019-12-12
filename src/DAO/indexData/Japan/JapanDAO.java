package DAO.indexData.Japan;

import POJO.indexData.Japan;
import java.sql.SQLException;
import java.util.ArrayList;

public interface JapanDAO {
    public ArrayList<Japan> select(Japan Japan) throws SQLException;
    public int count(Japan Japan) throws SQLException;

}
