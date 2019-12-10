package ServiceDAO.users;

import DAO.user.UserDAOImp;
import POJO.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceDAOImp implements UserServiceDAO {
  /**
   * 账号验证业务
   */
  public ArrayList<User> checkUser(User user) {
    Connection conn = DBUtil.getConnection();
    UserDAOImp UserD = new UserDAOImp(conn);
    try{
      ArrayList<User> UserList = new ArrayList<User>();
      UserList = UserD.select(user);//返回记录集
      conn.commit();
      return UserList;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return null;
    }finally{
      if(conn != null){
        DBUtil.closeConnection(conn);
      }
    }
  }

  /**
   * 查询业务
   */
  public ArrayList<User> select(User user) {
    Connection conn = DBUtil.getConnection();
    UserDAOImp studentD = new UserDAOImp(conn);
    try{
      ArrayList<User> userList = new ArrayList<User>();
      userList = studentD.select(user);//返回记录集
      conn.commit();
      return userList;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return null;
    }finally{
      if(conn != null){
        DBUtil.closeConnection(conn);
      }
    }
  }

  /**
   * 统计业务
   */
  public int count(User user) {
    Connection conn = DBUtil.getConnection();
    UserDAOImp usersD = new UserDAOImp(conn);
    int cnt;
    try{
      cnt = usersD.count(user);//返回记录集
      conn.commit();
      return cnt;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
        DBUtil.closeConnection(conn);
      }
    }
  }
  
  /**
   * 添加业务
   */
  public int insert(User user){
	Connection conn = DBUtil.getConnection();
	UserDAOImp UserD = new UserDAOImp(conn);
    try{
      int i = UserD.insert(user);
      conn.commit();
      return i;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
    	DBUtil.closeConnection(conn);
      }
    }
  }

  /**
   * 更新业务(密码、相片、昵称等)
   */
  public int updatePassword(User user){
	Connection conn = DBUtil.getConnection();
	UserDAOImp UserD = new UserDAOImp(conn);
    try{
      int i = UserD.updatePart(user);
      conn.commit();
      return i;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
    	DBUtil.closeConnection(conn);
      }
    }
  }
  
  /**
   * 删除业务
   */
  public int delete(User user){
	Connection conn = DBUtil.getConnection();
	UserDAOImp UserD = new UserDAOImp(conn);
    try{
      int i = UserD.delete(user);
      conn.commit();
      return i;
    }catch(Exception e){
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }finally{
      if(conn != null){
    	DBUtil.closeConnection(conn);
      }
    }
  }
}