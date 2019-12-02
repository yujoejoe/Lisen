package ServiceDAO.album;

import DAO.album.AlbumDAO;
import DAO.album.AlbumDAOImp;
import util.DBUtil;
import POJO.Album;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class AlbumServiceDAOImp implements AlbumServiceDAO {


    @Override
    public ArrayList<Album> select(Album album) {
        Connection conn = DBUtil.getConnection();
        AlbumDAOImp albumDI = new AlbumDAOImp(conn);

        try {
            ArrayList<Album> resultLists = new ArrayList<Album>();
            resultLists = albumDI.select(album);
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
    public ArrayList<Album> selectRan(Album album) {
        return null;
    }

    @Override
    public int insert(Album album) {
        return 0;
    }

    @Override
    public int delete(Album album) {
        return 0;
    }

    @Override
    public int update(Album album) {
        return 0;
    }

    @Override
    public int count(Album album) {
        Connection conn = DBUtil.getConnection();
        AlbumDAOImp  albumDI = new AlbumDAOImp(conn);

        try{
            int cnt = albumDI.count(album);
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
