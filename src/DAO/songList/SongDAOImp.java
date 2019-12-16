package DAO.songList;

import POJO.songList.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        return null;
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
        return 0;
    }

    @Override
    public int count(Song song) throws SQLException {
        return 0;
    }
}