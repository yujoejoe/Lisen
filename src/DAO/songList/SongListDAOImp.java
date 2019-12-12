package DAO.songList;

import POJO.SongList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/12.
 */
public class SongListDAOImp implements SongListDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;

    public SongListDAOImp(){
        super();
    }

    public SongListDAOImp(Connection conn){
        super();
        this.conn = conn;
    }

    @Override
    public ArrayList<SongList> select(SongList sList) throws SQLException {
        String sql = "select"
                    + " songlist.id as id,"
                    + " songlist.name as name,"
                    + " user.name as author,"
                    + " style.name as style,"
                    + " theme.name as theme,"
                    + " songlist.date as date,"
                    + " songlist.play as play,"
                    + " songlist.img as img "
                    +"from"
                    + " songlist "
                    +"left join user "
                    +"on songlist.userId = user.id "
                    +"left join style "
                    +"on songlist.styleId = style.id "
                    +"left join theme "
                    +"on songlist.themeId = theme.id "
                    +"where 1=1 ";

        // 添加条件
        String condition = sList.getCondition();
        if(condition!=null && !condition.equals("")){
            sql += condition;
        }
        // 排序
        String orderBy = sList.getOrderBy();
        if(orderBy != null && !orderBy.equals("")){
            sql += orderBy;
        }
        // 分页
        String limit = sList.getLimit();
        if(limit != null && !limit.equals("")){
            sql += limit;
        }

        // 控制台输出sql语句，检验正确性
        System.out.println("SongList SELECT: " +sql);

        // 创建prepareStatement对象
        pst = conn.prepareStatement(sql);
        // 执行查询语句并返回结果集
        ResultSet rs = pst.executeQuery();

        ArrayList<SongList> resultList = new ArrayList<>();
        while(rs.next()){
            SongList tmp = new SongList();
            tmp.setAuthor(rs.getString("author"));
            tmp.setStyle(rs.getString("style"));
            tmp.setTheme(rs.getString("theme"));
            tmp.setName(rs.getString("name"));
            tmp.setDate(rs.getString("date"));
            tmp.setImg(rs.getString("img"));
            tmp.setPlay(rs.getInt("play"));
            tmp.setId(rs.getInt("id"));
            resultList.add(tmp);
        }
        return resultList;
    }

    @Override
    public ArrayList<SongList> selectRan(SongList sList) throws SQLException {
        return null;
    }

    @Override
    public int insert(SongList sList) throws SQLException {
        return 0;
    }

    @Override
    public int delete(SongList sList) throws SQLException {
        return 0;
    }

    @Override
    public int update(SongList sList) throws SQLException {
        return 0;
    }

    @Override
    public int count(SongList sList) throws SQLException {
        // sql语句
        String sql = "select count(id) as counts from songlst where 1=1";

        pst = conn.prepareStatement(sql);

        // 控制台输出sql语句，检验正确性
        System.out.println("mv COUNT: "+sql);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return Integer.parseInt(rs.getString("counts"));
    }
}
