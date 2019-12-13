package ServiceDAO.indexData.SongHits;

import DAO.indexData.SongHits.SongHitsDAOImp;
import POJO.indexData.SongHits;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongHitsServiceDAOImp implements SongHitsServiceDAO {

    @Override
    public ArrayList<SongHits> select(SongHits SongHits) {
        Connection conn = DBUtil.getConnection();
        SongHitsDAOImp SongHitsDI = new SongHitsDAOImp(conn);

        try {
            ArrayList<SongHits> resultLists = new ArrayList<>();
            resultLists = SongHitsDI.select(SongHits);
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
    public int count(SongHits SongHits) {
        Connection conn = DBUtil.getConnection();
        SongHitsDAOImp SongHitsDI = new SongHitsDAOImp(conn);

        try{
            int cnt = SongHitsDI.count(SongHits);
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
    public ArrayList<SongHits> selectRan(SongHits SongHits) {
        return null;
    }

    @Override
    public int insert(SongHits SongHits) {
        return 0;
    }

    @Override
    public int delete(SongHits SongHits) {
        return 0;
    }

    @Override
    public int update(SongHits SongHits) {
        return 0;
    }

}
