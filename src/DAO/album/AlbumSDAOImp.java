package DAO.album;

//import POJO.Album;
import POJO.AlbumS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumSDAOImp  implements  AlbumSDAO{
    private Connection conn = null;
    private PreparedStatement pst = null;

    public AlbumSDAOImp(Connection conn) {
        super();
        this.conn = conn;
    }




        @Override
        public ArrayList<AlbumS> select (AlbumS albums) throws SQLException {
            try {
                String sql = "select"
                        + " song.name as song,"
                        + " singer.name as singer"
                        + " from"
                        + " song"
                        + " inner join"
                        + " singer"
                        + " on "
                        + " song.singerId = singer.id "
//                    + " inner join "
//                    + " song "
//                    + " on "
//                    + " album.songId = song.albumId "
//                        + " inner join"
//                        + " area"
//                        + " on"
//                        + " album.areaId = area.id"
                        + " where 1=1 ";

//            String sql = " select album.name from album where 1=1 ";


                // 添加条件
                String condition = albums.getCondition();
                if (condition != null && !condition.equals("")) {
                    sql += " and" + condition;
                }
                // 排序
                String orderBy = albums.getOrderBy();
                if (orderBy != null && !orderBy.equals("")) {
                    sql += orderBy;
                }
                // 分页
                String limit = albums.getLimit();
                if (limit != null && limit.equals("")) {
                    sql += limit;
                }

                // 控制台输出sql语句，检验正确性
                System.out.println("AlbumS SELECT: " + sql);

                // 创建prepareStatement对象
                pst = conn.prepareStatement(sql);
                // 执行查询语句并返回结果集
                ResultSet rs = pst.executeQuery();

                // 创建ArrayList对象存储每条记录
                ArrayList<AlbumS> resultList = new ArrayList<AlbumS>();

                while (rs.next()) {
                    AlbumS tmp = new AlbumS();

                    tmp.setSong(rs.getString("song"));
                    tmp.setSinger(rs.getString("singer"));
                    resultList.add(tmp);
                }

                return resultList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    public int count(AlbumS albums) throws SQLException {
        try{
            // sql语句
            String sql = "select count(*) as counts from song where 1=1";
            // 添加条件
            String condition = albums.getCondition();
            if(condition != null && !condition.equals("")){
                sql += " and" + condition;
            }

            pst = conn.prepareStatement(sql);

            // 控制台输出sql语句，检验正确性
            System.out.println("albums COUNT: "+sql);

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
