package ServiceDAO.songList;

import DAO.songList.SongListDAOImp;
import POJO.songList.SongList;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/12.
 */
public class SongListServiceDAOImp implements SongListServiceDAO{
    @Override
    public ArrayList<SongList> select(SongList sList) {
        Connection conn = DBUtil.getConnection();
        SongListDAOImp slDI = new SongListDAOImp(conn);

        try {
            ArrayList<SongList> resultList = new ArrayList<>();
            resultList = slDI.select(sList);
            conn.commit();
            return resultList;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return null;
        } finally {
            if(conn != null){
                DBUtil.closeConnection(conn);
            }
        }
    }

    @Override
    public ArrayList<SongList> selectRan(SongList sList) {
        return null;
    }

    @Override
    public int insert(SongList sList) {
        return 0;
    }

    @Override
    public int delete(SongList sList) {
        return 0;
    }

    @Override
    public int update(SongList sList) {
        return 0;
    }

    @Override
    public int count(SongList sList) {
        Connection conn = DBUtil.getConnection();
        SongListDAOImp singerDI = new SongListDAOImp(conn);

        try{
            int count = singerDI.count(sList);
            conn.commit();
            return count;
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
}
