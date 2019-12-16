package DAO.onesinger;



import POJO.Single;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class SingleDAOImp implements SingleDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;

    public SingleDAOImp(Connection conn){
        super();
        this.conn = conn;
    }


    @Override
    public ArrayList<Single> select(Single single) throws SQLException {
        try{
            String sql = "select"
                    + " song.name as song,"
                    + " album.name as album,"
                    + " duration"
                    +" from"
                    + " song"
                    +" inner join"
                    + " singer"
                    +" on "
                    + " song.singerId = singer.id "
                    + " inner join "
                    + " album "
                    + " on "
                    + " song.albumId = album.id "
                    +" where 1=1 ";

//            String sql = " select album.name from album where 1=1 ";




            // 添加条件
            String condition = single.getCondition();
            if(condition!=null && !condition.equals("")){
                sql += " and" + condition;
            }
            // 排序
            String orderBy = single.getOrderBy();
            if(orderBy != null && !orderBy.equals("")){
                sql += orderBy;
            }
            // 分页
            String limit = single.getLimit();
            if(limit != null && limit.equals("")){
                sql += limit;
            }

            // 控制台输出sql语句，检验正确性
            System.out.println("Album SELECT: " +sql);

            // 创建prepareStatement对象
            pst = conn.prepareStatement(sql);
            // 执行查询语句并返回结果集
            ResultSet rs = pst.executeQuery();

            // 创建ArrayList对象存储每条记录
            ArrayList<Single> resultList = new ArrayList<Single>();

            while(rs.next()){
                Single tmp = new Single();

                tmp.setSong(rs.getString("song"));
                tmp.setAlbum(rs.getString("album"));
                tmp.setDuration(rs.getString("duration"));
                resultList.add(tmp);
            }

            return resultList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int count(Single single) throws SQLException {
        try{
            // sql语句
            String sql = "select count(*) as counts from song inner join singer on  song.singerId=singer.id  where 1=1";
            // 添加条件
            String condition = single.getCondition();
            if(condition != null && !condition.equals("")){
                sql += " and" + condition;
            }

            pst = conn.prepareStatement(sql);

            // 控制台输出sql语句，检验正确性
//            System.out.println("album COUNT: "+sql);

            ResultSet rs = pst.executeQuery();
            rs.next();

            int counts = Integer.parseInt(rs.getString("counts"));

            return counts;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
