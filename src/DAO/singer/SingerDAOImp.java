package DAO.singer;

import POJO.Singer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/11/28.
 */
public class SingerDAOImp implements SingerDAO {
    private Connection conn = null;
    private PreparedStatement pst = null;


    public SingerDAOImp(){
        super();
    }
    public SingerDAOImp(Connection conn){
        super();
        this.conn = conn;
    }




    @Override
    public ArrayList<Singer> select(Singer singer) throws SQLException {
        try{
            String sql = "select"
                        + " singer.name as name,"
                        + " singer.alias as alias,"
                        + " singer.py as py,"
                        + " singer.sex as sex,"
                        + " singer.birthday as birthday,"
                        + " area.name as area,"
                        + " style.name as style,"
                        + " singer.songNum as songNum,"
                        + " singer.fans as fans"
                        +" from"
                        + " singer"
                        +" inner join"
                        + " area"
                        +" on"
                        + " singer.areaId = area.id"
                        +" inner join"
                        + " style"
                        +" on"
                        + " singer.styleId = style.id"
                        +" where 1=1";


            // 添加条件
            String condition = singer.getCondition();
            if(condition!=null && !condition.equals("")){
                sql += " and" + condition;
            }
            // 排序
            String orderBy = singer.getOrderBy();
            if(orderBy != null && !orderBy.equals("")){
                sql += orderBy;
            }
            // 分页
            String limit = singer.getLimit();
            if(limit != null && limit.equals("")){
                sql += limit;
            }

            // 控制台输出sql语句，检验正确性
            System.out.println("Singer SELECT: " +sql);

            // 创建prepareStatement对象
            pst = conn.prepareStatement(sql);
            // 执行查询语句并返回结果集
            ResultSet rs = pst.executeQuery();

            // 创建ArrayList对象存储每条记录
            ArrayList<Singer> resultList = new ArrayList<Singer>();
            
            while(rs.next()){
                Singer tmp = new Singer();
                tmp.setSex(rs.getString("sex"));
                tmp.setName(rs.getString("name"));
                tmp.setAlias(rs.getString("alias"));
                tmp.setPy(rs.getString("py"));
                tmp.setSongNum(rs.getInt("songNum"));
                tmp.setBirthday(rs.getString("birthday"));
                tmp.setFans(rs.getString("fans"));
                tmp.setArea(rs.getString("area"));
                tmp.setStyle(rs.getString("style"));

                resultList.add(tmp);
            }
            
            return resultList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Singer> selectRan(Singer singer) throws SQLException {
        return null;
    }

    @Override
    public int insert(Singer singer) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Singer singer) throws SQLException {
        return 0;
    }

    @Override
    public int update(Singer singer) throws SQLException {
        return 0;
    }

    @Override
    public int count(Singer singer) throws SQLException {
        try{
            // sql语句
            String sql = "select count(*) as counts from singer where 1=1";
            // 添加条件
            String condition = singer.getCondition();
            if(condition != null && !condition.equals("")){
                sql += " and" + condition;
            }

            pst = conn.prepareStatement(sql);

            // 控制台输出sql语句，检验正确性
            System.out.println("singer COUNT: "+sql);

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
