package DAO.songList;

import POJO.Album;
import POJO.Singer;
import POJO.songList.Song;
import ServiceDAO.album.AlbumServiceDAOImp;
import ServiceDAO.singer.SingerServiceDAOImp;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2019/12/16.
 */
public class SongDAOImp implements SongDAO{
    private Connection conn = null;
    private PreparedStatement pst = null;
    
    public SongDAOImp(){
        super();
    }
    public SongDAOImp(Connection conn){
        super();
        this.conn = conn;
    }
    
    @Override
    public ArrayList<Song> select(Song song) throws SQLException {
        String sql = "select"
                    + " song.id as id,"
                    + " song.name as song,"
                    + " singer.name as singer,"
                    + " album.name as album,"
                    + " style.name as style,"
                    + " song.format as format,"
                    + " song.duration as time,"
                    + " song.date as date,"
                    + " song.hits as hits,"
                    + " song.status as status "
                    +"from"
                    + " song "
                    +"left join singer "
                    +"on song.singerId = singer.id "
                    +"left join album "
                    +"on song.albumId = album.id "
                    +"left join style "
                    +"on song.styleId = style.id "
                    +"where 1=1 ";

        // 添加条件
        String condition = song.getCondition();
        if(condition!=null && !condition.equals("")){
            sql += condition;
        }
        // 排序
        String orderBy = song.getOrderBy();
        if(orderBy != null && !orderBy.equals("")){
            sql += orderBy;
        }
        // 分页
        String limit = song.getLimit();
        if(limit != null && !limit.equals("")){
            sql += limit;
        }

        // 控制台输出sql语句，检验正确性
        System.out.println("Song SELECT: " +sql);

        // 创建prepareStatement对象
        pst = conn.prepareStatement(sql);
        // 执行查询语句并返回结果集
        ResultSet rs = pst.executeQuery();

        ArrayList<Song> resultList = new ArrayList<>();
        while(rs.next()){
            Song tmp = new Song();
            tmp.setDuration(rs.getString("time"));
            tmp.setSinger(rs.getString("singer"));
            tmp.setFormat(rs.getString("format"));
            tmp.setStyle(rs.getString("style"));
            tmp.setAlbum(rs.getString("album"));
            tmp.setStatus(rs.getInt("status"));
            tmp.setSong(rs.getString("song"));
            tmp.setDate(rs.getString("date"));
            tmp.setHits(rs.getInt("hits"));
            tmp.setId(rs.getInt("id"));
            resultList.add(tmp);
        }
        return resultList;
    }

    @Override
    public ArrayList<Song> selectList(Song song) throws SQLException {
        String sql = "select "
                + "song.name as song,"
                + "singer.name as singer,"
                + "album.name as album,"
                + "song.duration as duration "
                +"from"
                + " songlistmenu "
                +"left join songlist "
                +"on songlistmenu.songListId = songlist.id "
                +"left join song "
                +"on songlistmenu.songId = song.id "
                +"left join singer "
                +"on song.singerId = singer.id "
                +"left join album "
                +"on song.albumId = album.id "
                +"where 1=1 ";

        // 添加条件
        String condition = song.getCondition();
        if(condition!=null && !condition.equals("")){
            sql += condition;
        }
        // 排序
        String orderBy = song.getOrderBy();
        if(orderBy != null && !orderBy.equals("")){
            sql += orderBy;
        }
        // 分页
        String limit = song.getLimit();
        if(limit != null && !limit.equals("")){
            sql += limit;
        }

        // 控制台输出sql语句，检验正确性
        System.out.println("Song SELECT: " +sql);

        // 创建prepareStatement对象
        pst = conn.prepareStatement(sql);
        // 执行查询语句并返回结果集
        ResultSet rs = pst.executeQuery();

        ArrayList<Song> resultList = new ArrayList<>();
        while(rs.next()){
            Song tmp = new Song();
            tmp.setDuration(rs.getString("duration"));
            tmp.setSinger(rs.getString("singer"));
            tmp.setAlbum(rs.getString("album"));
            tmp.setSong(rs.getString("song"));
            resultList.add(tmp);
        }
        return resultList;
    }

