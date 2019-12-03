package ServiceDAO.album;

import DAO.album.AlbumDAOImp;
import DAO.album.AlbumSDAOImp;
import POJO.AlbumS;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumSServiceDAOImp implements AlbumSServiceDAO {


    @Override
    public ArrayList<AlbumS> select(AlbumS albums) {
        Connection conn = DBUtil.getConnection();
        AlbumSDAOImp albumDI = new AlbumSDAOImp(conn);

        try {
            ArrayList<AlbumS> resultLists = new ArrayList<>();
            resultLists = albumDI.select(albums);
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
    public ArrayList<AlbumS> selectRan(AlbumS album) {
        return null;
    }

    @Override
    public int insert(AlbumS album) {
        return 0;
    }

    @Override
    public int delete(AlbumS album) {
        return 0;
    }

    @Override
    public int update(AlbumS album) {
        return 0;
    }

    @Override
    public int count(AlbumS albums) {
        Connection conn = DBUtil.getConnection();
        AlbumSDAOImp albumDI = new AlbumSDAOImp(conn);

        try{
            int cnt = albumDI.count(albums);
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
}
