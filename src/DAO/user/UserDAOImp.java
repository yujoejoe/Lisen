package DAO.user;

import POJO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 2019/12/2.
 */
public class UserDAOImp implements UserDAO {
  private Connection conn = null;
  private PreparedStatement pst = null;

  public UserDAOImp(Connection conn) {
    super();
    this.conn = conn;
  }

  public UserDAOImp() {
    super();
  }

  /**
   * 用于登录的查询验证
   */
  public ArrayList<User> select(User user) throws SQLException {
    try{
      String sql = "select"
                 + "  user.id as id,"
                 + "  user.name as name,"
                 + "  user.pswd as pswd,"
                 + "  user.phone as phone,"
                 + "  user.sex as sex,"
                 + "  user.email as email,"
                 + "  user.img as img "
                 + "from"
                 + "  user "
                 + "where 1=1 ";
      //查询
      String condition = user.getCondition();
      if(condition != null && ! condition.equals("")){
        sql += " and " + condition;
      }

//      System.out.println("user SELECT: " + sql);

      pst = conn.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      ArrayList<User> UserList = new ArrayList<User>();
      if(rs.next()){
        for(int i=0;i<=rs.getRow();i++){
          User temp = new User();
          temp.setId(rs.getInt("id"));
          temp.setSex(rs.getString("sex"));
          temp.setImg(rs.getString("img"));
          temp.setName(rs.getString("name"));
          temp.setPswd(rs.getString("pswd"));
          temp.setPhone(rs.getString("phone"));
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
  public int insert(User user) throws SQLException {
    try{
      String sql = "insert into user(name,pswd,phone,sex,email,img) values(?,?,?,?,?,?) ";
      pst = conn.prepareStatement(sql);
      pst.setString(1, user.getName());
      pst.setString(2, user.getPswd());
      pst.setString(3, user.getPhone());
      pst.setString(4, user.getSex());
      pst.setString(5, user.getEmail());
      pst.setString(6, user.getImg());

      return pst.executeUpdate();
    }catch(Exception e){
      e.printStackTrace();
//      System.out.println("UserDAOImp Insert 异常！");
      return -1;
    }
  }

  /**
   * 统计记录
   */
  public int count(User user) throws SQLException {
    try{
      String sql = "select count(id) as cnt from user where 1=1";
      String condition = user.getCondition();
      if(condition!=null && !condition.equals("")){
        sql += " and " + condition;
      }
      String limit = user.getLimit();
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
  public int update(User user) throws SQLException {
    try{
      String sql = "update user set name=?,pswd=?,phone=?,sex=?,email=? where id=?";
      pst = conn.prepareStatement(sql);
      pst.setString(1, user.getName());
      pst.setString(2, user.getPswd());
      pst.setString(3, user.getPhone());
      pst.setString(4, user.getSex());
      pst.setString(5, user.getEmail());
      pst.setInt(6, user.getId());
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
  public int updatePart(User user) throws SQLException {
    try{
      int cnt = 0;
      String sql = "update user set ";
      if(user.getName() != null && !user.getName().equals("")){
        sql += " nickName = '" + user.getName() + "',";
        cnt ++;
      }
      if(user.getPswd() != null && !user.getPswd().equals("")){
        sql += " pswd = '" + user.getPswd() + "',";
        cnt ++;
      }
      if(user.getSex() != null && !user.getSex().equals("")){
        sql += " sex = '" + user.getSex() + "',";
        cnt ++;
      }
      if(cnt > 0){
        sql = sql.substring(0,sql.length()-1);//去掉最后一个逗号
        sql += " where 1=1 ";//开始拼接条件
        if(user.getId()+"" != ""){
          sql += " and id = "+ user.getId();
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
  public int delete(User user) throws SQLException {
    try{
      String sql = "delete from user where id=?";
      pst = conn.prepareStatement(sql);
      pst.setInt(1, user.getId());
      int i = pst.executeUpdate();
      return i;
    }catch(Exception e){
      e.printStackTrace();
      return -1;
    }
  }

  
}
