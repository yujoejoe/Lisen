package ServiceDAO.mv;

import DAO.mv.mvDAOImp;
import POJO.MV;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/3.
 */
public class mvServiceDAOImp implements mvServiceDAO {
    @Override
    public ArrayList<MV> select(MV mv) {
        Connection conn = DBUtil.getConnection();
        mvDAOImp mvDI = new mvDAOImp(conn);

        try {
            ArrayList<MV> resultList = mvDI.select(mv);
            conn.commit();

            return resultList;
        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            }
            return null;

        } finally {
            if (conn != null) {
                DBUtil.closeConnection(conn);
            }
        }
    }

    @Override
    public ArrayList<MV> selectRan(MV mv) {
        Connection conn = DBUtil.getConnection();
        mvDAOImp mvDI = new mvDAOImp(conn);

        try{
            ArrayList<MV> resultList = mvDI.selectRan(mv);
            conn.commit();
            return  resultList;
        }catch(Exception e){
            try{
                conn.rollback();
            }catch(SQLException sqe){
                sqe.printStackTrace();
            }
            return null;
        }finally {
            if(conn != null){
                DBUtil.closeConnection(conn);
            }
        }
    }

    @Override
    public int insert(MV mv) {
        return 0;
    }

    @Override
    public int delete(MV mv) {
        return 0;
    }

    @Override
    public int update(MV mv) {
        return 0;
    }

    @Override
    public int count(MV mv) {
        Connection conn = DBUtil.getConnection();
        mvDAOImp singerDI = new mvDAOImp(conn);

        try{
            int cnt = singerDI.count(mv);
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
