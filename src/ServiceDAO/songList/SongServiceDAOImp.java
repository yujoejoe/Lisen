package ServiceDAO.songList;

import DAO.songList.SongDAOImp;
import POJO.songList.Song;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/16.
 */
public class SongServiceDAOImp implements SongServiceDAO{
    @Override
    public ArrayList<Song> select(Song song) {
        Connection conn = DBUtil.getConnection();
        SongDAOImp sDI = new SongDAOImp(conn);

        try {
            ArrayList<Song> resultList = sDI.select(song);
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
    public ArrayList<Song> selectList(Song song) {
        Connection conn = DBUtil.getConnection();
        SongDAOImp sDI = new SongDAOImp(conn);

        try {
            ArrayList<Song> resultList = new ArrayList<>();
            resultList = sDI.selectList(song);
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
    public int insert(Song song) {
        return 0;
    }

    @Override
    public int delete(Song song) {
        return 0;
    }

    @Override
    public int update(Song song) {
        return 0;
    }

    @Override
    public int count(Song song) {
        Connection conn = DBUtil.getConnection();
        SongDAOImp sDI = new SongDAOImp(conn);
        try {
            int count = sDI.count(song);
            conn.commit();
            return count;
        } catch (SQLException e) {
            try{
                conn.rollback();
            }catch(SQLException sqe){
                sqe.printStackTrace();
            }
            e.printStackTrace();
            return -1;
        } finally {
            if(conn != null){
                DBUtil.closeConnection(conn);
            }
        }
    }
}
