package DAO.album;

import POJO.Album;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumDAOImp implements AlbumDAO{

    private Connection conn = null;
    private PreparedStatement pst = null;

    public AlbumDAOImp(Connection conn){
        super();
        this.conn = conn;
    }


    @Override
    public ArrayList<Album> select(Album album) throws SQLException {
        try{
            String sql = "select"
                    + " album.name as name,"
                    + " singer.name as singer,"
                    + " song.name as song,"
                    + " album.date as date,"
                    + " album.img as img,"
                    + " album.company as company,"
                    + " area.name as area,"
                    +" from"
                    + " album"
                    +" inner join"
                    + " singer"
                    +" on "
                    + " alnum.singerId = singer.id "
                    + " inner join "
                    + " song "
                    + " on "
                    + " album.songId = song.id "
                    +" inner join"
                    + " area"
                    +" on"
                    + " album.areaId = area.id"
                    +" where 1=1 ";


            // 添加条件
            String condition = album.getCondition();
            if(condition!=null && !condition.equals("")){
                sql += " and" + condition;
            }
            // 排序
            String orderBy = album.getOrderBy();
            if(orderBy != null && !orderBy.equals("")){
                sql += orderBy;
            }
            // 分页
            String limit = album.getLimit();
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
            ArrayList<Album> resultList = new ArrayList<Album>();

            while(rs.next()){
                Album tmp = new Album();

                tmp.setName(rs.getString("name"));
                tmp.setSinger(rs.getString("singer"));
                tmp.setSong(rs.getString("song"));
                tmp.setDate(rs.getString("date"));
                tmp.setImg(rs.getString("img"));
                tmp.setCompany(rs.getString("company"));
                tmp.setArea(rs.getString("area"));
                resultList.add(tmp);
            }

            return resultList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}