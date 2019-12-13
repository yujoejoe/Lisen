package ServiceDAO.indexData.timeDown;

import DAO.indexData.timeDown.SongDAOImp;
import POJO.indexData.timeDown;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class timeDownServiceDAOImp implements timeDownServiceDAO {

    @Override
    public ArrayList<timeDown> select(timeDown song) {
        Connection conn = DBUtil.getConnection();
        SongDAOImp songDI = new SongDAOImp(conn);

        try {
            ArrayList<timeDown> resultLists = new ArrayList<>();
            resultLists = songDI.select(song);
            conn.commit();

            return resultLists;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqe) {
                System.out.println("failed to rollback!");
                sqe.printStackTrace();
            }
            e.printStackTrace();
            return null;
        } finally {
            // 释放数据库资源
            if (conn != null) {
                DBUtil.closeConnection(conn);
            }
        }
    }

    @Override
    public int count(timeDown song) {
        Connection conn = DBUtil.getConnection();
        SongDAOImp songDI = new SongDAOImp(conn);

        try{
            int cnt = songDI.count(song);
            conn.commit();

            return cnt;
        }catch(Exception e){
            try{
                conn.rollback();
            }catch(SQLException sqe){
                System.out.println("failed to rollback!");
                sqe.printStackTrace();
            }
            e.printStackTrace();
            return -1;
        }finally {
            // 释放数据库资源
            if(conn != null){
                DBUtil.closeConnection(conn);
            }
        }
    }

    @Override
    public ArrayList<timeDown> selectRan(timeDown song) {
        return null;
    }

    @Override
    public int insert(timeDown song) {
        return 0;
    }

    @Override
    public int delete(timeDown song) {
        return 0;
    }

    @Override
    public int update(timeDown song) {
        return 0;
    }

}