    @Override
    public int insert(Song song) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Song song) throws SQLException {
        return 0;
    }

    @Override
    public int update(Song song) throws SQLException {
        Map<String, Object> para = new HashMap<>();

        int id = song.getId();
        if(id != -1){
            para.put("id", id);
        }
        int status = song.getStatus();
        if(status != -1){
            para.put("status", status);
        }
        String name = song.getSong();
        if(name != null && !name.equals("")){
            para.put("name", "'" + name + "'");
        }
        String data = song.getDate();
        if(data != null && !data.equals("")){
            para.put("data", "'" + data + "'");
        }
        String singer = song.getSinger();
        if(singer != null && !singer.equals("")){
            para.put("singer", "'" + singer + "'");
        }
        String album = song.getAlbum();
        if(album != null && !album.equals("")){
            para.put("album", "'" + album + "'");
        }
        String style = song.getStyle();
        if(style != null && !style.equals("")){
            para.put("style", "'" + style + "'");
        }
        String format = song.getFormat();
        if(format != null && !format.equals("")){
            para.put("format", "'" + format + "'");
        }
        String duration = song.getDuration();
        if(duration != null && !duration.equals("")){
            para.put("duration", "'" + duration + "'");
        }

        if(para.size() != 0 && id > 0) {
            StringBuilder sql = new StringBuilder("update song set");

            for (String key: para.keySet()) {
                if(key.equals("singer")){
                    String singerSql = "select singer.id as id from singer where singer.name = " + key;
                    System.out.println("Song UPDATE(singer): " + singerSql);
                    Connection conn = DBUtil.getConnection();
                    PreparedStatement pst = conn.prepareStatement(singerSql);
                    ResultSet rs = pst.executeQuery();
                    Object singerId = null;
                    if(rs.next()){
                        singerId = rs.getInt("id");
                    }
                    para.put(key, singerId);
                    DBUtil.closeConnection(conn);
                }
                if(key.equals("album")){
                    String albumSql = "select album.id as id from album where album.name = " + key;
                    System.out.println("Song UPDATE(album): " + albumSql);
                    Connection conn = DBUtil.getConnection();
                    PreparedStatement pst = conn.prepareStatement(albumSql);
                    ResultSet rs = pst.executeQuery();
                    Object albumId = null;
                    if(rs.next()){
                        albumId = rs.getInt("id");
                    }
                    para.put(key, albumId);
                    DBUtil.closeConnection(conn);
                }
                if(key.equals("style")){
                    String styleSql = "select style.id as id from style where style.name = " + key;
                    System.out.println("Song UPDATE(style): " + styleSql);
                    Connection conn = DBUtil.getConnection();
                    PreparedStatement pst = conn.prepareStatement(styleSql);
                    ResultSet rs = pst.executeQuery();
                    Object styleId = null;
                    if(rs.next()){
                        styleId = rs.getInt("id");
                    }
                    para.put(key, styleId);
                    DBUtil.closeConnection(conn);
                }
                String str = " " + key + "=" + para.get(key) + ",";
                sql.append(str);
            }
            sql.setCharAt(sql.length() - 1, ' ');

            if(para.size() != 0 && song.getId() > 0){
                String condition = "where song.id = " + id;
                sql.append(condition);
                System.out.println("Song UPDATE: " + sql.toString());

                pst = conn.prepareStatement(sql.toString());

                return pst.executeUpdate();

            }

        }

        return -1;
    }

    @Override
    public int count(Song song) throws SQLException {
        // sql语句
        String sql = "select count(id) as counts from song where 1=1";

        pst = conn.prepareStatement(sql);

        // 控制台输出sql语句，检验正确性
        System.out.println("song COUNT: "+sql);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return Integer.parseInt(rs.getString("counts"));
    }
}
