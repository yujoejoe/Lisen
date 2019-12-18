package ServiceDAO.users;


import DAO.user.CollectionsDAOImp;
import POJO.Collection;


import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class SServiceDAOImp implements SServiceDAO {


    @Override
    public ArrayList<Collection> select(Collection collection) {
        Connection conn = DBUtil.getConnection();
        CollectionsDAOImp collectionDI = new CollectionsDAOImp(conn);

        try{
            ArrayList<Collection> resultLists = collectionDI.select(collection);
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
    public int count(Collection collection) {
        Connection conn = DBUtil.getConnection();
        CollectionsDAOImp collectionDI = new CollectionsDAOImp(conn);

        try{
            int cnt = collectionDI.count(collection);
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
    public int insert(Collection collection) {


        return 0;
    }

    @Override
    public int delete(Collection collection) {
        return 0;
    }
}
