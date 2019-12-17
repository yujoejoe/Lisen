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
                    + " language.name as language,"
                    + " album.genre as genre"
                    +" from"
                    + " album"
                    +" inner join"
                    + " singer"
                    +" on "
                    + " album.singerId = singer.id "
                    + " inner join "
                    + " song "
                    + " on "
                    + " song.albumId = album.id "
                    +" inner join"
                    + " language"
                    +" on"
                    + " album.languageId = language.id"
                    +" where 1=1 ";

//            String sql = " select album.name from album where 1=1 ";




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
                tmp.setLanguage(rs.getString("language"));
                tmp.setGenre(rs.getString("genre"));
                resultList.add(tmp);
        }

            return resultList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int count(Album  album) throws SQLException {
        try{
            // sql语句
            String sql = "select count(*) as counts from album inner join singer  on  album.singerId = singer.id   inner join song  on  song.albumid = album.id   where 1=1";
            // 添加条件
            String condition = album.getCondition();
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
