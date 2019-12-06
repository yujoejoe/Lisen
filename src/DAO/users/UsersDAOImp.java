package DAO.users;

import POJO.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/2.
 */
public class UsersDAOImp implements UsersDAO {
  private Connection conn = null;
  private PreparedStatement pst = null;

  public UsersDAOImp(Connection conn) {
    super();
    this.conn = conn;
  }

 /* public UsersDAOImp() {
    super();
  }*/

  /**
   * 用于登录的查询验证
   */
  public ArrayList<Users> select(Users users) throws SQLException {
    try{
      String sql = "select "
                 + "  id,name,pswd,phone,sex,email "
                 + "from user "
                 + "where 1=1 ";
      //查询
      String condition = users.getCondition();
      if(condition != null && ! condition.equals("")){
        sql += " and " + condition;
      }
      pst = conn.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      ArrayList<Users> UserList = new ArrayList<Users>();
      if(rs.next()){
        for(int i=0;i<=rs.getRow();i++){
          Users temp = new Users();
          temp.setId(rs.getInt("id"));
          temp.setName(rs.getString("name"));
          temp.setPswd(rs.getString("pswd"));
          temp.setPhone(rs.getString("phone"));
          temp.setSex(rs.getString("sex"));
          temp.setEmail(rs.getString("email"));
          UserList.add(temp);
          rs.next();
        }
      }
      return UserList;
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 插入/注册
   */
  public int insert(Users users) throws SQLException {
    try{
      String sql = "insert into user(name,pswd,phone,sex,email) values(?,?,?,?,?) ";
      pst = conn.prepareStatement(sql);
      pst.setString(1, users.getName());
      pst.setString(2, users.getPswd());
      pst.setString(3, users.getPhone());
      pst.setString(4, users.getSex());
      pst.setString(5, users.getEmail());
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 统计记录
   */
  public int count(Users users) throws SQLException {
    try{
      String sql = "select count(*) as cnt from user where 1=1";
      String condition = users.getCondition();
      if(condition!=null && !condition.equals("")){
        sql += " and " + condition;
      }
      String limit = users.getLimit();
      if(limit!=null && !limit.equals("")){
        sql += limit;
      }
      pst = conn.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      rs.next();
      int cnt = Integer.parseInt(rs.getString("cnt"));
      return cnt;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 更新记录
   */
  public int update(Users users) throws SQLException {
    try{
      String sql = "update user set name=?,pswd=?,phone=?,sex=?,email=? where id=?";
      pst = conn.prepareStatement(sql);
      pst.setString(1, users.getName());
      pst.setString(2, users.getPswd());
      pst.setString(3, users.getPhone());
      pst.setString(4, users.getSex());
      pst.setString(5, users.getEmail());
      pst.setInt(6, users.getId());
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 更新部分记录
   */
  public int updatePart(Users users) throws SQLException {
    try{
      int cnt = 0;
      String sql = "update user set ";
      if(users.getName() != null && !users.getName().equals("")){
        sql += " nickName = '" + users.getName() + "',";
        cnt ++;
      }
      if(users.getPswd() != null && !users.getPswd().equals("")){
        sql += " pswd = '" + users.getPswd() + "',";
        cnt ++;
      }
      if(users.getSex() != null && !users.getSex().equals("")){
        sql += " sex = '" + users.getSex() + "',";
        cnt ++;
      }
      if(cnt > 0){
        sql = sql.substring(0,sql.length()-1);//去掉最后一个逗号
        sql += " where 1=1 ";//开始拼接条件
        if(users.getId()+"" != ""){
          sql += " and id = "+users.getId();
        }
      }
      pst = conn.prepareStatement(sql);
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 删除记录
   */
  public int delete(Users users) throws SQLException {
    try{
      String sql = "delete from user where id=?";
      pst = conn.prepareStatement(sql);
      pst.setInt(1, users.getId());
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }

  
}
