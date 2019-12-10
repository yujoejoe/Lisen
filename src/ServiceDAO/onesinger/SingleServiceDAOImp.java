package ServiceDAO.onesinger;

import DAO.album.AlbumDAOImp;

import DAO.onesinger.SingleDAOImp;
import POJO.Single;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingleServiceDAOImp implements SingleServiceDAO{


    @Override
    public ArrayList<Single> select(Single single) {
        Connection conn = DBUtil.getConnection();
        SingleDAOImp singleDI = new SingleDAOImp(conn);

        try {
            ArrayList<Single> resultLists = new ArrayList<Single>();
            resultLists = singleDI.select(single);
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
    public int count(Single single) {
        Connection conn = DBUtil.getConnection();
        SingleDAOImp singleDI = new SingleDAOImp(conn);

        try{
            int cnt = singleDI.count(single);
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
