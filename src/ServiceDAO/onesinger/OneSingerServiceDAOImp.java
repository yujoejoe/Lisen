package ServiceDAO.onesinger;


import DAO.onesinger.OneSingerDAOImp;
import POJO.Singer;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OneSingerServiceDAOImp implements OneSingerServiceDAO{


    @Override
    public ArrayList<Singer> select(Singer singer) {
        Connection conn = DBUtil.getConnection();

        OneSingerDAOImp singerDI = new OneSingerDAOImp(conn);

        try {
            ArrayList<Singer> resultLists = new ArrayList<>();
            resultLists = singerDI.select(singer);
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
    public int count(Singer singer) {
        Connection conn = DBUtil.getConnection();
        OneSingerDAOImp  singerDI= new OneSingerDAOImp(conn);

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
