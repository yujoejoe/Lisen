package DAO.onesinger;

import POJO.Singer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OneSingerDAO {

    public ArrayList<Singer> select(Singer singer) throws SQLException;
    public int count(Singer singer) throws SQLException;
}
