package ServiceDAO.singer;

import util.DBUtil;
import POJO.Singer;
import DAO.singer.SingerDAOImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by user on 2019/11/28.
 */
public class SingerServiceDAOImp implements SingerServiceDAO{
    @Override
    public ArrayList<Singer> select(Singer singer) {
        Connection conn = DBUtil.getConnection();
        SingerDAOImp singerDI = new SingerDAOImp(conn);

        try{
            ArrayList<Singer> resultLists = new ArrayList<Singer>();
            resultLists = singerDI.select(singer);
            conn.commit();

            return resultLists;
        }catch(Exception e){
            try{
                conn.rollback();
            }catch(SQLException sqe){
                System.out.println("failed to rollback!");
                sqe.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }finally {
            // 释放数据库资源
            if(conn != null){
                DBUtil.closeConnection(conn);
            }
        }
    }

    @Override
    public ArrayList<Singer> selectRan(Singer singer) {
        return null;
    }

    @Override
    public int insert(Singer singer) {
        return 0;
    }

    @Override
    public int delete(Singer singer) {
        return 0;
    }

    @Override
    public int update(Singer singer) {
        return 0;
    }

    @Override
    public int count(Singer singer) {
        Connection conn = DBUtil.getConnection();
        SingerDAOImp singerDI = new SingerDAOImp(conn);

        try{
            int cnt = singerDI.count(singer);
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
