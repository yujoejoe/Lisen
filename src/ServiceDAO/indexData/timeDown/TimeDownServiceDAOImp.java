package ServiceDAO.indexData.timeDown;

import DAO.indexData.timeDown.SongDAOImp;
import POJO.indexData.TimeDown;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimeDownServiceDAOImp implements TimeDownServiceDAO {

    @Override
    public ArrayList<TimeDown> select(TimeDown song) {
        Connection conn = DBUtil.getConnection();
        SongDAOImp songDI = new SongDAOImp(conn);

        try {
            ArrayList<TimeDown> resultLists = new ArrayList<>();
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
    public int count(TimeDown song) {
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
    public ArrayList<TimeDown> selectRan(TimeDown song) {
        return null;
    }

    @Override
    public int insert(TimeDown song) {
        return 0;
    }

    @Override
    public int delete(TimeDown song) {
        return 0;
    }

    @Override
    public int update(TimeDown song) {
        return 0;
    }

}
