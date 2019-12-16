package DAO.user;

import POJO.Collection;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CollectionmDAO  {
    public ArrayList<Collection> select(Collection collection) throws SQLException;
    public ArrayList<Collection> selectRan(Collection collection) throws SQLException;
    public int insert(Collection collection) throws SQLException;
    public int delete(Collection collection) throws SQLException;
    public int update(Collection collection) throws SQLException;
    public int count(Collection collection) throws SQLException;

}
