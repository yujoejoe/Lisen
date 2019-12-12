package ServiceDAO.indexData.Japan;

import DAO.indexData.Japan.JapanDAOImp;
import POJO.indexData.Japan;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class JapanServiceDAOImp implements JapanServiceDAO {

    @Override
    public ArrayList<Japan> select(Japan Japan) {
        Connection conn = DBUtil.getConnection();
        JapanDAOImp JapanDI = new JapanDAOImp(conn);

        try {
            ArrayList<Japan> resultLists = new ArrayList<>();
            resultLists = JapanDI.select(Japan);
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
    public int count(Japan Japan) {
        Connection conn = DBUtil.getConnection();
        JapanDAOImp JapanDI = new JapanDAOImp(conn);

        try{
            int cnt = JapanDI.count(Japan);
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
    public ArrayList<Japan> selectRan(Japan Japan) {
        return null;
    }

    @Override
    public int insert(Japan Japan) {
        return 0;
    }

    @Override
    public int delete(Japan Japan) {
        return 0;
    }

    @Override
    public int update(Japan Japan) {
        return 0;
    }

}
