package DAO.mv;

import POJO.MV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/3.
 */
public class MvDAOImp implements MvDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;

    public MvDAOImp(){
        super();
    }

    public MvDAOImp(Connection conn){
        super();
        this.conn = conn;
    }

    @Override
    public ArrayList<MV> select(MV mv) throws SQLException {
        String sql = "select"
                    + " mv.id as id,"
                    + " mv.title as title,"
                    + " singer.name as singer,"
                    + " song.name as song,"
                    + " mv.play as play,"
                    + " mv.date as date,"
                    + " mv.img as img,"
                    + " mv.url as url,"
                    + " area.name as area,"
                    + " version.name as version "
                    +"from"
                    + " mv "
                    +"left join singer "
                    +"on mv.singerId = singer.id "
                    +"left join song "
                    +"on mv.songId = song.id "
                    +"left join area "
                    +"on singer.areaId = area.id "
                    +"left join version "
                    +" on mv.versionId = version.id "
                    +" where 1=1 ";

        // 添加条件
        String condition = mv.getCondition();
        if(condition!=null && !condition.equals("")){
            sql += condition;
        }
        // 排序
        String orderBy = mv.getOrderBy();
        if(orderBy != null && !orderBy.equals("")){
            sql += orderBy;
        }
        // 分页
        String limit = mv.getLimit();
        if(limit != null && !limit.equals("")){
            sql += limit;
        }

        // 控制台输出sql语句，检验正确性
        System.out.println("MV SELECT: " +sql);

        // 创建prepareStatement对象
        pst = conn.prepareStatement(sql);
        // 执行查询语句并返回结果集
        ResultSet rs = pst.executeQuery();

        ArrayList<MV> resultList = new ArrayList<>();
        while(rs.next()){
            MV tmp = new MV();
            tmp.setVersion(rs.getString("version"));
            tmp.setSinger(rs.getString("singer"));
            tmp.setTitle(rs.getString("title"));
            tmp.setDate(rs.getString("date"));
            tmp.setArea(rs.getString("area"));
            tmp.setImg(rs.getString("img"));
            tmp.setUrl(rs.getString("url"));
            tmp.setPlay(rs.getInt("play"));
            tmp.setId(rs.getInt("id"));
            resultList.add(tmp);
        }
        return resultList;
    }

    @Override
    public ArrayList<MV> selectRan(MV mv) throws SQLException {
        String sql = "select"
                + " mv.title as title,"
                + " singer.name as singer,"
                + " mv.play as play,"
                + " mv.date as date,"
                + " mv.img as img,"
                + " mv.url as url,"
                + " area.name as area,"
                + " version.name as version"
                +" from"
                + " mv"
                +" inner join"
                + " singer"
                +" on"
                + " mv.singerId = singer.id"
                +" inner join"
                + " area"
                +" on"
                + " mv.areaId = area.id"
                +" inner join"
                + " version"
                +" on"
                + " mv.versionId = version.id"
                + " limit 0, 12";

        // 控制台输出sql语句，检验正确性
        System.out.println("MV SELECT: " +sql);

        // 创建prepareStatement对象
        pst = conn.prepareStatement(sql);
        // 执行查询语句并返回结果集
        ResultSet rs = pst.executeQuery();

        ArrayList<MV> resultList = new ArrayList<>();
        while(rs.next()){
            MV tmp = new MV();
            tmp.setVersion(rs.getString("version"));
            tmp.setSinger(rs.getString("singer"));
            tmp.setTitle(rs.getString("title"));
            tmp.setArea(rs.getString("area"));
            tmp.setDate(rs.getString("date"));
            tmp.setImg(rs.getString("img"));
            tmp.setUrl(rs.getString("url"));
            tmp.setPlay(rs.getInt("play"));
            resultList.add(tmp);
        }
        return resultList;

    }

    @Override
    public int insert(MV mv) throws SQLException {
        return 0;
    }

    @Override
    public int delete(MV mv) throws SQLException {
        return 0;
    }

    @Override
    public int update(MV mv) throws SQLException {
        return 0;
    }

    @Override
    public int count(MV mv) throws SQLException {
        try{
            // sql语句
            String sql = "select count(id) as counts from mv where 1=1";

            pst = conn.prepareStatement(sql);

            // 控制台输出sql语句，检验正确性
            System.out.println("mv COUNT: "+sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("counts"));
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
