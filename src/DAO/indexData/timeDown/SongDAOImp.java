package DAO.indexData.timeDown;

import POJO.indexData.TimeDown;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongDAOImp implements SongDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;

    public SongDAOImp(Connection conn) {
        super();
        this.conn = conn;
    }

        @Override
        public ArrayList<TimeDown> select (TimeDown timeDown) throws SQLException {
            try {
                String sql = "select distinct"
                        + " song.name as song,"
                        + " singer.name as singer,"
                        + " album.img as img,"
                        + " song.date as date,"
                        + " song.duration as duration"
                        + " from"
                        + " song"
                        + " inner join"
                        + " singer"
                        + " on "
                        + " song.singerId = singer.id "
                        + " inner join "
                        + " album "
                        + " on "
                        + " song.albumId = album.id"
                        + " WHERE singer.areaId=1"
//                        + " ORDER BY date DESC"
////                        + " LIMIT 20;";
                ;


                // 添加条件
                String condition = timeDown.getCondition();
                if (condition != null && !condition.equals("")) {
                    sql += " and" + condition;
                }

                // 排序
                String orderBy = timeDown.getOrderBy();
                if (orderBy != null && !orderBy.equals("")) {
                    sql += orderBy;
                }

                // 分页
                String limit = timeDown.getLimit();
                if (limit != null && limit.equals("")) {
                    sql += limit;
                }

                /*// 控制台输出sql语句，检验正确性
                System.out.println("timeDown SELECT: " + sql);*/

                // 创建prepareStatement对象
                pst = conn.prepareStatement(sql);

                // 执行查询语句并返回结果集
                ResultSet rs = pst.executeQuery();

                // 创建ArrayList对象存储每条记录
                ArrayList<TimeDown> resultList = new ArrayList<TimeDown>();

                while (rs.next()) {
                    TimeDown tmp = new TimeDown();
                    tmp.setImg(rs.getString("img"));
                    tmp.setSong(rs.getString("song"));
                    tmp.setSinger(rs.getString("singer"));
                    tmp.setDate(rs.getString("date"));
                    tmp.setDuration(rs.getString("duration"));
                    resultList.add(tmp);
                }

                return resultList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    public int count(TimeDown timeDown) throws SQLException {
        try{
            // sql语句
            String sql = "select count(*) as counts from song  " +
                    "INNER JOIN album ON song.albumId = album.id  " +
                    "INNER JOIN singer ON song.singerId = singer.id " +
                    "where singer.areaId=1";
            // 添加条件
            String condition = timeDown.getCondition();
            if(condition != null && !condition.equals("")){
                sql += " and" + condition;
            }

            pst = conn.prepareStatement(sql);

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
