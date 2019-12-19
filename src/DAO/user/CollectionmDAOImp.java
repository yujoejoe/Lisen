package DAO.user;

import POJO.Collection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionmDAOImp implements CollectionmDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;


    public CollectionmDAOImp() {
        super();
    }

    public CollectionmDAOImp(Connection conn) {
        super();
        this.conn = conn;
    }


    @Override
    public ArrayList<Collection> select(Collection collection) throws SQLException {
        try {
            String sql =
                    "select"
                            + " mv.title as mvName,"
                            + " mv.img as mvImg"
                            + " from"
                            + " collectionMv"
                            + " left join"
                            + " mv"
                            + " on "
                            + "collectionMv.mvId = mv.id"
                            + " left join"
                            + " user"
                            + " on "
                            + "collectionMv.userId = user.id"
                            + " where 1=1";


            // 添加条件
            String condition = collection.getCondition();
            if (condition != null && !condition.equals("")) {
                sql += " and" + condition;
            }
            // 排序
            String orderBy = collection.getOrderBy();
            if (orderBy != null && !orderBy.equals("")) {
                sql += orderBy;
            }
            // 分页
            String limit = collection.getLimit();
            if (limit != null && limit.equals("")) {
                sql += limit;
            }

            // 控制台输出sql语句，检验正确性
//            System.out.println("collection SELECT: " +sql);

            // 创建prepareStatement对象
            pst = conn.prepareStatement(sql);
            // 执行查询语句并返回结果集
            ResultSet rs = pst.executeQuery();

            // 创建ArrayList对象存储每条记录
            ArrayList<Collection> resultList = new ArrayList<Collection>();

            while (rs.next()) {
                Collection tmp = new Collection();
                tmp.setMvImg(rs.getString("mvImg"));
                tmp.setMvName(rs.getString("mvName"));
                resultList.add(tmp);
            }

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Collection> selectRan(Collection collection) throws SQLException {
        return null;
    }

    @Override
    public int insert(Collection collection) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Collection collection) throws SQLException {
        return 0;
    }

    @Override
    public int update(Collection collection) throws SQLException {
        return 0;
    }

    @Override
    public int count(Collection collection) throws SQLException {
        try {
            // sql语句
            String sql = "select count(*) as counts from collectionMv inner  join  user on collectionMv.userId = user.id  where 1=1";
            // 添加条件
            String condition = collection.getCondition();
            if (condition != null && !condition.equals("")) {
                sql += " and" + condition;
            }

            pst = conn.prepareStatement(sql);

            // 控制台输出sql语句，检验正确性
//            System.out.println("collection COUNT: "+sql);

            ResultSet rs = pst.executeQuery();
            rs.next();

            int counts = Integer.parseInt(rs.getString("counts"));

            return counts;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
