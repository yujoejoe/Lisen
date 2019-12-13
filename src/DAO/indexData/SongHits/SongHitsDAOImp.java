package DAO.indexData.SongHits;

import POJO.indexData.SongHits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongHitsDAOImp implements SongHitsDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;

    public SongHitsDAOImp(Connection conn) {
        super();
        this.conn = conn;
    }

        @Override
        public ArrayList<SongHits> select (SongHits SongHits) throws SQLException {
            try {
                String sql = "select distinct"
                        + " song.name as song,"
                        + " singer.name as singer,"
                        + " song.hits as hits,"
                        + " album.img as img"
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
                        + " ORDER BY hits DESC"
                        + " LIMIT 20;";


                // 添加条件
                String condition = SongHits.getCondition();
                if (condition != null && !condition.equals("")) {
                    sql += " and" + condition;
                }

                // 排序
                String orderBy = SongHits.getOrderBy();
                if (orderBy != null && !orderBy.equals("")) {
                    sql += orderBy;
                }

                // 分页
                String limit = SongHits.getLimit();
                if (limit != null && limit.equals("")) {
                    sql += limit;
                }

                /*// 控制台输出sql语句，检验正确性
                System.out.println("SongHits SELECT: " + sql);*/

                // 创建prepareStatement对象
                pst = conn.prepareStatement(sql);

                // 执行查询语句并返回结果集
                ResultSet rs = pst.executeQuery();

                // 创建ArrayList对象存储每条记录
                ArrayList<SongHits> resultList = new ArrayList<SongHits>();

                while (rs.next()) {
                    SongHits tmp = new SongHits();
                    tmp.setImg(rs.getString("img"));
                    tmp.setName(rs.getString("song"));
                    tmp.setSinger(rs.getString("singer"));
                    tmp.setHits(rs.getString("hits"));
                    resultList.add(tmp);
                }

                return resultList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    public int count(SongHits SongHits) throws SQLException {
        try{
            // sql语句
            String sql = "select count(*) as counts from song  " +
                    "INNER JOIN album ON song.albumId = album.id  " +
                    "INNER JOIN singer ON song.singerId = singer.id " ;
            // 添加条件
            String condition = SongHits.getCondition();
            if(condition != null && !condition.equals("")){
                sql += " and" + condition;
            }

            pst = conn.prepareStatement(sql);

            // 控制台输出sql语句，检验正确性
//            System.out.println("timeDown COUNT: "+sql);

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
