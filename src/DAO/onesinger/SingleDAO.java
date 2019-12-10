package DAO.onesinger;

import POJO.Album;
import POJO.Single;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SingleDAO {
    public ArrayList<Single> select(Single single) throws SQLException;
    public int count(Single single) throws SQLException;

}
