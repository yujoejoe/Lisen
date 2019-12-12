package ServiceDAO.indexData.EuropeAmerica;

import DAO.indexData.EuropeAmerica.EuropeAmericaDAOImp;
import POJO.indexData.EuropeAmerica;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EuropeAmericaServiceDAOImp implements EuropeAmericaServiceDAO {

    @Override
    public ArrayList<EuropeAmerica> select(EuropeAmerica EuropeAmerica) {
        Connection conn = DBUtil.getConnection();
        EuropeAmericaDAOImp EuropeAmericaDI = new EuropeAmericaDAOImp(conn);

        try {
            ArrayList<EuropeAmerica> resultLists = new ArrayList<>();
            resultLists = EuropeAmericaDI.select(EuropeAmerica);
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
    public int count(EuropeAmerica EuropeAmerica) {
        Connection conn = DBUtil.getConnection();
        EuropeAmericaDAOImp EuropeAmericaDI = new EuropeAmericaDAOImp(conn);

        try{
            int cnt = EuropeAmericaDI.count(EuropeAmerica);
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
    public ArrayList<EuropeAmerica> selectRan(EuropeAmerica EuropeAmerica) {
        return null;
    }

    @Override
    public int insert(EuropeAmerica EuropeAmerica) {
        return 0;
    }

    @Override
    public int delete(EuropeAmerica EuropeAmerica) {
        return 0;
    }

    @Override
    public int update(EuropeAmerica EuropeAmerica) {
        return 0;
    }

}
